package com.wenyuankeji.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IUserEvaluatePhotoMappingDao;
import com.wenyuankeji.spring.model.UserEvaluatePhotoMappingModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class UserEvaluatePhotoMappingDaoImpl implements
		IUserEvaluatePhotoMappingDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserEvaluatePhotoMappingModel> searchUserEvaluatePhotoMappingg(
			int evaid) throws BaseException{
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserEvaluatePhotoMappingModel WHERE evaid=? ";
			Query query = session.createQuery(hql);

			query.setInteger(0, evaid);

			List<UserEvaluatePhotoMappingModel> userEvaluateList = null;
			if (null != query.list() && query.list().size() > 0) {
				userEvaluateList = (List<UserEvaluatePhotoMappingModel>) query.list();
			}

			return userEvaluateList;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public boolean addUserEvaluatePhotoMapping(UserEvaluatePhotoMappingModel o)
			throws BaseException {
		try {

			Session session = sessionFactory.getCurrentSession();
			
			String sql = "INSERT INTO user_evaluate_photo_mapping (evaid,picid,createtime) VALUES ('"+o.getEvaid()+"','"+o.getPicid()+"',SYSDATE())";			
			
			Query query = session.createSQLQuery(sql);
			
			if (query.executeUpdate()>0) {
				return true;
			}else {
				return false;
			}

		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public boolean addUserEvaluateScore(int evaid,String evaluate, String score)
			throws BaseException {
		try {

			Session session = sessionFactory.getCurrentSession();
			
			String sql = "INSERT INTO user_evaluate_score (evaid,evaluate,score,createtime) VALUES ('"+evaid+"','"+evaluate+"','"+score+"',SYSDATE())";			
			
			Query query = session.createSQLQuery(sql);
			
			if (query.executeUpdate()>0) {
				return true;
			}else {
				return false;
			}

		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public List<String> searchUserEvaluateicturepathList(int evaid) throws BaseException{
		try {
			Session session = sessionFactory.getCurrentSession();
			
			String sql = "select t2.picturepath from user_evaluate_photo_mapping t1  ";
				   sql += "LEFT JOIN base_picture t2 ON ";
				   sql += "t1.picid = t2.picid ";
				   sql += "where t1.evaid = '"+evaid+"' ";
				   
			Query query = session.createSQLQuery(sql);
			
			@SuppressWarnings("unchecked")
			List<String> servicenameList = query.list();
			
			return servicenameList;
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserEvaluatePhotoMappingModel> searchUserEvaluatePhotoMapping(
			int evaid) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserEvaluatePhotoMappingModel WHERE evaid=? ";
			Query query = session.createQuery(hql);

			query.setInteger(0, evaid);

			List<UserEvaluatePhotoMappingModel> userEvaluatePhotoMappingModels = null;
			if (null != query.list() && query.list().size() > 0) {
				userEvaluatePhotoMappingModels = query.list();
			}

			return userEvaluatePhotoMappingModels;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
	}
}
