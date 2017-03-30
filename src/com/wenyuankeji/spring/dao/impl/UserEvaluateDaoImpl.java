package com.wenyuankeji.spring.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IUserEvaluateDao;
import com.wenyuankeji.spring.model.UserEvaluateModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class UserEvaluateDaoImpl implements IUserEvaluateDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public UserEvaluateModel searchUserEvaluate(int userid, String content) throws BaseException{
		try {
			Session session = sessionFactory.getCurrentSession();

			String hql = "FROM UserEvaluateModel WHERE deleted = 0 and userid=? AND content=?";
			Query query = session.createQuery(hql);

			query.setInteger(0, userid);
			query.setString(1, content);

			UserEvaluateModel userEvaluateModel = null;
			@SuppressWarnings("unchecked")
			List<Object> oList = query.list();
			
			if (null != oList && oList.size() > 0) {
				userEvaluateModel = (UserEvaluateModel) oList.get(0);
			}

			return userEvaluateModel;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public List<UserEvaluateModel> searchUserEvaluate(int item, int type) throws BaseException{
		try {
			Session session = sessionFactory.getCurrentSession();

			String hql = "FROM UserEvaluateModel WHERE deleted = 0 and item=? AND type=? and userid!=0 order by createtime desc";
			Query query = session.createQuery(hql);

			query.setInteger(0, item);
			query.setInteger(1, type);

			@SuppressWarnings("unchecked")
			List<UserEvaluateModel> userEvaluateList = (List<UserEvaluateModel>) query.list();

			return userEvaluateList;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public List<UserEvaluateModel> searchUserEvaluate(int userid) throws BaseException{
		try {
			Session session = sessionFactory.getCurrentSession();

			String hql = "FROM UserEvaluateModel WHERE deleted = 0 and userid=?";
			Query query = session.createQuery(hql);

			query.setInteger(0, userid);

			@SuppressWarnings("unchecked")
			List<UserEvaluateModel> userEvaluateList = (List<UserEvaluateModel>) query.list();

			return userEvaluateList;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public int addUserEvaluate(UserEvaluateModel o) throws BaseException {
		try {
			
			Session session = sessionFactory.getCurrentSession();
			Integer evaid = (Integer) session.save(o);
			
			return evaid;
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public List<Object []> searchUserEvaluateCount(String item,String type)throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();

			String sql = "select evaluate,COUNT(*) as con,SUM(score) as score  from user_evaluate_score where evaid in (select evaid from user_evaluate where type = "+type+" and item = "+item+") GROUP BY evaluate ";
			Query query = session.createSQLQuery(sql);

			@SuppressWarnings("unchecked")
			List<Object []> oList = query.list();
			
			if (null != oList && oList.size() > 0) {
				return oList;
			}

			return null;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	@Override
	public UserEvaluateModel searchUserEvaluate(int userid, int item,
			int orderid,int type)throws BaseException {
		
		try {
			Session session = sessionFactory.getCurrentSession();

			String hql = "FROM UserEvaluateModel WHERE deleted = 0 and type = ? and userid=? AND item=? and orderid = ?";
			Query query = session.createQuery(hql);

			query.setInteger(0, type);
			query.setInteger(1, userid);
			query.setInteger(2, item);
			query.setInteger(3, orderid);

			UserEvaluateModel userEvaluateModel = null;
			@SuppressWarnings("unchecked")
			List<Object> oList = query.list();
			
			if (null != oList && oList.size() > 0) {
				userEvaluateModel = (UserEvaluateModel) oList.get(0);
			}

			return userEvaluateModel;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
	}

	@Override
	public List<UserEvaluateModel> searchCommentNum(int type,int item)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();

			String hql = "FROM UserEvaluateModel WHERE deleted = 0 and type = ? and item = ? order by createtime desc ";
			Query query = session.createQuery(hql);

			query.setInteger(0, type);
			query.setInteger(1, item);
			
			@SuppressWarnings("unchecked")
			List<UserEvaluateModel> userEvaluateList = (List<UserEvaluateModel>) query.list();

			return userEvaluateList;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	
	@Override
	public List<UserEvaluateModel> searchUserEvaluateByItem(int item,int evaid,int styleID)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();

			StringBuffer sql = new StringBuffer("select a.evaid,a.item,a.userid,a.type,a.score,a.content,a.orderid,a.deleted,a.createtime from user_evaluate as a,orderinfo as b");
			sql.append(" where a.orderid = b.orderid ");
			sql.append(" and a.type = 1 ");	  	//美发师
			sql.append(" and a.item = ? ");	    //美发师ID
			sql.append(" and a.evaid > ? ");  	//分页使用
			if (styleID != 0) {
				sql.append(" and b.styleid = ? ");  //发型项目ID
			}
			sql.append(" and a.deleted = 0 ");  	//未删除的数据
			sql.append(" order by a.evaid asc ");
			
			Query query = session.createSQLQuery(sql.toString());

			query.setInteger(0, item);
			query.setInteger(1, evaid);
			if (styleID != 0) {
				query.setInteger(2, styleID);
			}
			
			query.setFirstResult(0);
			query.setMaxResults(10);
			
			@SuppressWarnings("unchecked")
			List<Object[]> objList = (List<Object[]>) query.list();
			
			List<UserEvaluateModel> userEvaluateList = new ArrayList<UserEvaluateModel>();
			for (Object[] obj : objList) {
				UserEvaluateModel userEvaluateModel = new UserEvaluateModel();
				userEvaluateModel.setEvaid(Integer.parseInt(obj[0].toString()));
				userEvaluateModel.setItem(obj[1].toString());
				userEvaluateModel.setUserid(Integer.parseInt(obj[2].toString()));
				userEvaluateModel.setType(Integer.parseInt(obj[3].toString()));
				userEvaluateModel.setScore(Float.parseFloat(obj[4].toString()));
				userEvaluateModel.setContent(obj[5].toString());
				userEvaluateModel.setOrderid(Integer.parseInt(obj[6].toString()));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = sdf.parse(obj[8].toString());
				userEvaluateModel.setCreatetime(date);
				userEvaluateList.add(userEvaluateModel);
			}

			return userEvaluateList;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public List<UserEvaluateModel> searchUserEvaluateByStore(int item, int evaid)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();

			String hql = "FROM UserEvaluateModel WHERE type=2 and item=? and evaid>? and deleted = 0 order by evaid asc";
			Query query = session.createQuery(hql);

			query.setInteger(0, item);
			query.setInteger(1, evaid);
			
			query.setFirstResult(0);
			query.setMaxResults(10);
			
			@SuppressWarnings("unchecked")
			List<UserEvaluateModel> userEvaluateList = (List<UserEvaluateModel>) query.list();

			return userEvaluateList;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public boolean updateUserEvaluate(int evaid, int score) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hqlUpdate = "UPDATE user_evaluate SET score=" + score + " WHERE evaid= " + evaid;
			Query queryUpdate = session.createSQLQuery(hqlUpdate);
			int isok = queryUpdate.executeUpdate();
			if(isok > 0){
				return true;
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		return false;
	}
	
}
