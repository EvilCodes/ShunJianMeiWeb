package com.wenyuankeji.spring.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IUserPhotoMappingDao;
import com.wenyuankeji.spring.model.UserPhotoMappingModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class UserPhotoMappingDaoImpl implements IUserPhotoMappingDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public UserPhotoMappingModel searchUserPhoto(int userid, int type) throws BaseException{
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserPhotoMappingModel WHERE userid=? AND type=? order by sortnum,createtime desc";
			Query query = session.createQuery(hql);

			query.setInteger(0, userid);
			query.setInteger(1, type);

			UserPhotoMappingModel userPhotoMappingModel = null;
			
			@SuppressWarnings("unchecked")
			List<Object> oList = query.list();
			
			if (null != oList && oList.size() > 0) {
				userPhotoMappingModel = (UserPhotoMappingModel) oList.get(0);
			}

			return userPhotoMappingModel;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
	}

	
	@Override
	public UserPhotoMappingModel searchUserPhoto(int userid, int type,int sortnum) throws BaseException{
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserPhotoMappingModel WHERE userid=? AND type=? and sortnum = ? order by sortnum,createtime desc";
			Query query = session.createQuery(hql);

			query.setInteger(0, userid);
			query.setInteger(1, type);
			query.setInteger(2, sortnum);

			UserPhotoMappingModel userPhotoMappingModel = null;
			
			@SuppressWarnings("unchecked")
			List<Object> oList = query.list();
			
			if (null != oList && oList.size() > 0) {
				userPhotoMappingModel = (UserPhotoMappingModel) oList.get(0);
			}

			return userPhotoMappingModel;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
	}
	
	@Override
	public List<UserPhotoMappingModel> searchUserPhotos(int userid,int type)throws BaseException{
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserPhotoMappingModel WHERE userid='"+userid+"' AND type='"+type+"' order by sortnum,createtime desc";
			Query query = session.createQuery(hql);

//			query.setInteger(0, userid);
//			query.setInteger(1, type);

			
			@SuppressWarnings("unchecked")
			List<UserPhotoMappingModel> oList = (List<UserPhotoMappingModel>)query.list();
			
			if (null != oList && oList.size() > 0) {
				return oList;
			}else {
				return null;
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	
	@Override
	public UserPhotoMappingModel addUserPhoto(UserPhotoMappingModel userphoto) throws BaseException{
		try {
			Session session = sessionFactory.getCurrentSession();
			Integer id = (Integer) session.save(userphoto);
			// 查询添加的数据
			//UserPhotoMappingModel userPhotoMappingModel = searchUserPhoto(userphoto.getUserid(), userphoto.getType());
			
			if (id > 0) {
				return userphoto;
			}
			return null;
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
	}
	
	@Override
	public boolean updateUserPhoto(UserPhotoMappingModel userphoto) throws BaseException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(userphoto);
			
			return true;
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
	}
	
	
	@Override
	public boolean addUserPhotoInfo(UserPhotoMappingModel userphoto) throws BaseException{
		Session session = sessionFactory.getCurrentSession();
		try {
			Serializable isok = session.save(userphoto);
			int result = Integer.parseInt(isok.toString());
			return result != 0;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public List<UserPhotoMappingModel> searchUserPhotoList(int userid, int type) throws BaseException{
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserPhotoMappingModel WHERE userid=? AND type=? ";
			Query query = session.createQuery(hql);

			query.setInteger(0, userid);
			query.setInteger(1, type);
			
			@SuppressWarnings("unchecked")
			List<UserPhotoMappingModel> userPhotoMappingList = (List<UserPhotoMappingModel>)query.list();
			return userPhotoMappingList;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
	}

	@Override
	public float searchUserPhotoMax() throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserPhotoMappingModel ORDER BY sortnum DESC";
			Query query = session.createQuery(hql);
			
			UserPhotoMappingModel userPhotoMappingList = (UserPhotoMappingModel)query.list().get(0);
			
			return userPhotoMappingList.getSortnum();
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

}
