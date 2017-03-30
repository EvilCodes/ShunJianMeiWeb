package com.wenyuankeji.spring.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.wenyuankeji.spring.dao.IOrderinfoDao;
import com.wenyuankeji.spring.dao.IStoreinfoDao;
import com.wenyuankeji.spring.dao.IUserTradelogDao;
import com.wenyuankeji.spring.dao.IUserWalletDao;
import com.wenyuankeji.spring.dao.IUsersubinfoDao;
import com.wenyuankeji.spring.model.OrderinfoModel;
import com.wenyuankeji.spring.model.StoreinfoModel;
import com.wenyuankeji.spring.model.UserTradelogModel;
import com.wenyuankeji.spring.model.UserWalletModel;
import com.wenyuankeji.spring.model.UsersubinfoModel;
import com.wenyuankeji.spring.service.IMyTaskService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class MyTaskSerivceImpl implements IMyTaskService {

	@Autowired
	private IUserWalletDao userWalletDao;
	@Autowired
	private IUserTradelogDao userTradelogDao;
	@Autowired
	private IOrderinfoDao orderinfoDao;
	@Autowired
	private IUsersubinfoDao usersubinfoDao;
	@Autowired
	private IStoreinfoDao storeinfoDao;
	
	@Override
	public boolean updateAllocationSet(OrderinfoModel orderinfoModel) throws BaseException {
		//1、更新订单的状态为8订单完成
		//2、钱包分成的逻辑， 根据订单ID查询对应的订单金额，再查询订单明细的物料总额，  订单金额 - 物料总额 = 分成的金额
		//根据订单ID查询对应的美发师和美发店的分成比例，分成比例 * 乘以分成的金额 = 各自入账的金额
		//3、更新美发师和美发店的钱包表。
		//4、插入美发师和美发店的流水记录（状态为3分成）。
		try {
			//修改为已完成
			orderinfoDao.updateOrderinfoPayState(orderinfoModel.getOrderid(), 8);
			
			//获取订单金额
			double total = orderinfoModel.getTotal();
			//获取订单明细物料总额
			float materialidpriceTotal = orderinfoDao.getMaterialidpriceTotal(orderinfoModel.getOrderid());
			
			//分成金额
			float allocationTotal = (float) (total - materialidpriceTotal);
			
			//获取美发师分成
			int userid = orderinfoModel.getUserid();
			UsersubinfoModel usersubinfoModel = usersubinfoDao.searchUsersubinfo(userid);
			//获取订单分成
			int storeid = orderinfoModel.getStoreid();
			StoreinfoModel storeinfoModel = storeinfoDao.searchStoreinfo(storeid);
			
			float allocation = 0;
			float allocation2 = 0;
			if (usersubinfoModel != null && usersubinfoModel.getAllocation() != null) {
				allocation = allocationTotal * Integer.parseInt(usersubinfoModel.getAllocation())/100;
			}
			if (storeinfoModel != null && storeinfoModel.getAllocation() != null) {
				allocation2 = allocationTotal * Integer.parseInt(storeinfoModel.getAllocation())/100 
						+ materialidpriceTotal;
			}
			Date date = new Date();
			
			//修改钱包表
			UserWalletModel userWalletModel = userWalletDao.searchUserWallet(userid,2);
			//if (userWalletModel != null) {
				float allAmount = userWalletModel.getBalance();
				userWalletModel.setBalance(allAmount + allocation);//金额+分成
				userWalletModel.setOwnertype(2);
				userWalletDao.updateUserWalletByUserId(userWalletModel);
				
				//添加交易日志表
				//美发师
				UserTradelogModel userTradelogModel = new UserTradelogModel();
				userTradelogModel.setOrderid(orderinfoModel.getOrderid());
				userTradelogModel.setWalletid(userWalletModel.getWalletid());
				userTradelogModel.setAmount(allocation);
				userTradelogModel.setRemark("美发师分成");
				userTradelogModel.setCreatetime(date);
				userTradelogModel.setLogtype(3);
				userTradelogDao.addUserTradelog(userTradelogModel);
			//}
			
			
			UserWalletModel userWalletModel2 = userWalletDao.searchUserWallet(storeid,3);
			//if (userWalletModel2 != null) {
				float allAmount2 = userWalletModel2.getBalance();
				userWalletModel2.setBalance(allAmount2 + allocation2);//金额+分成
				userWalletModel2.setOwnertype(3);
				userWalletDao.updateUserWalletByUserId(userWalletModel2);
				
				//美发店
				UserTradelogModel userTradelogModel2 = new UserTradelogModel();
				userTradelogModel2.setOrderid(orderinfoModel.getOrderid());
				userTradelogModel2.setWalletid(userWalletModel2.getWalletid());
				userTradelogModel2.setAmount(allocation2);
				userTradelogModel2.setRemark("美发店分成");
				userTradelogModel2.setCreatetime(date);
				userTradelogModel2.setLogtype(3); 
				userTradelogDao.addUserTradelog(userTradelogModel2);
				
			//}
			return true;
		} catch (Exception e) {
			//事务回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return false;
	}

	@Override
	public boolean searchUserTradelogByOrderId(int orderid, int logtype) throws BaseException {
		return userTradelogDao.searchUserTradelogByOrderId(orderid, logtype);
	}
}
