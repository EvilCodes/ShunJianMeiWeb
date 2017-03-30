package com.wenyuankeji.spring.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IUserinfoDao;
import com.wenyuankeji.spring.model.UserinfoModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class UserinfoDaoImpl implements IUserinfoDao{
	
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	@Override
	public boolean updatelLastlogintimeById(int id) throws BaseException{
		
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "UPDATE UserinfoModel SET lastlogintime = SYSDATE() WHERE userid = ? ";
			Query query = session.createQuery(hql);
			query.setInteger(0, id);
			return query.executeUpdate() == 1;
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	@Override
	public boolean updateUserinfo(UserinfoModel userinfo)throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "UPDATE UserinfoModel SET userName=?,passWord=? WHERE userid = ?";
			Query query = session.createQuery(hql);
			query.setString(0, userinfo.getUsername());
			query.setString(1,userinfo.getPassword());
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
	public UserinfoModel addUserinfo(String userid, String nickName, String sex)throws BaseException {
		UserinfoModel userinfo = new UserinfoModel();
		try {
			userinfo.setUserid(Integer.parseInt(userid));
			userinfo.setNickname(nickName);
			userinfo.setSex(Integer.parseInt(sex));
			Session session = sessionFactory.getCurrentSession();
			Serializable isok = session.save(userinfo);
			int result = Integer.parseInt(isok.toString());
			if(result != 0)
				return userinfo;
			else
				return null;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	@Override
	public boolean updateUserinfo(int userID,String oldPassWord,String newPassWord, int userState)throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "UPDATE UserinfoModel SET password=? WHERE userid = ? AND password=? AND userstate=?";
			Query query = session.createQuery(hql);
			query.setString(0, newPassWord);
			query.setInteger(1,userID);
			query.setString(2, oldPassWord);
			query.setInteger(3, userState);
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
	public boolean updateFindUserinfo(UserinfoModel userinfo)throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "UPDATE UserinfoModel SET nickname=? , sex=? WHERE userid = ?";
			Query query = session.createQuery(hql);
			query.setString(0, userinfo.getNickname());
			query.setInteger(1,userinfo.getSex());
			query.setInteger(2,userinfo.getUserid());
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
	public boolean updateFindPassWord(String userName, String passWord, int userState)throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "UPDATE UserinfoModel SET password=?,updatetime=SYSDATE() WHERE username = ? AND usertype=? ";
			Query query = session.createQuery(hql);
			query.setString(0, passWord);
			query.setString(1, userName);
			query.setInteger(2, userState);
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
	public UserinfoModel searchUserinfoByUserName(String userName,int type) throws BaseException{
		Session session = sessionFactory.getCurrentSession();

		try {
			//查询添加的数据
			String hql = "FROM UserinfoModel WHERE username=? and usertype = ? ";
			Query query = session.createQuery(hql);
			
			query.setString(0, userName);
			query.setInteger(1, type);
			
			@SuppressWarnings("unchecked")
			List<Object> objList = query.list();
			
			if (null != objList && objList.size() > 0) {
				return (UserinfoModel) objList.get(0);
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
		return null;
	}

	@Override
	public List<Integer> searchUserinfoByCityId(int cityid,String version)
			throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			String sql = "SELECT a.userid FROM userinfo AS a ";
			sql += "INNER JOIN usersubinfo AS b ";
			sql += "ON a.userid = b.userid ";
			sql += "WHERE 1=1 ";
			sql += "AND a.cityid=? ";
			sql += "AND a.usertype=2 AND a.userstate=1 AND b.checkstate=2 ";
			if(version.equals("1.0")){
				sql += "AND b.istype=0";
			}
			Query query = session.createSQLQuery(sql);
			query.setInteger(0, cityid);
			
			@SuppressWarnings("unchecked")
			List<Integer> userIdList = (List<Integer>)query.list();
			return userIdList;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	
	
	
	
	@Override
	public boolean updateUserInfoByUserId(UserinfoModel userInfo) throws BaseException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(userInfo);
			return true;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		/*
		String hql = "update UserinfoModel set username = ?,password=?,nickname=?,sex=?,birthday=?,provinceid=?,cityid=?,bindphone=?,usertype=?,userstate=?,updatetime=? where userid = ? ";
		Query query = session.createQuery(hql);
		int i = 0;
		query.setString(i, userInfo.getUsername());
		query.setString(++i, userInfo.getPassword());
		query.setString(++i, userInfo.getNickname());
		query.setInteger(++i, userInfo.getSex());
		query.setDate(++i, userInfo.getBirthday());
		query.setInteger(++i, userInfo.getProvinceid());
		query.setInteger(++i, userInfo.getCityid());
		query.setString(++i, userInfo.getBindphone());
		query.setInteger(++i, userInfo.getUsertype());
		query.setInteger(++i, userInfo.getUserstate());
		query.setDate(++i, userInfo.getUpdatetime());
		query.setInteger(++i, userInfo.getUserid());

		int isok = query.executeUpdate();
	    
	    if (isok > 0) {
			return true;
		}	    
	    return false;*/
	}

	@Override
	public UserinfoModel searchUserinfo(String userName, String uassWord,int usertype) throws BaseException{
		Session session = sessionFactory.getCurrentSession();
		UserinfoModel userinfoModel = null;
		
		try {
			String hql = "FROM UserinfoModel WHERE username=? AND password=? AND userstate=1 and usertype = ?";
			Query query = session.createQuery(hql);

			query.setString(0, userName);
			query.setString(1, uassWord);
			query.setInteger(2, usertype);
			
			@SuppressWarnings("unchecked")
			List<Object> objList = query.list();
			
			if (null != objList && objList.size() > 0) {
				userinfoModel = (UserinfoModel) objList.get(0);
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
		return userinfoModel;
	
	}
	
	@Override
	public boolean addUserinfo(UserinfoModel userinfo) {
		
		Session session = sessionFactory.getCurrentSession();
		Integer id = (Integer) session.save(userinfo);

		return id != 0;
		
		
		//查询添加的数据
		//String hql = "FROM UserinfoModel WHERE username=? AND password=? AND userstate=1";
		//Query query = session.createQuery(hql);
		
		//query.setString(0, userinfo.getUsername());
		//query.setString(1, userinfo.getPassword());
		
//		@SuppressWarnings("unchecked")
		//List<Object> objList = query.list();
		
//		if (null != objList && objList.size() > 0) {
//			return false;
//		}else{
//			userinfo.setPassword(userinfo.getPassword());
//			Serializable isok = session.save(userinfo);
//			int result = Integer.parseInt(isok.toString());
//			if(result != 0){
//				return true;
//			}else{
//				return false;
//			}
//		}
	}
	
	@Override
	public int addUserInfo(UserinfoModel userinfo) throws BaseException {
		
		try {
			Session session = sessionFactory.getCurrentSession();
			Integer userid = (Integer) session.save(userinfo);
			return userid;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
		
		/*
		//查询添加的数据
		String hql = "FROM UserinfoModel WHERE username=? AND password=? AND userstate=1";
		Query query = session.createQuery(hql);
		
		query.setString(0, userinfo.getUsername());
		query.setString(1, userinfo.getPassword());
		
		@SuppressWarnings("unchecked")
		List<Object> objList = query.list();
		
		if (null != objList && objList.size() > 0) {
			result = -1;
		}else{
			userinfo.setPassword(userinfo.getPassword());
			Serializable isok = session.save(userinfo);
			int addResult = Integer.parseInt(isok.toString());
			if(addResult != 0){
				result = addResult;
			}else{
				result = 0;
			}
		}
		*/
	}
	
	@Override
	public UserinfoModel searchUserinfoById(int userid) {
		UserinfoModel userinfoModel = new UserinfoModel();
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM UserinfoModel WHERE userid=?";
		Query query = session.createQuery(hql);

		query.setInteger(0, userid);

		@SuppressWarnings("unchecked")
		List<Object> olist = query.list();
		
		if(null != olist && olist.size() > 0 ){
			userinfoModel = (UserinfoModel) olist.get(0);
		}
		return userinfoModel;
	}

	/***********UP  mupengyuan*****************/
	
	
	/************管理端************/
	@Override
	public List<UserinfoModel> searchUserinfo(String cityid, String userid,
			String tel, String startTime, String endTime, String checkstate,
			int page, int rows) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			StringBuffer sql = new StringBuffer("FROM UserinfoModel WHERE usertype=2");
			//美发师ID
			if (userid !=null && userid.trim().length() > 0) {
				sql.append(" and userid='"+ userid +"' ");
			}
			//手机号
			if (tel !=null && tel.trim().length() > 0) {
				sql.append(" and bindphone='"+ tel +"' ");
			}
			//注册开始时间
			if (startTime !=null && startTime.trim().length() > 0) {
				sql.append(" and createtime>='"+ startTime +"' ");
			}
			//注册结束时间
			if (endTime !=null && endTime.trim().length() > 0) {
				sql.append(" and createtime<='"+ endTime +"' ");
			}
			//审核状态
			//if (checkstate !=null && checkstate.trim().length() > 0) {
				//sql.append(" and userid=='"+ checkstate +"' ");
			//}
			Query query = session.createQuery(sql.toString());

			query.setFirstResult((page - 1) * rows);
			query.setMaxResults(rows);

			@SuppressWarnings("unchecked")
			List<UserinfoModel> userinfoModels = (List<UserinfoModel>) query.list();

			return userinfoModels;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public int searchUserinfoCount(String cityid, String userid, String tel,
			String startTime, String endTime, String checkstate)
			throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			StringBuffer sql = new StringBuffer("FROM UserinfoModel WHERE usertype=2");
			//美发师ID
			if (userid !=null && userid.trim().length() > 0) {
				sql.append(" and userid='"+ userid +"' ");
			}
			//手机号
			if (tel !=null && tel.trim().length() > 0) {
				sql.append(" and bindphone='"+ tel +"' ");
			}
			//注册开始时间
			if (startTime !=null && startTime.trim().length() > 0) {
				sql.append(" and createtime>='"+ startTime +"' ");
			}
			//注册结束时间
			if (endTime !=null && endTime.trim().length() > 0) {
				sql.append(" and createtime<='"+ endTime +"' ");
			}
			//审核状态
			Query query = session.createQuery(sql.toString());
			@SuppressWarnings("unchecked")
			List<UserinfoModel> userinfoModels = (List<UserinfoModel>) query.list();

			return userinfoModels.size();
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	
}
