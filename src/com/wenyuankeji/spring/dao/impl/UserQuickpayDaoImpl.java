package com.wenyuankeji.spring.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IUserQuickpayDao;
import com.wenyuankeji.spring.dao.IUserinfoDao;
import com.wenyuankeji.spring.model.UserQuicklogSearchModel;
import com.wenyuankeji.spring.model.UserQuickpayModel;
import com.wenyuankeji.spring.model.UserTradelogSearchModel;
import com.wenyuankeji.spring.model.UserinfoModel;
import com.wenyuankeji.spring.util.BaseException;
import com.wenyuankeji.spring.util.Constant;
import com.wenyuankeji.spring.util.ShunJianMeiUtil;

@Repository
public class UserQuickpayDaoImpl implements IUserQuickpayDao 
{
	@Autowired
	private IUserinfoDao userinfoDao;
	
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Override
	public int addQuickpayOrder(UserQuickpayModel userQuickpayModel)
			throws BaseException {
		int paymentid = 0;
		try {
			Session session = sessionFactory.getCurrentSession();
			paymentid = (Integer) session.save(userQuickpayModel);
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		return paymentid;
	}

	@Override
	public boolean updateUserQuickpayWithPaystate(int paymentid,int paystate)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			UserQuickpayModel uqpModel = searchUserQuickpayInfo(paymentid);
			if (uqpModel != null)
			{
				uqpModel.setPaystate(paystate);
				session.update(uqpModel);
				return true;
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean updateUserQuickpayWithOrdercode(int paymentid,String ordercode)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			UserQuickpayModel uqpModel = searchUserQuickpayInfo(paymentid);
			if (uqpModel != null)
			{
				uqpModel.setOrdercode(ordercode);
				session.update(uqpModel);
				return true;
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
		return false;
	}

	@Override
	public UserQuickpayModel searchUserQuickpayInfo(int paymentid)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserQuickpayModel WHERE paymentid=?";
			Query query = session.createQuery(hql);
			query.setInteger(0, paymentid);
			UserQuickpayModel userQuickpayModel = null;
			if (null != query.list() && query.list().size() > 0) {
				userQuickpayModel = (UserQuickpayModel) query.list().get(0);
			}
			return userQuickpayModel;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	/**
	 * 根据code查询闪惠订单信息
	 * @param paymentid
	 * @return UserQuickpayModel
	 */
	@Override
	public UserQuickpayModel searchUserQuickpayByCode(String code) throws BaseException
	{
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserQuickpayModel WHERE ordercode=?";
			Query query = session.createQuery(hql);
			query.setString(0, code);
			UserQuickpayModel userQuickpayModel = null;
			if (null != query.list() && query.list().size() > 0) {
				userQuickpayModel = (UserQuickpayModel) query.list().get(0);
			}
			return userQuickpayModel;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public int searchUserQuickTotalByStoreID(int storeid) throws BaseException{
		Session session = sessionFactory.getCurrentSession()  ;
		try {
			String hql="FROM UserQuickpayModel WHERE storeid=" + storeid +" and paystate = "+Constant.QUICKPAY_PAYSTATE_SUCCESS;
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<Object[]> objList = (List<Object[]>) query.list();
			if(objList != null && objList.size() > 0){
				return objList.size();
			}else{
				return 0;
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public List<UserQuicklogSearchModel> searchUserQuick(int storeid, int intPage,
			int intRows) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql="FROM UserQuickpayModel WHERE storeid=" + storeid +" and paystate = "+Constant.QUICKPAY_PAYSTATE_SUCCESS;
			Query query = session.createQuery(hql);
			
			query.setFirstResult((intPage - 1) * intRows);
			query.setMaxResults(intRows);
			
			@SuppressWarnings("unchecked")
			List<UserQuickpayModel> quickpayModelList = (List<UserQuickpayModel>) query.list();
			
			List<UserQuicklogSearchModel> searchModelList = new ArrayList<UserQuicklogSearchModel>();
			for (UserQuickpayModel quickpayModel  : quickpayModelList) {
				int userID=quickpayModel.getUserid();
				UserinfoModel userinfoModel=userinfoDao.searchUserinfoById(userID);
				String username=userinfoModel.getUsername();
				UserQuicklogSearchModel searchModel=new UserQuicklogSearchModel();
				searchModel.setUsername(username);
				searchModel.setOrdercode(quickpayModel.getOrdercode().toString());
				searchModel.setPayamount(String.valueOf(quickpayModel.getPayamount()));
				searchModel.setCreatetime(quickpayModel.getCreatetime().toString().substring(0, quickpayModel.getCreatetime().toString().length() - 2));
				searchModel.setMastername(quickpayModel.getMastername().toString());
				searchModel.setCustomreduce(String.valueOf(quickpayModel.getCustomreduce()));
				searchModel.setStorereduce(String.valueOf(quickpayModel.getStorereduce()));
				searchModel.setPayTotal(String.valueOf(quickpayModel.getPayamount()+quickpayModel.getStorereduce()));
				searchModelList.add(searchModel);
			}
			return searchModelList;
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
	}
}
