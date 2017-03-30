package com.wenyuankeji.spring.dao.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IUserCashrecordDao;
import com.wenyuankeji.spring.model.UserCashrecordModel;
import com.wenyuankeji.spring.model.UserCouponsModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class UserCashrecordDaoImpl implements IUserCashrecordDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	// 提现记录
	@Override
	public boolean addUserCashrecord(UserCashrecordModel userCashrecord)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			Serializable isok = session.save(userCashrecord);
			int result = Integer.parseInt(isok.toString());
			if (result > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public Integer addUserCouponsModel(UserCouponsModel o)throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			Integer id = (Integer) session.save(o);
			return id;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	@Override
	public boolean addUserCashrecordEditBalance(
			UserCashrecordModel userCashrecord, float balance, int ownerid, int ownertype)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			Serializable isok = session.save(userCashrecord);
			int result = Integer.parseInt(isok.toString());
			
			StringBuffer hql1 = new StringBuffer();
			hql1.append("UPDATE UserWalletModel SET ");
			hql1.append("balance=? ");
			hql1.append("WHERE ");
			hql1.append("ownerid=? and ownertype=?");
			Query query1 = session.createQuery(hql1.toString());

			query1.setFloat(0, balance);
			query1.setInteger(1, ownerid);
			query1.setInteger(2, ownertype);
			query1.executeUpdate();
			
			//if (isok1 > 0 && result > 0) {
			if (result > 0) {
				return true;
			}
			
			return false;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

}
