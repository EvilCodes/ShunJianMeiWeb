package com.wenyuankeji.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IMaterialStationDao;
import com.wenyuankeji.spring.model.MaterialStationModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class MaterialStationDaoImpl implements IMaterialStationDao {


	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	
	@Override
	public List<MaterialStationModel> searchMaterialStation()
			throws BaseException {
		
		try{
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM MaterialStationModel";
			Query query = session.createQuery(hql);

			@SuppressWarnings("unchecked")
			List<MaterialStationModel> materialStation = (List<MaterialStationModel>)query.list();

			return materialStation;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public MaterialStationModel searchMaterialStation(int stationid)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM MaterialStationModel where stationid = ?";
			Query query = session.createQuery(hql);
			query.setInteger(0, stationid);
			@SuppressWarnings("unchecked")
			List<MaterialStationModel> materialStation = (List<MaterialStationModel>) query.list();
			
			if (null != materialStation && materialStation.size() > 0) {
				return materialStation.get(0);
			}
			
			return null;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	

}
