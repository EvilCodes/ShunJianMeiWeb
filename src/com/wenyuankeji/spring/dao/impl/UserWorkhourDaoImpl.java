package com.wenyuankeji.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IUserWorkhourDao;
import com.wenyuankeji.spring.model.UserWorkhourModel;
import com.wenyuankeji.spring.util.BaseException;
import com.wenyuankeji.spring.util.Constant;

@Repository
public class UserWorkhourDaoImpl implements IUserWorkhourDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public boolean updateUserWorkhour(int orderid,int state) throws BaseException{
		Session session = sessionFactory.getCurrentSession();
		
		try {
			String sql = "update user_workhour set state = '"+state+"',ordertype='"+Constant.HOUR_ORDER_BY_PLATFORM+"' where orderid = '"+orderid+"'";
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
	
	
	@Override
	public UserWorkhourModel searchUserWorkhour(int orderid,int wdid,int hour) throws BaseException{
		Session session = sessionFactory.getCurrentSession();
		
		try {
			
			String hql = "FROM UserWorkhourModel WHERE wdid=? and hour=? ";
			
			Query query = session.createQuery(hql);
			query.setInteger(0, wdid);
			query.setInteger(1, hour);
			
			@SuppressWarnings("unchecked")
			List<UserWorkhourModel> lists = query.list();
			
			if (null != lists && lists.size() > 0) {
				return lists.get(0);
			}
			return null;
			
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
	}
	
	@Override
	public boolean updateUserWorkhour(int orderid,int wdid,int hour,int state,
			int ordertype) throws BaseException
	{
		Session session = sessionFactory.getCurrentSession();
		try {
			String sql = "update user_workhour set orderid = "+orderid+", state = "+
				state+ ", ordertype = " + ordertype + 
				" where wdid = " + wdid + " and hour = " + hour + "";
			
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
	
	@Override
	public boolean updateUserWorkhour(int orderid,int wdid,int hour,int state) throws BaseException{
		Session session = sessionFactory.getCurrentSession();
		
		try {
			String sql = "update user_workhour set orderid = '"+orderid+"',state = '"+state+"' where wdid = '"+wdid+"' and hour = '"+hour+"'";
			
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
	
	@Override
	public UserWorkhourModel searchUserWorkhour(int whid) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		UserWorkhourModel userWorkhourModel = null;
		
		try {
			String hql = "FROM UserWorkhourModel WHERE whid=? ";
			Query query = session.createQuery(hql);
			query.setInteger(0, whid);
			
			@SuppressWarnings("unchecked")
			List<Object> objList = query.list();
			
			if (null != objList && objList.size() > 0) {
				userWorkhourModel = (UserWorkhourModel) objList.get(0);
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
		return userWorkhourModel;
	}
	
	@Override
	public List<UserWorkhourModel> searchUserWorkhourByWdid(int wdid) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		
		try {
			String hql = "FROM UserWorkhourModel WHERE wdid=? order by hour ";
			Query query = session.createQuery(hql);
			query.setInteger(0, wdid);
			
			@SuppressWarnings("unchecked")
			List<UserWorkhourModel> objList = query.list();
			
			if (null != objList && objList.size() > 0) {
				return objList;
			}
			return null;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
	}

	@Override
	public boolean addUserWorkhour(UserWorkhourModel o) throws BaseException{
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			String sql = "INSERT INTO user_workhour (wdid,hour,state,createtime,orderid) "
					+ " VALUES('"+o.getWdid()+"','"+o.getHour()+"','"+o.getState()+"',SYSDATE(),0)";
			
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
	public boolean updateUserWorkhourState(UserWorkhourModel o) throws BaseException{
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			String sql = "update user_workhour set state = '"+o.getState()+"' where hour = '"+o.getHour()+"' and wdid = '"+o.getWdid()+"'  ";
			
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
	public boolean deleteUserWorkhourByWdid(int wdid) throws BaseException{
		
		Session session = sessionFactory.getCurrentSession();
		try {
			
			String delsql = "delete from user_workhour where wdid = '"+wdid+"' ";
			
			Query query = session.createSQLQuery(delsql);
			
			if (query.executeUpdate() > 0) {
				return true;
			}
			
			return false;
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	
	@Override
	public List<UserWorkhourModel> searchUserWorkhourByWdidAndState(int wdid) throws BaseException{
		Session session = sessionFactory.getCurrentSession();
		
		try {
			String hql = "FROM UserWorkhourModel WHERE  wdid=? ";
			Query query = session.createQuery(hql);
			query.setInteger(0, wdid);
			
			@SuppressWarnings("unchecked")
			List<UserWorkhourModel> objList = query.list();
			
			if (null != objList && objList.size() > 0) {
				return objList;
			}
			return null;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
	}

	@Override
	public boolean updateUserWorkhour(int orderid) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			String sql = "update user_workhour set state=0,orderid=0 where orderid =" + orderid;
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
	public boolean updateUserWorkhour2(int orderid) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			String sql = "update user_workhour set state=2 where orderid =" + orderid;
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
	public boolean updateStoreWorkhour(int orderid) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			String sql = "update store_workhour set state=0,orderid=0 where orderid =" + orderid;
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
	public boolean getUserWorkHourState(int wdid) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "FROM UserWorkhourModel WHERE wdid=? AND state=-1 ";
			Query query = session.createQuery(hql);
			query.setInteger(0, wdid);
			
			if (query.list().size() == 12) {
				return true;
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		return false;
	}
	
	/**
	 * 删除自由编辑的日程订单
	 * @param orderid 订单ID
	 * @return
	 * @throws BaseException
	 */
	@Override
	public boolean updateUserEditWorkDelete(int orderid) throws BaseException
	{
		Session session = sessionFactory.getCurrentSession();
		try {
			String sql = "update user_workhour set state = '"
					+ Constant.HOUR_REST_STATE +"',ordertype= '"+Constant.HOUR_ORDER_BY_PLATFORM +
					"' where orderid = '" + orderid +"' and ordertype = '"
					+ Constant.HOUR_ORDER_BY_EDIT_WORK +"'  ";
			
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
