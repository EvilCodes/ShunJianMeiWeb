package com.wenyuankeji.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IMaterialDeliveryrecordDao;
import com.wenyuankeji.spring.model.MaterialDeliveryrecordModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class MaterialDeliveryrecordDaoImpl implements
		IMaterialDeliveryrecordDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public MaterialDeliveryrecordModel searchMaterialDeliveryrecordById(
			int recordid) throws BaseException {

		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM MaterialDeliveryrecordModel where recordid = ?";
			Query query = session.createQuery(hql);
			query.setInteger(0, recordid);
			@SuppressWarnings("unchecked")
			List<MaterialDeliveryrecordModel> materialDeliveryrecord = (List<MaterialDeliveryrecordModel>) query
					.list();

			if (null != materialDeliveryrecord
					&& materialDeliveryrecord.size() > 0) {
				return materialDeliveryrecord.get(0);
			}

			return null;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public List<MaterialDeliveryrecordModel> searchmaterialDeliveryrecord(
			int userid) throws BaseException {

		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM MaterialDeliveryrecordModel WHERE userid=? ";
			Query query = session.createQuery(hql);

			query.setInteger(0, userid);

			@SuppressWarnings("unchecked")
			List<MaterialDeliveryrecordModel> materialDeliveryrecord = (List<MaterialDeliveryrecordModel>) query
					.list();

			return materialDeliveryrecord;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	// wm
	@Override
	public List<MaterialDeliveryrecordModel> searchmaterialDeliveryrecord(
			int userid, int state) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM MaterialDeliveryrecordModel WHERE userid = ? and state = ? ";
			Query query = session.createQuery(hql);

			query.setInteger(0, userid);
			query.setInteger(1, state);
			@SuppressWarnings("unchecked")
			List<MaterialDeliveryrecordModel> materialDeliveryrecord = (List<MaterialDeliveryrecordModel>) query
					.list();

			return materialDeliveryrecord;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public List<MaterialDeliveryrecordModel> searchmaterialDeliveryrecord(
			int recordid, int userid, int state) throws BaseException {

		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM MaterialDeliveryrecordModel WHERE state="
					+ state + " and userid=" + userid;
			if (recordid != 0) {
				hql += " and recordid<" + recordid;
			}
			hql += " order by createtime desc ";

			Query query = session.createQuery(hql);

			query.setFirstResult(0);
			query.setMaxResults(10);
			@SuppressWarnings("unchecked")
			List<MaterialDeliveryrecordModel> materialDeliveryrecord = (List<MaterialDeliveryrecordModel>) query
					.list();

			return materialDeliveryrecord;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public boolean updateMaterialDeliveryrecordModel(int recordid)
			throws BaseException {

		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "UPDATE MaterialDeliveryrecordModel SET state=2,deliverytime=SYSDATE() WHERE recordid = ? ";
			Query query = session.createQuery(hql);
			
			query.setInteger(0, recordid);

			int isok = query.executeUpdate();

			if (isok > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}

	}

	@Override
	public int addMaterialDeliveryrecord(MaterialDeliveryrecordModel o)
			throws BaseException {

		try {

			Session session = sessionFactory.getCurrentSession();
			Integer id = (Integer) session.save(o);

			return id;

		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}

	}

}
