package com.wenyuankeji.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IUserWorkplaceDao;
import com.wenyuankeji.spring.model.UserWorkdateModel;
import com.wenyuankeji.spring.model.UserWorkhourModel;
import com.wenyuankeji.spring.model.UserWorkplaceModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class UserWorkplaceDaoImpl implements IUserWorkplaceDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Override
	public UserWorkdateModel searchUserWorkdate(int userid) throws BaseException{
		Session session = sessionFactory.getCurrentSession();
		UserWorkdateModel userWorkdateModel = null;
		
		try {
			String hql = "FROM UserWorkdateModel WHERE userid=? ";
			Query query = session.createQuery(hql);
			query.setInteger(0, userid);
			
			@SuppressWarnings("unchecked")
			List<Object> objList = query.list();
			
			if (null != objList && objList.size() > 0) {
				userWorkdateModel = (UserWorkdateModel) objList.get(0);
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
		return userWorkdateModel;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserWorkhourModel> searchUserWorkhour(int wdid)
			throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		List<UserWorkhourModel> userWorkhourModels = null;
		try {
			String hql = "FROM UserWorkhourModel WHERE wdid=? ";
			Query query = session.createQuery(hql);
			query.setInteger(0, wdid);
			
			userWorkhourModels = query.list();
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
		return userWorkhourModels;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserWorkplaceModel> searchUserWorkplaces(int storeid)
			throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		List<UserWorkplaceModel> userWorkplaceModels = null;
		
		try {
			String hql = "FROM UserWorkplaceModel WHERE storeid=? and DATE_FORMAT(CONCAT(userWorkdate.year,case when userWorkdate.month < 10 then CONCAT('0',userWorkdate.month) else userWorkdate.month end,case when userWorkdate.day < 10 then CONCAT('0',userWorkdate.day) else userWorkdate.day end),'%Y%m%d') >= DATE_FORMAT(SYSDATE(),'%Y%m%d') ORDER BY userWorkdate.year,userWorkdate.month,userWorkdate.day ASC";
			Query query = session.createQuery(hql);
			query.setInteger(0, storeid);
			
			
			if (null != query.list() && query.list().size() > 0) {
				userWorkplaceModels = query.list();
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
		return userWorkplaceModels;
	}
	
	
	@Override
	public boolean updateStoreWorkhour(int orderid,int state) throws BaseException{
		Session session = sessionFactory.getCurrentSession();
		
		try {
			String sql = "update store_workhour set state = '"+state+"' where orderid = '"+orderid+"'";
			Query query = session.createSQLQuery(sql);
			
			int id = query.executeUpdate();
			
			if (id > 0) {
				return true;
			}
			
			return false;
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserWorkplaceModel> searchUserWorkplace(int wdid)throws BaseException{
		Session session = sessionFactory.getCurrentSession();
		List<UserWorkplaceModel> userWorkplaceModels = null;
		try {
			String hql = "FROM UserWorkplaceModel WHERE wdid=? ";
			Query query = session.createQuery(hql);
			query.setInteger(0, wdid);
			
			userWorkplaceModels = query.list();
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
		return userWorkplaceModels;
	}

	@Override
	public boolean addUserWorkplace(UserWorkplaceModel o) throws BaseException {
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			String sql = "INSERT INTO user_workplace (wdid,storeid,createtime) "
					+ " VALUES('"+o.getWdid()+"','"+o.getStoreid()+"',SYSDATE())";
			
			Query query = session.createSQLQuery(sql);
			
			if (query.executeUpdate() > 0) {
				return true;
			}
			
			return false;
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	
	@Override
	public boolean deleteUserWorkplaceByWdid(int wdid) throws BaseException {
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			String sql = "delete from user_workplace where wdid = '"+wdid+"'";
			
			Query query = session.createSQLQuery(sql);
			
			if (query.executeUpdate() > 0) {
				return true;
			}
			
			return false;
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
	}
	
}
