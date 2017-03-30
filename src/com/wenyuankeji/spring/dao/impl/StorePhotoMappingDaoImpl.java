package com.wenyuankeji.spring.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IStorePhotoMappingDao;
import com.wenyuankeji.spring.model.StorePhotoMappingModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class StorePhotoMappingDaoImpl implements IStorePhotoMappingDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public StorePhotoMappingModel searchStorePhoto(int storeid, int type)throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "FROM StorePhotoMappingModel WHERE storeid=? AND type=? ORDER BY createtime DESC";
			Query query = session.createQuery(hql);

			query.setInteger(0, storeid);
			query.setInteger(1, type);
			@SuppressWarnings("unchecked")
			List<Object> oList = query.list();
			StorePhotoMappingModel storePhotoMappingModel = null;
			if (null != oList && oList.size() > 0) {
				storePhotoMappingModel = (StorePhotoMappingModel) oList.get(0);
			}

			return storePhotoMappingModel;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public List<StorePhotoMappingModel> searchStorePhotoMapping(int storeid,
			int type) throws BaseException{
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "FROM StorePhotoMappingModel WHERE storeid=? AND type=? ";
			Query query = session.createQuery(hql);

			query.setInteger(0, storeid);
			query.setInteger(1, type);
			
			@SuppressWarnings("unchecked")
			List<StorePhotoMappingModel> storePhotoMappingList = (List<StorePhotoMappingModel>)query.list();

			return storePhotoMappingList;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public boolean addStorePhoto(StorePhotoMappingModel storePhotoMappingModel) throws BaseException{
		Session session = sessionFactory.getCurrentSession();
		try {
			Serializable isok = session.save(storePhotoMappingModel);
			int result = Integer.parseInt(isok.toString());
			return result != 0;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	@Override
	public boolean updateStorePhoto(StorePhotoMappingModel storePhotoMappingModel) throws BaseException{
		Session session = sessionFactory.getCurrentSession();
		try {
			session.update(storePhotoMappingModel);
			return true;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
}
