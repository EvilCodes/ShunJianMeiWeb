package com.wenyuankeji.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IUserShareRecordDao;
import com.wenyuankeji.spring.model.UserShareRecordModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class UserShareRecordDaoImpl implements IUserShareRecordDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public UserShareRecordModel searchUserShareRecord(int evaid) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserShareRecordModel WHERE evaid=? ";
			Query query = session.createQuery(hql);
			query.setInteger(0, evaid);
			
			UserShareRecordModel userShareRecordModel = null;
			
			@SuppressWarnings("unchecked")
			List<UserShareRecordModel> objects = query.list();
			
			if (null != objects && objects.size() > 0) {
				userShareRecordModel = (UserShareRecordModel) objects.get(0);
			}
			return userShareRecordModel;
		}catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public int addUserShareRecord(UserShareRecordModel o) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();

			String sql = "insert into user_sharerecord (userid,evaid,picid,createtime) "
					+ "values("+o.getUserid()+","+o.getEvaid()+",'"+o.getPicid()+"',SYSDATE())";
			
			Query query = session.createSQLQuery(sql);

			int isok = query.executeUpdate();
			
			return isok;
		}catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	
}
