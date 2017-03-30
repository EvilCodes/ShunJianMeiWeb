package com.wenyuankeji.spring.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IUserMyhairpackedDao;
import com.wenyuankeji.spring.model.UserMyhairPackedModel;
import com.wenyuankeji.spring.util.BaseException;
@Repository
public class UserMyhairpackedDaoImpl implements IUserMyhairpackedDao{
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public boolean addUserMyhairpacked(UserMyhairPackedModel myhairPackedModel)throws BaseException  {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.save(myhairPackedModel);
			return true;
		} catch (Exception e) {
				throw new BaseException(e.getMessage());
		}
	}
	@Override
	public boolean addUserMyhairpackedBatch(List<UserMyhairPackedModel> myhairPackedModelList)throws BaseException  {
		Session session = sessionFactory.getCurrentSession();
		try {
			for (int j = 0; j < myhairPackedModelList.size(); j++) {
				 session.save(myhairPackedModelList.get(j)); // 保存对象  
			}
			return true;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	@Override
	public boolean deleteUserMyhairpacked(Integer hairdresserPackedid)throws BaseException  {
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "delete FROM  user_myhair_packed WHERE id="+hairdresserPackedid;
			Query query = session.createSQLQuery(hql);
			query.executeUpdate();
			return true;
		} catch (Exception e) {
				throw new BaseException(e.getMessage());
		}
	}
	@Override
	public boolean deleteUserMyhairpackedBatch(List<String> hairdresserPackedids)throws BaseException  {
		Session session = sessionFactory.getCurrentSession();
		try {
			for (int i = 0; i < hairdresserPackedids.size(); i++) {
				String hql = "delete FROM  user_myhair_packed WHERE id="+hairdresserPackedids.get(i);
				Query query = session.createSQLQuery(hql);
				query.executeUpdate();
			}
			return true;
		} catch (Exception e) {
				throw new BaseException(e.getMessage());
		}
	}
	@Override
	public List<UserMyhairPackedModel> findAllpackedByid(int id) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		UserMyhairPackedModel userMyhairPackedModel=new UserMyhairPackedModel(); 
		
		String hql = "FROM UserMyhairPackedModel WHERE userid=?";
		Query query = session.createQuery(hql);
		query.setInteger(0, id);
		List<UserMyhairPackedModel> list = (ArrayList<UserMyhairPackedModel>)query.list();
		return list;
	}
	@Override
	public UserMyhairPackedModel getUserMyHairPackedModel(int userid,
			int packed_id) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try{
			String hql = "FROM UserMyhairPackedModel where userid = ? and hairpackedid = ?";
			Query query = session.createQuery(hql);
			query.setInteger(0, userid);
			query.setInteger(1, packed_id);
			if(!query.list().isEmpty() && query.list().size() > 0){
				return (UserMyhairPackedModel) query.list().get(0);
			}
			return null;
		}catch(Exception e){
			throw new BaseException(e.getMessage());
		}
	}
	@Override
	public List<UserMyhairPackedModel> searchUserMyHairpacked(
			Integer hairpackedid) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try{
			String hql = "FROM UserMyhairPackedModel where hairpackedid = "+hairpackedid;
			Query query = session.createQuery(hql);
			List<UserMyhairPackedModel> list=query.list();
			return list;
		}catch(Exception e){
			throw new BaseException(e.getMessage());
		}
	}
}
