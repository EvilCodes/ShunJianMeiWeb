package com.wenyuankeji.spring.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IUserHairpackedItemDao;
import com.wenyuankeji.spring.model.UserHairpackedItemModel;
import com.wenyuankeji.spring.util.BaseException;
@Repository
public class UserHairpackedItemDaoImpl implements IUserHairpackedItemDao {
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public boolean addHairpackedItem(UserHairpackedItemModel hairpackedItemModels)
			throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.save(hairpackedItemModels);
			return true;
		} catch (Exception e) {
				throw new BaseException(e.getMessage());
		}
	}
	@Override
	public boolean addHairpackedItemBatch(
			List<UserHairpackedItemModel> hairpackedItemModels)
			throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			for (int j = 0; j < hairpackedItemModels.size(); j++) {
				 session.save(hairpackedItemModels.get(j)); // 保存药品对象  
			}
			return true;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	@Override
	public boolean updateHairpackedItemBatch(
			List<UserHairpackedItemModel> hairpackedItemModels)
			throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.update(hairpackedItemModels);
			return true;
		} catch (Exception e) {
				throw new BaseException(e.getMessage());
		}
	}
	@Override
	public List<UserHairpackedItemModel> searchUserHairpackedItem(
			Integer hairpackedid) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try{
			String hql = "FROM UserHairpackedItemModel where hairpackedid = "+hairpackedid;
			Query query = session.createQuery(hql);
			List<UserHairpackedItemModel> list= query.list();
			return list;
		}catch(Exception e){
			throw new BaseException(e.getMessage());
		}
	}
	@Override
	public List<UserHairpackedItemModel> getHairpackedItemByHairId(Integer id)
			throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		String hql="from UserHairpackedItemModel where hairpackedid=?";
		Query query = session.createQuery(hql);
		query.setInteger(0, id);

		List<UserHairpackedItemModel> list = (ArrayList<UserHairpackedItemModel>)query.list();
		
		return list;
	}
}
