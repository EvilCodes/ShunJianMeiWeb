package com.wenyuankeji.spring.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IBaseAlipaytradelogDao;
import com.wenyuankeji.spring.dao.IOrderinfoDao;
import com.wenyuankeji.spring.dao.IStoreWorkdateDao;
import com.wenyuankeji.spring.dao.IUserQuickpayDao;
import com.wenyuankeji.spring.dao.IUserTradelogDao;
import com.wenyuankeji.spring.dao.IUserWalletDao;
import com.wenyuankeji.spring.dao.IUserWorkhourDao;
import com.wenyuankeji.spring.model.OrderinfoModel;
import com.wenyuankeji.spring.model.UserTradelogModel;
import com.wenyuankeji.spring.model.UserWalletModel;
import com.wenyuankeji.spring.service.IWalletPayService;
import com.wenyuankeji.spring.util.BaseException;
import com.wenyuankeji.spring.util.Constant;

@Service
public class WalletPayServiceImpl implements IWalletPayService {

	@Autowired
	private IUserWalletDao userWalletDao;
	@Autowired
	private IUserTradelogDao userTradelogDao;
	@Autowired
	private IOrderinfoDao orderinfoDao;
	@Autowired
	private IUserQuickpayDao uerQuickpayDao;
	@Autowired
	private IUserWorkhourDao userWorkhourDao;
	@Autowired
	private IStoreWorkdateDao storeWorkdateDao;
	@Autowired
	private IBaseAlipaytradelogDao baseAlipaytradelogDao;

	@Override
	public UserWalletModel searchUserWallet(int userid,int ownertype) throws BaseException {
		return userWalletDao.searchUserWallet(userid,ownertype);
	}

	@Override
	public boolean updateWalletPay(UserWalletModel userWalletModel,
			UserTradelogModel userTradelogModel) throws BaseException {
		try {
			// 1、修改钱包金额
			if (userWalletDao.updateUserWalletByUserId(userWalletModel)) {
				// 2、修改订单状态
				if (orderinfoDao.updateOrderinfoModel(
						userTradelogModel.getOrderid(), 3)) {
					// 更新订单支付状态
					if (orderinfoDao.updateOrderPayType(
							userTradelogModel.getOrderid(), 3)) {
						// 3、修改美发师工时状态 可约
						if (userWorkhourDao.updateUserWorkhour2(userTradelogModel
								.getOrderid())) {
							// add by jiazhaohui 暂时不考虑工作的概念了
							// 4、修改美发店工位工时状态 可约
//							if (storeWorkdateDao
//									.updateStoreWorkhour(userTradelogModel
//											.getOrderid())) {
//
//							}
							// 5、添加交易日志
							if (userTradelogDao
									.addUserTradelog(userTradelogModel)) {

							}
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
	public OrderinfoModel searchOrderinfoByAdditionalcode(String additionalcode)
			throws BaseException {
		return orderinfoDao.searchOrderinfoByAdditionalcode(additionalcode);
	}

	@Override
	public boolean updateWalletPay(UserWalletModel userWalletModel,
			UserTradelogModel userTradelogModel, String additionalcode)
			throws BaseException {
		try {
			// 1、修改钱包金额
			if (userWalletDao.updateUserWalletByUserId(userWalletModel)) {
				// 2、修改加单状态
				if (orderinfoDao.updateAdditionalInfo(additionalcode)) {
					// 修改加单支付类型
					if(orderinfoDao.updateAdditionalPaytype(additionalcode, 3)){
						// 3、添加交易日志
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
	public boolean updateOrderinfo(int orderId) throws BaseException{
		try {
			// 更新订单
			if (orderinfoDao.updateOrderinfoModel(orderId, 3)) {
				// 更新订单支付方式
				if (orderinfoDao.updateOrderPayType(orderId, 2)) {
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
							userTradelogModel.setRemark("支付宝支付");
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
				if(orderinfoDao.updateAdditionalPaytype(additionalcode, 2)){
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
					userTradelogModel.setRemark("支付宝加单支付");
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

	@Override
	public boolean addBaseAlipaytradelog(String orderid, String content, int quickpay) throws BaseException{
		try {
			return baseAlipaytradelogDao.addBaseAlipaytradelog(orderid, content, quickpay);
		} catch (BaseException e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public boolean updateWalletQuickPay(UserWalletModel userWalletModel) throws BaseException {
		try {
			// 1、修改钱包金额
			if (userWalletDao.updateUserWalletByUserId(userWalletModel)) {
				return true;
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		return false;
	}

}
