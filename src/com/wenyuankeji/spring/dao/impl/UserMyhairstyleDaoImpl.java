package com.wenyuankeji.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IUserMyhairstyleDao;
import com.wenyuankeji.spring.model.UserMyhairstyleModel;
import com.wenyuankeji.spring.model.UserMyhairstylePhotoMappingModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class UserMyhairstyleDaoImpl implements IUserMyhairstyleDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public UserMyhairstyleModel searchUserMyhairstyle(int mystyleid) throws BaseException{
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserMyhairstyleModel WHERE mystyleid=? ";
			Query query = session.createQuery(hql);

			query.setInteger(0, mystyleid);

			UserMyhairstyleModel userMyhairstyle = null;
			if (null != query.list() && query.list().size() > 0) {
				userMyhairstyle = (UserMyhairstyleModel) query.list().get(0);
			}
			return userMyhairstyle;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	@Override
	public List<UserMyhairstyleModel> searchUserMyhairstyle(String userid,String styleid,String sort,int pageSize,int pageIndex) throws BaseException{
		try {
			Session session = sessionFactory.getCurrentSession();
			
			StringBuffer sbBuffer = new StringBuffer("FROM UserMyhairstyleModel where state = 4 ");
			
			if(userid.length() > 0){			
				sbBuffer.append(" and userid in (" + userid + ")" );
			}
			
			if (!"".equals(styleid) && !styleid.equals("0")) {
				sbBuffer.append(" and styleid=? ");
			}
			if (!"".equals(sort) && sort.equals("1")) {
				sbBuffer.append(" order by price desc");
			}else{
				sbBuffer.append(" order by price asc");
			}
			
			Query query = session.createQuery(sbBuffer.toString());
			//query.setString(0, userid);
			if (!"".equals(styleid) && !styleid.equals("0")) {
				query.setString(0, styleid);
			}
			query.setFirstResult((pageIndex - 1) * pageSize);
			query.setMaxResults(pageSize);
			
			@SuppressWarnings("unchecked")
			List<UserMyhairstyleModel> userMyhairstyleModels = (List<UserMyhairstyleModel>)query.list();
			return userMyhairstyleModels;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
	}
	
	/**
	 * 查询发型相册的卡片
	 * @param mystyleid 我的发型ID
	 * @return
	 */
	public UserMyhairstylePhotoMappingModel searchUserMyhairstylePhotoMapping(int mystyleid)throws BaseException{
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserMyhairstylePhotoMappingModel WHERE mystyleid=? ";
			Query query = session.createQuery(hql);
			query.setInteger(0, mystyleid);

			UserMyhairstylePhotoMappingModel userMyhairstylePhotoMappingModel = null;
			if (null != query.list() && query.list().size() > 0) {
				userMyhairstylePhotoMappingModel = (UserMyhairstylePhotoMappingModel) query.list().get(0);
			}
			return userMyhairstylePhotoMappingModel;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
	}
	
	@Override
	public UserMyhairstyleModel searchUserMyhairstyleByUserId(int userid) throws BaseException{
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserMyhairstyleModel WHERE deleted = 0 and userid=? ";
			Query query = session.createQuery(hql);

			query.setInteger(0, userid);

			UserMyhairstyleModel userMyhairstyle = null;
			if (null != query.list() && query.list().size() > 0) {
				userMyhairstyle = (UserMyhairstyleModel) query.list().get(0);
			}
			return userMyhairstyle;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
	}
	
	//根据用户id查询发型列表
	@Override
	public List<UserMyhairstyleModel> searchUserMyhairstyleUserId(int userid) throws BaseException{
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserMyhairstyleModel WHERE state = 4 and deleted = 0 and userid=? ";
			Query query = session.createQuery(hql);

			query.setInteger(0, userid);

			@SuppressWarnings("unchecked")
			List<UserMyhairstyleModel> userMyhairstyleModel = (List<UserMyhairstyleModel>) query
					.list();
			return userMyhairstyleModel;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
	}

	@Override
	public int addUserMyhairstyle(UserMyhairstyleModel o) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			Integer mystyleid = (Integer) session.save(o);
			return mystyleid;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public boolean updateUserMyhairstyle(UserMyhairstyleModel o) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(o);
			return true;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
			
		}
	}

	@Override
	public boolean updateUserMyhairstyle(int mystyleid,int state,int oldState) throws BaseException {
		try {
			
			Session session = sessionFactory.getCurrentSession();
			StringBuffer sql = new StringBuffer(" update user_myhairstyle set ");
			if (state == 1) {
				sql.append(" deleted=1 ");
				sql.append(" where mystyleid = '"+mystyleid+"'");
				
				Query query = session.createSQLQuery(sql.toString());
				
				if (query.executeUpdate() > 0) {
					return true;
				}else {
					return false;
				}
			}else{
				if (state == 2) {
					if (oldState == 4) {
						sql.append(" state=3 ");
						sql.append(" where mystyleid = '"+mystyleid+"'");
						
						Query query = session.createSQLQuery(sql.toString());
						
						if (query.executeUpdate() > 0) {
							return true;
						}else {
							return false;
						}
					}
				}
				if (state == 3) {
					if (oldState == 3) {
						sql.append(" state=4 ");
						sql.append(" where mystyleid = '"+mystyleid+"'");
						
						Query query = session.createSQLQuery(sql.toString());
						
						if (query.executeUpdate() > 0) {
							return true;
						}else {
							return false;
						}
					}
				}
			}
			return false;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public List<UserMyhairstyleModel> searchUserMyhairstylesUserId(int userid)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserMyhairstyleModel WHERE deleted = 0 and userid=? ";
			Query query = session.createQuery(hql);

			query.setInteger(0, userid);

			@SuppressWarnings("unchecked")
			List<UserMyhairstyleModel> userMyhairstyleModel = (List<UserMyhairstyleModel>) query
					.list();
			return userMyhairstyleModel;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
}
