package com.wenyuankeji.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IUserCouponsDao;
import com.wenyuankeji.spring.model.UserCouponsModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class UserCouponsDaoImpl implements IUserCouponsDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public List<UserCouponsModel> searchUserCoupons(int userid)
			throws BaseException {

		try {
			Session session = sessionFactory.getCurrentSession();

			String hql = "FROM UserCouponsModel WHERE userid=? and ifnull(orderid,0)=0 and coupons.deleted = 0";
			Query query = session.createQuery(hql);

			query.setInteger(0, userid);

			@SuppressWarnings("unchecked")
			List<UserCouponsModel> userCouponsModel = (List<UserCouponsModel>) query
					.list();
			return userCouponsModel;

		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}

	}

	
	@Override
	public UserCouponsModel searchUserCouponsByID(int id)
			throws BaseException {

		try {
			Session session = sessionFactory.getCurrentSession();

			String hql = "FROM UserCouponsModel WHERE id=? ";
			Query query = session.createQuery(hql);

			query.setInteger(0, id);

			@SuppressWarnings("unchecked")
			List<UserCouponsModel> userCouponsModel = (List<UserCouponsModel>) query
					.list();
			if (userCouponsModel != null && userCouponsModel.size() > 0) {
				return userCouponsModel.get(0);
			}
			return null;

		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}

	}
	
	@Override
	public int addUserCoupons(UserCouponsModel o) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();

			//int id = (Integer) session.save(o);
			
			String sql = "insert into user_coupons (userid,couponid,orderid,mobile,createtime,source) "
					+ "values('"+o.getUserid()+"','"+o.getCouponid()+"','"+o.getOrderid()+"','"+o.getMobile()+"',SYSDATE(),'"+o.getSource()+"')";
			
			Query query = session.createSQLQuery(sql);

			int isok = query.executeUpdate();
			
			return isok;
			
		} catch (Exception e) {
 			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public int searchUserCouponsCount(int userid) throws BaseException {
		int count = 0;
		try {
			Session session = sessionFactory.getCurrentSession();
			String sql = "FROM UserCouponsModel where userid=? and ifnull(orderid,0)=0";
			Query query = session.createQuery(sql);
			query.setInteger(0, userid);
			
			if(query.list() != null && query.list().size() > 0){
				count = query.list().size();
			}
		}catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		return count;
	}

	@Override
	public List<UserCouponsModel> searchUserCoupons(int userid, int pageSize,
			int pageIndex) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();

			String hql = "FROM UserCouponsModel WHERE userid=? and ifnull(orderid,0)=0";
			Query query = session.createQuery(hql);

			query.setInteger(0, userid);
			query.setFirstResult((pageIndex - 1) * pageSize);
			query.setMaxResults(pageSize);
			
			@SuppressWarnings("unchecked")
			List<UserCouponsModel> userCouponsModel = (List<UserCouponsModel>) query
					.list();
			return userCouponsModel;

		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserCouponsModel> searchUserCoupon(int orderid) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();

			String hql = "FROM UserCouponsModel WHERE orderid=?";
			Query query = session.createQuery(hql);

			query.setInteger(0, orderid);

			if (query.list() != null && query.list().size() > 0) {
				return (List<UserCouponsModel>) query.list();
			}
			return null;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public UserCouponsModel searchUserCoupons(String mobile, int source) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();

			String hql = "FROM UserCouponsModel WHERE mobile=? and source=?";
			Query query = session.createQuery(hql);

			query.setString(0, mobile);
			query.setInteger(1, source);

			if (query.list() != null && query.list().size() > 0) {
				return (UserCouponsModel) query.list().get(0);
			}
			return null;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	@Override
	public boolean updateUserCoupons(int orderId) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();

			String hql = "update UserCouponsModel set orderId = 0 WHERE orderId="+orderId;
			Query query = session.createQuery(hql);
			return query.executeUpdate()>0;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	
	@Override
	public boolean updateUserCoupons(int id,int orderId) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();

			String hql = "update UserCouponsModel set orderId = '"+orderId+"' WHERE id="+id;
			Query query = session.createQuery(hql);
			return query.executeUpdate()>0;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	@Override
	public UserCouponsModel searchCoupons(String orderid, String servicecode) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			
			String hql = "From UserCouponsModel where orderid = ? and coupons.servicecode = ?";
			
			Query query = session.createQuery(hql);
			
			query.setString(0, orderid);
			query.setString(1, servicecode);
			 
			UserCouponsModel userCouponsModel = null;
			if (query.list() != null && query.list().size() > 0) {
				userCouponsModel = (UserCouponsModel) query.list().get(0);
			}
			return userCouponsModel;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public UserCouponsModel searchCoupons(int couponid, int userid) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();

			String hql = "FROM UserCouponsModel WHERE couponid=? and userid=? and ifnull(orderid,0)=0";
			Query query = session.createQuery(hql);

			query.setInteger(0, couponid);
			query.setInteger(1, userid);

			@SuppressWarnings("unchecked")
			List<UserCouponsModel> userCouponsModel = (List<UserCouponsModel>) query
					.list();
			if (userCouponsModel != null && userCouponsModel.size() > 0) {
				return userCouponsModel.get(0);
			}
			return null;

		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public boolean updateUserCoupons(String mobile, int userid) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();

			String hql = "update UserCouponsModel set userid = ? WHERE mobile = ?";
			Query query = session.createQuery(hql);
			query.setInteger(0, userid);
			query.setString(1, mobile);
			return query.executeUpdate() > 0;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
}
