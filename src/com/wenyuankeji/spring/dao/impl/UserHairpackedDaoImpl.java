package com.wenyuankeji.spring.dao.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IUserHairpackedDao;
import com.wenyuankeji.spring.dao.IUserHairpackedItemDao;
import com.wenyuankeji.spring.dao.IUserMyhairpackedDao;
import com.wenyuankeji.spring.model.UserHairpackedModel;
import com.wenyuankeji.spring.util.BaseException;
/**
 * 发型套餐实现类
 * @ClassName: UserHairpackedDaoImpl
 * @Description: 
 * @author lnn
 * @date 2016年1月11日
 */
@Repository
public class UserHairpackedDaoImpl implements IUserHairpackedDao{
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	/*查询套餐列表（只查询可用的）*/
	@Override
	public List<UserHairpackedModel> searchUserHairpacked(UserHairpackedModel userHairpacked, int page, int rows) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		List<UserHairpackedModel> list=new ArrayList<UserHairpackedModel>();
		try {
			String hql="FROM UserHairpackedModel where storeid=? and state=1 order by createtime";
			Query query = session.createQuery(hql);
			query.setInteger(0, userHairpacked.getStoreid());
			query.setFirstResult((page - 1) * rows);
			query.setMaxResults(rows);
			list = query.list();
			return list;
		} catch (Exception e) {
				throw new BaseException(e.getMessage());
		}
	}
	
	public List<UserHairpackedModel> searchUserHairpackedLength(UserHairpackedModel userHairpacked){
		Session session = sessionFactory.getCurrentSession();
		List<UserHairpackedModel> list=new ArrayList<UserHairpackedModel>();
		String hql="FROM UserHairpackedModel where storeid=? and state=1 order by createtime";
		Query query = session.createQuery(hql);
		query.setInteger(0, userHairpacked.getStoreid());
		list = query.list();
		return list;
	}
	
	/*添加套餐*/
	@Override
	public boolean addUserHairpacked(UserHairpackedModel userHairpacked) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			Serializable save = session.save(userHairpacked);
			return save!=null?true:false;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	@Override
	public List<UserHairpackedModel> searchUserHairpackedAll(
			UserHairpackedModel userHairpacked,int page, int rows) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql="FROM UserHairpackedModel where storeid=? order by createtime";
			Query query = session.createQuery(hql);
			query.setInteger(0, userHairpacked.getStoreid());
			query.setFirstResult((page - 1) * rows);
			query.setMaxResults(rows);
			return query.list();
		} catch (Exception e) {
				throw new BaseException(e.getMessage());
		}
	}
	@Override
	public boolean updateUserHairpacked(UserHairpackedModel userHairpacked)
			throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.update(userHairpacked);
			return true;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	@Override
	public UserHairpackedModel searchUserHairpackedByUserHairpacedId(Integer id)
			throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		List<UserHairpackedModel> list=new ArrayList<UserHairpackedModel>();
		try {
			String hql="FROM UserHairpackedModel where storeid= ?";
			Query query = session.createQuery(hql);
			query.setInteger(0, id);
			list = query.list();
			if (list!=null && list.size()>0) {
				return list.get(0);
			}
			return new UserHairpackedModel();
		} catch (Exception e) {
				throw new BaseException(e.getMessage());
		}
	}
	
	/**
	 * 查询状态可用的套餐
	 */
	@Override
	public UserHairpackedModel searchUserHairpackedById(Integer id)
			throws BaseException {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hql="from UserHairpackedModel where id=? and state=1";
		Query query=session.createQuery(hql);
		query.setInteger(0, id);
		UserHairpackedModel uhm=null;
		if(query.list().size()>0){
			uhm=(UserHairpackedModel)query.list().get(0);
		}
		return uhm;
	}
	@Override
	public UserHairpackedModel searchUserHairpackedByIdnoState(Integer id)
			throws BaseException {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hql="from UserHairpackedModel where id=? ";
		Query query=session.createQuery(hql);
		query.setInteger(0, id);
		UserHairpackedModel uhm=null;
		if(query.list().size()>0){
			uhm=(UserHairpackedModel)query.list().get(0);
		}
		return uhm;
	}
}
