package com.wenyuankeji.spring.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oruit.weixin.api.PayMchAPI;
import com.oruit.weixin.paymch.bean.MchBaseResult;
import com.oruit.weixin.paymch.bean.MchPayApp;
import com.oruit.weixin.paymch.bean.MchPayNotify;
import com.oruit.weixin.paymch.bean.Unifiedorder;
import com.oruit.weixin.paymch.bean.UnifiedorderResult;
import com.oruit.weixin.util.PayUtil;
import com.oruit.weixin.util.SignatureUtil;
import com.wenyuankeji.spring.model.BaseWechatTradelogModel;
import com.wenyuankeji.spring.model.OrderinfoModel;
import com.wenyuankeji.spring.model.UserQuickpayModel;
import com.wenyuankeji.spring.model.UserTradelogModel;
import com.wenyuankeji.spring.model.UserWalletModel;
import com.wenyuankeji.spring.service.IOrderinfoService;
import com.wenyuankeji.spring.service.IUserQuickpayService;
import com.wenyuankeji.spring.service.IWalletPayService;
import com.wenyuankeji.spring.service.IWechatPayService;
import com.wenyuankeji.spring.util.Constant;
import com.wenyuankeji.spring.util.MessagePushUtil;
import com.wenyuankeji.spring.util.ShunJianMeiUtil;
import com.wenyuankeji.spring.util.XMLConverUtil;

/**
 * 微信支付回调和微信下单
 *
 * @author Liuk
 */
@Controller
public class WeinxinPayController {

    //private static final Logger log = Logger.getLogger(WeinxinPayAction.class);

    @Autowired
   	private	IUserQuickpayService userQuickpayService;
    @Autowired
	private IWechatPayService wechatPayService;
    @Autowired
	private IWalletPayService walletPayService;	
    @Autowired
	private IOrderinfoService orderinfoService;

	@Value("#{configProperties['ipHost']}")
	private String ipHost;
    /**
     * 微信支付回调
     * @param request
     * @param response
     * @throws IOException 
     */
    @SuppressWarnings("unused")
	@RequestMapping("/pay_result")
    public void defaultMethod(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取请求数据
        MchPayNotify payNotify = XMLConverUtil.convertToObject(MchPayNotify.class, request.getInputStream());
        //已处理 去重
        //签名验证
        System.out.println("-------------------微信回调--------------------------------" + payNotify.toString());
        try {
        	if (SignatureUtil.validateAppSignature(payNotify, Constant.key)) {
            	System.out.println("-------------支付回调---微信支付成功----------");
                MchBaseResult baseResult = new MchBaseResult();
                baseResult.setReturn_code("SUCCESS");
                baseResult.setReturn_msg("OK");
                response.getOutputStream().write(XMLConverUtil.convertToXML(baseResult).getBytes());
                
                Date date = new Date();
                
                //订单编号，根据订单长度判断是否为加单
                String orderid = payNotify.getAttach();
                
                //判断是否为加单
                if (!"".equals(orderid) && orderid.length() > 10) {//加单
                	
                	//根据加单编号查询加单
					OrderinfoModel orderinfo = walletPayService.searchOrderinfoByAdditionalcode(orderid);
                	
                	//微信交易日志
                	BaseWechatTradelogModel baseWechatTradelogModel = new BaseWechatTradelogModel();
                    baseWechatTradelogModel.setOrderid(String.valueOf(orderinfo.getOrderid()));
                    baseWechatTradelogModel.setAdditionalcode(orderid);
                    baseWechatTradelogModel.setPayorderid(payNotify.getTransaction_id());
                    baseWechatTradelogModel.setPaytime(date);
                    baseWechatTradelogModel.setCreatetime(date);
                	
					if(orderinfo != null){
						//订单待支付状态下执行更新订单状态
						if(orderinfo.getAdditionalstate() == 1){							
							//支付成功更新加单状态
							if (wechatPayService.updateAdditionalInfo(orderid)) {
								
								baseWechatTradelogModel.setState(1);
								baseWechatTradelogModel.setRemark("微信加单支付成功");
								wechatPayService.addBaseWechatTradelog(baseWechatTradelogModel);
								
								System.out.println("微信加单支付成功 -------------------------："+orderid);
							}else{
								
								baseWechatTradelogModel.setState(0);
								baseWechatTradelogModel.setRemark("微信加单支付失败");
								wechatPayService.addBaseWechatTradelog(baseWechatTradelogModel);
								
								System.out.println("微信加单支付失败1 -------------------------："+orderid);
							}
						}
					}else{
						
						baseWechatTradelogModel.setState(0);
						baseWechatTradelogModel.setRemark("微信加单支付失败");
						wechatPayService.addBaseWechatTradelog(baseWechatTradelogModel);
						
						System.out.println("---------微信加单支付失败2---------------");
					}
    			}else{//订单
    				OrderinfoModel orderinfo = orderinfoService.searchOrderinfo(Integer.parseInt(orderid));
    				
    				//微信交易日志
                	BaseWechatTradelogModel baseWechatTradelogModel = new BaseWechatTradelogModel();
                    baseWechatTradelogModel.setOrderid(orderid);
                    baseWechatTradelogModel.setAdditionalcode(null);
                    baseWechatTradelogModel.setPayorderid(payNotify.getTransaction_id());
                    baseWechatTradelogModel.setPaytime(date);
                    baseWechatTradelogModel.setCreatetime(date);
                    
                	//订单ID
                	if(orderinfo != null){
    					//订单待支付状态下执行更新订单状态
    					if(orderinfo.getPaystate() == 1){	
    						//支付成功更新订单状态
    						if (wechatPayService.updateOrderinfo(Integer.parseInt(orderid))) {
    							
    							baseWechatTradelogModel.setState(1);
								baseWechatTradelogModel.setRemark("微信订单支付成功");
								wechatPayService.addBaseWechatTradelog(baseWechatTradelogModel);
								
								System.out.println("微信订单支付成功 -------------------------："+orderid);
								
								// add by jiazhaohui
								// 预约成功，发送短信
								orderinfoService.SendMobileMessage(orderinfo);
								
								// add by jiazhaohui
								// 微信支付成功的通知
								//////////////---------推送消息-begin--------//////////////////////
								List<String> lsUserList = new ArrayList<String>();
								String contentMsg = "有用户预约啦，快去看看吧~";
								
								// 获取美发师的id
								lsUserList.add(String.valueOf(orderinfo.getUserid()));
								
								Map<String,Object> jsonMap = new HashMap<String,Object>();
								 jsonMap.put("type", 1);
								 jsonMap.put("dealid",orderinfo.getOrderid());
								 try {
									 MessagePushUtil.SendPush(lsUserList,jsonMap,contentMsg,2);
								} catch (Exception e) {
									
								}
						        //////////////---------推送消息-end--------////////////////////////
								
							
							}else{
								
								baseWechatTradelogModel.setState(0);
								baseWechatTradelogModel.setRemark("微信订单支付失败");
								wechatPayService.addBaseWechatTradelog(baseWechatTradelogModel);
								
								System.out.println("微信订单支付失败1 -------------------------："+orderid);
							}
    					}
    				}else{
    					
    					baseWechatTradelogModel.setState(0);
						baseWechatTradelogModel.setRemark("微信订单支付失败");
						wechatPayService.addBaseWechatTradelog(baseWechatTradelogModel);
						
    					System.out.println("------微信订单支付失败2--------");
    				}
    			}
            } else {
            	System.out.println("-------------支付回调---微信支付失败----------");
                MchBaseResult baseResult = new MchBaseResult();
                baseResult.setReturn_code("FAIL");
                baseResult.setReturn_msg("ERROR");
                response.getOutputStream().write(XMLConverUtil.convertToXML(baseResult).getBytes());
            }
		} catch (Exception e) {
			System.out.println("-------------异常----------");
		}
    }
    
    /**
     * 微信闪惠支付回调
     * @param request
     * @param response
     * @throws IOException 
     */

	@RequestMapping("/quickPay_result")
    public void quickPay_resultMethod(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取请求数据
        MchPayNotify payNotify = XMLConverUtil.convertToObject(MchPayNotify.class, request.getInputStream());
        //已处理 去重
        //签名验证
        System.out.println("-------------------优惠支付微信回调--------------------------------" + payNotify.toString());
        try {
        	if (SignatureUtil.validateAppSignature(payNotify, Constant.key)) {
            	System.out.println("------------优惠支付回调---微信支付成功----------");
                MchBaseResult baseResult = new MchBaseResult();
                baseResult.setReturn_code("SUCCESS");
                baseResult.setReturn_msg("OK");
                response.getOutputStream().write(XMLConverUtil.convertToXML(baseResult).getBytes());
                
                Date date = new Date();
                
                //闪惠订单编号
                String code = payNotify.getAttach();
                

				//闪惠订单
				UserQuickpayModel userQuickpayModel = userQuickpayService.searchUserQuickpayByCode(code);
				int storeid = userQuickpayModel.getStoreid();
				float payamount =  userQuickpayModel.getPayamount();
				String masterName=userQuickpayModel.getMastername();
				float customreduce = userQuickpayModel.getCustomreduce();
				//微信交易日志
            	BaseWechatTradelogModel baseWechatTradelogModel = new BaseWechatTradelogModel();
                baseWechatTradelogModel.setOrderid(code);
                baseWechatTradelogModel.setAdditionalcode(null);
                baseWechatTradelogModel.setPayorderid(payNotify.getTransaction_id());
                baseWechatTradelogModel.setPaytime(date);
                baseWechatTradelogModel.setCreatetime(date);
                baseWechatTradelogModel.setQuickpay(Constant.QUICKPAY_PAYSTATE_SUCCESS);
                    
            	//订单ID
            	if(code != null){
            		float originalPrice = payamount + customreduce;
    				//支付成功更新订单状态
    				if (userQuickpayService.updateStoreWalletPay(storeid, payamount, userQuickpayModel.getOrderid(), 
    						originalPrice,masterName)) 
    				{
    					// 更新用户记录支付记录
						userQuickpayService.addUserQuickpayTradelog(userQuickpayModel.getOrderid());
    					baseWechatTradelogModel.setState(1);
						baseWechatTradelogModel.setRemark("微信订单支付成功");
						wechatPayService.addBaseWechatTradelog(baseWechatTradelogModel);
						System.out.println("优惠支付，微信订单支付成功 -------------------------：" + code);
					}
    				else
    				{
    					baseWechatTradelogModel.setState(0);
						baseWechatTradelogModel.setRemark("微信订单支付失败");
						wechatPayService.addBaseWechatTradelog(baseWechatTradelogModel);
								
						System.out.println("优惠支付，微信订单支付失败1 -------------------------："+code);
								
					}
    					
    			}else{
    				baseWechatTradelogModel.setState(0);
					baseWechatTradelogModel.setRemark("微信订单支付失败");
					wechatPayService.addBaseWechatTradelog(baseWechatTradelogModel);
    				System.out.println("------优惠支付，微信订单支付失败2--------");
    					
    			}
            } else {
            	System.out.println("-------------优惠支付回调---微信支付失败----------");
                MchBaseResult baseResult = new MchBaseResult();
                baseResult.setReturn_code("FAIL");
                baseResult.setReturn_msg("ERROR");
                response.getOutputStream().write(XMLConverUtil.convertToXML(baseResult).getBytes());
            }
		} catch (Exception e) {
			System.out.println("-------------异常----------");
		}
    }
}