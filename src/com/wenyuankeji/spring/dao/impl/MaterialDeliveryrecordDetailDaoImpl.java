package com.wenyuankeji.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IMaterialDeliveryrecordDetailDao;
import com.wenyuankeji.spring.model.MaterialDeliveryrecordDetailModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class MaterialDeliveryrecordDetailDaoImpl implements
		IMaterialDeliveryrecordDetailDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public List<MaterialDeliveryrecordDetailModel> searchmaterialDeliveryrecordDetail(
			int recordid) throws BaseException {

		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM MaterialDeliveryrecordDetailModel WHERE recordid=? ";
			Query query = session.createQuery(hql);

			query.setInteger(0, recordid);

			@SuppressWarnings("unchecked")
			List<MaterialDeliveryrecordDetailModel> materialDeliveryrecordDetail = (List<MaterialDeliveryrecordDetailModel>) query
					.list();

			return materialDeliveryrecordDetail;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public int addMaterialDeliveryrecordDetail(
			MaterialDeliveryrecordDetailModel o) throws BaseException {
		
		try {
			
			Session session = sessionFactory.getCurrentSession();
			
			Integer id = (Integer) session.save(o);
			
			return id;
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
	}

}
