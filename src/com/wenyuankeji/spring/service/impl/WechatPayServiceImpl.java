package com.wenyuankeji.spring.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wenyuankeji.spring.dao.IBaseWechatTradelogDao;
import com.wenyuankeji.spring.dao.IOrderinfoDao;
import com.wenyuankeji.spring.dao.IStoreWorkdateDao;
import com.wenyuankeji.spring.dao.IUserTradelogDao;
import com.wenyuankeji.spring.dao.IUserWalletDao;
import com.wenyuankeji.spring.dao.IUserWorkhourDao;
import com.wenyuankeji.spring.model.BaseWechatTradelogModel;
import com.wenyuankeji.spring.model.OrderinfoModel;
import com.wenyuankeji.spring.model.UserTradelogModel;
import com.wenyuankeji.spring.model.UserWalletModel;
import com.wenyuankeji.spring.service.IWechatPayService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class WechatPayServiceImpl implements IWechatPayService {

	@Autowired
	private IUserWalletDao userWalletDao;
	@Autowired
	private IUserTradelogDao userTradelogDao;
	@Autowired
	private IOrderinfoDao orderinfoDao;
	@Autowired
	private IUserWorkhourDao userWorkhourDao;
	@Autowired
	private IStoreWorkdateDao storeWorkdateDao;
	@Autowired
	private IBaseWechatTradelogDao baseWechatTradelogDao;
	
	@Override
	public boolean addBaseWechatTradelog(BaseWechatTradelogModel o) throws BaseException {
		
		return baseWechatTradelogDao.addBaseWechatTradelog(o);
	}

	@Override
	public boolean updateOrderinfo(int orderId) throws BaseException{
		try {
			// 更新订单
			if (orderinfoDao.updateOrderinfoModel(orderId, 3)) {
				// 更新订单支付方式
				if (orderinfoDao.updateOrderPayType(orderId, 1)) {
					// 更新工时
					if (userWorkhourDao.updateUserWorkhour2(orderId)) {
							// 添加支付记录
							UserTradelogModel userTradelogModel = new UserTradelogModel();
							//查询订单表
							OrderinfoModel orderinfoModel = orderinfoDao.searchOrderinfo(orderId);
							//获取钱包ID
							UserWalletModel userWalletModel = userWalletDao.searchUserWallet(orderinfoModel.getCustomerid(),1);//查询钱包ID
							if (userWalletModel != null) {
								userTradelogModel.setWalletid(userWalletModel.getWalletid());
							}
							userTradelogModel.setOrderid(orderId);
							userTradelogModel.setAmount((float) orderinfoModel.getAmount());
							userTradelogModel.setLogtype(1);
							userTradelogModel.setRemark("微信支付订单");
							userTradelogModel.setCreatetime(new Date());
							if (userTradelogDao.addUserTradelog(userTradelogModel)) {
								return true;
							}
					}
				}
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean updateAdditionalInfo(String additionalcode) throws BaseException{
		try {
			// 更新订单
			if (orderinfoDao.updateAdditionalInfo(additionalcode)) {
				// 更新加单支付类型
				if(orderinfoDao.updateAdditionalPaytype(additionalcode, 1)){
					// 添加支付记录
					UserTradelogModel userTradelogModel = new UserTradelogModel();
					// 根据加单编号查询订单信息
					OrderinfoModel orderinfoModel = orderinfoDao
							.searchOrderinfoByAdditionalcode(additionalcode);
					//获取钱包ID
					UserWalletModel userWalletModel = userWalletDao.searchUserWallet(orderinfoModel.getCustomerid(),1);//查询钱包ID
					if (userWalletModel != null) {
						userTradelogModel.setWalletid(userWalletModel.getWalletid());
					}
					userTradelogModel.setOrderid(orderinfoModel.getOrderid());
					String additionalamount = String.valueOf(orderinfoModel
							.getAdditionalamount());
					userTradelogModel.setAmount(Float.parseFloat(additionalamount));
					userTradelogModel.setCreatetime(new Date());
					userTradelogModel.setLogtype(2);
					userTradelogModel.setRemark("微信加单支付");
					if (userTradelogDao.addUserTradelog(userTradelogModel)) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		return false;
	}

}
