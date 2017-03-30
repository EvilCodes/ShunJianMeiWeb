package com.wenyuankeji.spring.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenyuankeji.spring.model.OrderinfoModel;
import com.wenyuankeji.spring.model.UserQuickpayModel;
import com.wenyuankeji.spring.model.UserinfoModel;
import com.wenyuankeji.spring.service.IOrderinfoService;
import com.wenyuankeji.spring.service.IUserQuickpayService;
import com.wenyuankeji.spring.service.IWalletPayService;
import com.wenyuankeji.spring.service.IUserinfoService;
import com.wenyuankeji.spring.util.BaseException;
import com.wenyuankeji.spring.util.Constant;
import com.wenyuankeji.spring.util.MessagePushUtil;
import com.wenyuankeji.spring.util.SmsHelper;

/**
 * 
 * 支付宝，回调
 * @author Liuk
 *
 */
@Controller
@RequestMapping
public class PayController {

	@Autowired
	private IWalletPayService walletPayService;
	
	@Autowired
	private IOrderinfoService orderinfoService;

	@Autowired
	private IUserQuickpayService userQuickpayService;
	
	@Autowired
	private IUserinfoService userinfoService;
	
	/**
	 * 支付宝回调
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws BaseException
	 */
	@RequestMapping("notify_url")
	@ResponseBody
	public String notifyUrl(HttpServletRequest request) throws UnsupportedEncodingException, BaseException{

		try {
			//获取支付宝POST过来反馈信息
			Map<String,String> params = new HashMap<String,String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
				System.out.println("-----------------------------"+valueStr);
				params.put(name, valueStr);
			}
			
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
//				if(AlipayNotify.verify(params)){//验证成功
				//////////////////////////////////////////////////////////////////////////////////////////
				//请在这里加上商户的业务逻辑程序代码
				//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
				
				int orderId =Integer.valueOf(out_trade_no);
				
				System.out.println("支付订单ID-------------------------："+orderId);
				
				//支付宝请求日志 start
				String content = "out_trade_no:" + new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8")
						+ " | " + "trade_no:" + new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8")
						+ " | " + "trade_status:" + new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8")
						+ " | " + "total_fee:" + new String(request.getParameter("total_fee").getBytes("ISO-8859-1"),"UTF-8");
				
				System.out.println("支付宝请求日志-------------------------："+content);
				
				walletPayService.addBaseAlipaytradelog(String.valueOf(orderId), content, Constant.NORMAL_PAYSTATE_SUCCESS);

				System.out.println("支付宝添加请求日志-------------------------："+orderId);
				//支付宝请求日志 end
				

				OrderinfoModel orderinfo = orderinfoService.searchOrderinfo(orderId);
				if(trade_status.equals("TRADE_FINISHED")){
					//判断该笔订单是否在商户网站中已经做过处理
						//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
						//如果有做过处理，不执行商户的业务程序
					//注意：
					//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
					
					//支付成功更新订单状态
					if(orderinfo != null){
						System.out.println("orderinfo != null");
						//处理支付宝多次回调
						//订单待支付状态下执行更新订单状态
						if(orderinfo.getPaystate() == 1){	
							System.out.println("orderinfo.getPaystate() == 1");
							//支付成功更新订单状态
							if (walletPayService.updateOrderinfo(orderId)) {
								return "success";
							}else{
								return "fail";
							}
						}
					}else{
						System.out.println("orderinfo == null");
						return "fail";
					}
					//预约达成时推送消息
					/*List<String> userList = new ArrayList<>();
			        userList.add(String.valueOf(orderId));
			        MessagePushUtil.SendPush(userList,"预约达成","有用户预约啦，快去看看吧~");*/
					
				} else if (trade_status.equals("TRADE_SUCCESS")){
					//判断该笔订单是否在商户网站中已经做过处理
						//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
						//如果有做过处理，不执行商户的业务程序
					//注意：
					//付款完成后，支付宝系统发送该交易状态通知
					if(orderinfo != null){
						System.out.println("orderinfo != null");
						//处理支付宝多次回调
						//订单待支付状态下执行更新订单状态
						if(orderinfo.getPaystate() == 1){	
							System.out.println("orderinfo.getPaystate() == 1");
							//支付成功更新订单状态
							if (walletPayService.updateOrderinfo(orderId)) {
								return "success";
							}else{
								return "fail";
							}
						}
					}else{
						System.out.println("orderinfo == null");
						return "fail";
					}
					//预约达成时推送消息
					/*List<String> userList = new ArrayList<>();
			        userList.add(String.valueOf(orderId));
			        MessagePushUtil.SendPush(userList,"预约达成","有用户预约啦，快去看看吧~");*/
				}
//					int orderId =Integer.valueOf(out_trade_no);
//					OrderinfoModel orderInfo = orderinfoService.searchOrderinfo(orderId);
//					if(orderInfo != null){
//						orderinfoService.updateOrderinfoModel(orderId, 3);
//					}
				// add by jiazhaohui
				// 预约成功，发送短信
				orderinfoService.SendMobileMessage(orderinfo);
				//////////////---------推送消息-begin--------//////////////////////
				List<String> lsUserList = new ArrayList<String>();
				String contentMsg = "有用户预约啦，快去看看吧~";
				
				// 获取美发师的id
				lsUserList.add(String.valueOf(orderinfo.getUserid()));
				
				Map<String,Object> jsonMap = new HashMap<String,Object>();
				 jsonMap.put("type", 1);
				 jsonMap.put("dealid",orderId);
				 try {
					 MessagePushUtil.SendPush(lsUserList,jsonMap,contentMsg,2);
				} catch (Exception e) {

				}
				
		        //////////////---------推送消息-end--------////////////////////////
				
				
				
				System.out.println("支付宝回调成功"+ orderId);
				return "success";
		} catch (Exception e) {
			return "fail";
		}
	}
	

	
	/**
	 * 加单回调
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws BaseException
	 */
	@RequestMapping("additionalNotifyUrl")
	@ResponseBody
	public String additionalNotifyUrl(HttpServletRequest request) throws UnsupportedEncodingException, BaseException{
		try {
			//获取支付宝POST过来反馈信息
			Map<String,String> params = new HashMap<String,String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
				System.out.println("-----------------------------"+valueStr);
				params.put(name, valueStr);
			}
			
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			//if(AlipayNotify.verify(params)){//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码
			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			
			//int orderId =Integer.valueOf(out_trade_no);
			System.out.println("加单支付订单ID-------------------------："+out_trade_no);
			
			//支付宝请求日志 start
			String content = "out_trade_no:" + new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8")
					+ " | " + "trade_no:" + new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8")
					+ " | " + "trade_status:" + new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8")
					+ " | " + "total_fee:" + new String(request.getParameter("total_fee").getBytes("ISO-8859-1"),"UTF-8");
			
			walletPayService.addBaseAlipaytradelog(out_trade_no, content, Constant.NORMAL_PAYSTATE_SUCCESS);
			
			System.out.println("加单支付请求日志 -------------------------："+out_trade_no);

			//支付宝请求日志 end
			
			OrderinfoModel orderinfo = orderinfoService.searchOrderinfoByAdditionalcode(out_trade_no);
			
			if(trade_status.equals("TRADE_FINISHED")){
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
				//注意：
				//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
				
				if(orderinfo != null){
					//处理支付宝多次回调
					//订单待支付状态下执行更新订单状态
					if(orderinfo.getAdditionalstate() == 1){							
						//支付成功更新加单状态
						if (walletPayService.updateAdditionalInfo(out_trade_no)) {
							System.out.println("加单支付成功 -------------------------："+out_trade_no);
							return "success";
						}else{
							System.out.println("加单支付失败 -------------------------："+out_trade_no);
							return "fail";
						}
					}
				}else{
					return "fail";
				}
			} else if (trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
				//注意：
				//付款完成后，支付宝系统发送该交易状态通知
				if(orderinfo != null){
					//处理支付宝多次回调
					//订单待支付状态下执行更新订单状态
					if(orderinfo.getAdditionalstate() == 1){							
						//支付成功更新加单状态
						if (walletPayService.updateAdditionalInfo(out_trade_no)) {
							System.out.println("加单支付成功 -------------------------："+out_trade_no);
							return "success";
						}else{
							System.out.println("加单支付失败 -------------------------："+out_trade_no);
							return "fail";
						}
					}
				}else{
					return "fail";
				}
			}
			System.out.println("支付宝加单回调成功"+ out_trade_no);
			return "success";

		} catch (Exception e) {
			return "fail";
		}
		
	}
	
	
	/**
	 * 支付宝闪惠回调
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws BaseException
	 */
	@SuppressWarnings("unused")
	@RequestMapping("quickpayNotifyUrl")
	
	@ResponseBody
	public String quickpayNotifyUrl(HttpServletRequest request) throws UnsupportedEncodingException, BaseException{
	try {
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			System.out.println("-----------------------------"+valueStr);
			params.put(name, valueStr);
		}
		
		
		
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
	//			if(AlipayNotify.verify(params)){//验证成功
				//////////////////////////////////////////////////////////////////////////////////////////
				//请在这里加上商户的业务逻辑程序代码
				//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

				
				System.out.println("支付宝优惠支付订单ID-------------------------："+out_trade_no);
				
				//支付宝请求日志 start
				String content = "out_trade_no:" + new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8")
						+ " | " + "trade_no:" + new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8")
						+ " | " + "trade_status:" + new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8")
						+ " | " + "total_fee:" + new String(request.getParameter("total_fee").getBytes("ISO-8859-1"),"UTF-8");
				
				System.out.println("支付宝优惠支付请求日志-------------------------："+content);
				
				walletPayService.addBaseAlipaytradelog(out_trade_no, content, Constant.QUICKPAY_PAYSTATE_SUCCESS);
	
				System.out.println("支付宝优惠支付添加请求日志-------------------------：" + out_trade_no);
				//支付宝请求日志 end
				
	
				UserQuickpayModel userQuickpayModel = userQuickpayService.searchUserQuickpayByCode(out_trade_no);
				int storeid=userQuickpayModel.getStoreid();
				float payamount=userQuickpayModel.getPayamount();
				float customreduce = userQuickpayModel.getCustomreduce();
				String masterName=userQuickpayModel.getMastername();
				float originalPrice;
				if (trade_status.equals("TRADE_SUCCESS")||trade_status.equals("TRADE_FINISHED")){
					//判断该笔订单是否在商户网站中已经做过处理
						//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
						//如果有做过处理，不执行商户的业务程序
					//注意：
					//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
					//付款完成后，支付宝系统发送该交易状态通知
					if(userQuickpayModel != null){
						System.out.println("orderinfo != null");
						//处理支付宝多次回调
						if(userQuickpayModel.getPaystate() != Constant.QUICKPAY_PAYSTATE_SUCCESS){	
							System.out.println("userQuickpayModel.getPaystate() != 1");
							//支付成功更新订单状态
							originalPrice=payamount+customreduce;
							// 更新用户记录支付记录
							userQuickpayService.addUserQuickpayTradelog(userQuickpayModel.getOrderid());
							// 更新商户钱包
							if (userQuickpayService.updateStoreWalletPay(storeid, payamount, userQuickpayModel.getOrderid(), 
									originalPrice,masterName)) {
								return "success";
							}else{
								return "fail";
							}
						}
					}else{
						System.out.println("orderinfo == null");
						return "fail";
					}
				
				}
				System.out.println("支付宝优惠支付回调成功"+ out_trade_no);
				return "success";
		} catch (Exception e) {
			return "fail";
		}
	
	
	}
}
