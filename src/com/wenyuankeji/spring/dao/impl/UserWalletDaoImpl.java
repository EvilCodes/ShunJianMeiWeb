package com.wenyuankeji.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IUserWalletDao;
import com.wenyuankeji.spring.model.UserWalletModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class UserWalletDaoImpl implements IUserWalletDao {

	
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public UserWalletModel searchUserWallet(int userid,int ownertype) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserWalletModel WHERE ownerid=? and ownertype = ? ";
			Query query = session.createQuery(hql);
			query.setInteger(0, userid);
			query.setInteger(1, ownertype);
			
			UserWalletModel userWalletModel = null;
			
			@SuppressWarnings("unchecked")
			List<Object> objects = query.list();
			
			if (null != objects && objects.size() > 0) {
				userWalletModel = (UserWalletModel) objects.get(0);
			}
			return userWalletModel;
		}catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public boolean updateUserWalletByUserId(UserWalletModel userWalletModel) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "UPDATE UserWalletModel SET balance = ? , updatetime = SYSDATE() WHERE ownerid = ? and ownertype = ?";
			Query query = session.createQuery(hql);
			query.setFloat(0, userWalletModel.getBalance());
			query.setInteger(1, userWalletModel.getOwnerid());
			query.setInteger(2, userWalletModel.getOwnertype());
			return query.executeUpdate() > 0;
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	@Override
	public UserWalletModel addUserWallet(UserWalletModel o) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			 session.save(o);
			return o;
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
 			
		}
	}
	
}
