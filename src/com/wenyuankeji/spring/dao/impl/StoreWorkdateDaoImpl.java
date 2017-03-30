package com.wenyuankeji.spring.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IStoreWorkdateDao;
import com.wenyuankeji.spring.model.StoreWorkdateModel;
import com.wenyuankeji.spring.util.BaseException;
@Repository
public class StoreWorkdateDaoImpl implements IStoreWorkdateDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<StoreWorkdateModel> searchStoreWorkdateList(String storeids,
			Map<String, Integer> userWorkdataInfoMap) throws BaseException {
		
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM StoreWorkdateModel WHERE storeid in ("+storeids+") "
					+ "AND year = ? AND month = ? AND day = ? order by storeid ";
			Query query = session.createQuery(hql);
			
			query.setString(0, userWorkdataInfoMap.get("workDataYear").toString());
			query.setString(1, userWorkdataInfoMap.get("workDataMonth").toString());
			query.setString(2, userWorkdataInfoMap.get("workDataDay").toString());
			
			@SuppressWarnings("unchecked")
			List<StoreWorkdateModel> objList = query.list();
			
			if (null != objList && objList.size() > 0) {
				return  objList;
			}
			
			return null;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
	}
	
	@Override
	public List<Integer> searchStoreHourInfo(int wdid,int startHour,int endhour) throws BaseException{
		try {
			Session session = sessionFactory.getCurrentSession();
			
			String sql = "select count(*) from store_workhour where state = 0 "
					+ "and `hour` >= "+startHour+" "
					+ "and `hour` <= "+endhour+" "
					+ "and wdid = "+wdid+" "
					+ "group by chairid order by chairid,`hour`";
			
			Query query = session.createSQLQuery(sql);
			
			@SuppressWarnings("unchecked")
			List<Integer> chairidInfoList = query.list();
			
			return chairidInfoList;
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public List<Integer> searchStoreWorkdateInfo() throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			
			String sql = "SELECT DISTINCT(store_workdate.storeid) FROM store_workdate ";
			sql += " INNER JOIN store_workhour ";
			sql += " ON store_workdate.wdid = store_workhour.wdid ";
			sql += " WHERE store_workhour.state = 0";
			
			Query query = session.createSQLQuery(sql);
			
			@SuppressWarnings("unchecked")
			List<Integer> storeIdList = query.list();
			
			return storeIdList;
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public boolean updateStoreWorkhour(int orderid) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			String sql = "update store_workhour set state=2 where orderid =" + orderid;
			Query query = session.createSQLQuery(sql);
			if (query.executeUpdate() > 0) {
				return true;
			}
			return false;
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	public StoreWorkdateModel searchStoreWorkdate(int storeId,int year,int month,int day ){
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM StoreWorkdateModel WHERE storeid = "+ storeId +" and year="+ year+" and month="+ month + " and day="+ day;
		Query query = session.createQuery(hql);

		StoreWorkdateModel orderinfoModel = null;
		if (null != query.list() && query.list().size() > 0) {
			orderinfoModel = (StoreWorkdateModel) query.list().get(0);
		}
		return orderinfoModel;
		
	}
}
