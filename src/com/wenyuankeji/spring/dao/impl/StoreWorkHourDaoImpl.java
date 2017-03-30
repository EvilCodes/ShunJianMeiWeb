package com.wenyuankeji.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IStoreWorkHourDao;
import com.wenyuankeji.spring.model.StoreWorkhourModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class StoreWorkHourDaoImpl implements IStoreWorkHourDao{


	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<StoreWorkhourModel> searchStoreHourInfo(int wdId, int hour)  throws BaseException {
		try{
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM StoreWorkhourModel WHERE state=0 and wdid = "+ wdId +" and hour="+ hour;
						
			return (List<StoreWorkhourModel>) session.createQuery(hql).list();
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public boolean updateStoreWorkhour(int orderid, int wdid, int hour, int state) throws BaseException {
		// TODO Auto-generated method stub
			Session session = sessionFactory.getCurrentSession();
			
			try {
				String querySql = "select * from store_workhour where wdid = '"+wdid+"' and hour = '"+hour+"'";	
				Query queryQuery = session.createSQLQuery(querySql);
				@SuppressWarnings("unchecked")
				List<Object[]> objectList = queryQuery.list();
				if(objectList == null || objectList.size() == 0){
					return false;
				}
				
				String sql = "update store_workhour set orderid = '"+orderid+"',state = '"+state+"' where wdid = '"+wdid+"' and hour = '"+hour+"'" + " limit 1";				
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

}
