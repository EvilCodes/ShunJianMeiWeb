package com.wenyuankeji.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IFavoritesDao;
import com.wenyuankeji.spring.model.FavoritesModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class FavoritesDaoImpl implements IFavoritesDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public FavoritesModel searchFavorites(int userid, int type, int item)
			throws BaseException {

		FavoritesModel favorites = null;

		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM FavoritesModel WHERE userid=? AND type=? AND item=? ";
			Query query = session.createQuery(hql);

			query.setInteger(0, userid);
			query.setInteger(1, type);
			query.setInteger(2, item);

			@SuppressWarnings("unchecked")
			List<Object> objList = query.list();

			if (null != objList && objList.size() > 0) {
				favorites = (FavoritesModel) objList.get(0);
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}

		return favorites;
	}

	@Override
	public boolean addFavorite(FavoritesModel o) throws BaseException {

		try {
			Session session = sessionFactory.getCurrentSession();

			int isok = (Integer) session.save(o);

			if (isok > 0) {
				return true;
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}

		return false;
	}

	@Override
	public boolean updateFavorite(String userid, String type, String item)
			throws BaseException {
		return false;
	}

	@Override
	public boolean deleteFavorite(String userid, String type, String item)
			throws BaseException {
		
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "DELETE FavoritesModel WHERE userid=? AND type=? AND item=? ";
			Query query = session.createQuery(hql);

			query.setString(0, userid);
			query.setString(1, type);
			query.setString(2, item);

			
			int isok = query.executeUpdate();

			if (isok > 0) {
				return true;
			}
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}

		return false;
	}

	@Override
	public List<FavoritesModel> searchFavorite(int userid, int type,int pageSize,int pageIndex) 
		throws BaseException{
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM FavoritesModel where userid=? and type=?";
			Query query = session.createQuery(hql);
			query.setInteger(0, userid);
			query.setInteger(1, type);

			query.setFirstResult((pageIndex - 1) * pageSize);
			query.setMaxResults(pageSize);
			
			@SuppressWarnings("unchecked")
			List<FavoritesModel> favoritesModels = query.list();
			return favoritesModels;
		}catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	
	@Override
	public int searchFavoriteCount(int userid, int type)
			throws BaseException {
		int count = 0;
		try {
			Session session = sessionFactory.getCurrentSession();
			StringBuffer sql = new StringBuffer("FROM FavoritesModel where userid=? ");
			if (type != 0) {
				sql.append(" and type=?");
			}
			Query query = session.createQuery(sql.toString());
			query.setInteger(0, userid);
			if (type != 0) {
				query.setInteger(1, type);
			}
			
			if(query.list() != null && query.list().size() > 0){
				count = query.list().size();
			}
		}catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		return count;
	}

}
