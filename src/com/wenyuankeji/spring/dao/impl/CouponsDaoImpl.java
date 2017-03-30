package com.wenyuankeji.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.ICouponsDao;
import com.wenyuankeji.spring.model.CouponsModel;
import com.wenyuankeji.spring.model.UserCouponsModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class CouponsDaoImpl implements ICouponsDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public UserCouponsModel searchCoupons(int userid, String servicecode)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			
			String hql = "From UserCouponsModel where userid = ? and ifnull(orderid,0)=0 and coupons.endtime >= SYSDATE() "
					+ " and coupons.servicecode = ? order by coupons.amount desc";
			
			Query query = session.createQuery(hql);
			
			query.setInteger(0, userid);
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
	public List<CouponsModel> searchCoupons() throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM CouponsModel WHERE deleted=0 ";
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<CouponsModel> couponsModel = query.list();
			return couponsModel;
		}catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public CouponsModel searchCouponByID(String couponID)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM CouponsModel where couponid = ?";
			Query query = session.createQuery(hql);
			
			query.setString(0, couponID);
			
			@SuppressWarnings("unchecked")
			List<CouponsModel> couponsModel = query.list();
			
			if (null != couponsModel && couponsModel.size() > 0) {
				return couponsModel.get(0);
			}
			
			return null;
		}catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public int addCoupons(CouponsModel o) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			Integer id = (Integer) session.save(o);
			return id;
		}catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public List<CouponsModel> searchCouponsByOrderPay(double amount)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM CouponsModel WHERE deleted=0 ";
			
			hql += " AND starttime <= SYSDATE()";
			hql += " AND endtime >= SYSDATE()";
			hql += " AND usecondition <= " + amount;
			
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<CouponsModel> couponsModel = query.list();
			return couponsModel;
		}catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
}
