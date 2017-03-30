package com.wenyuankeji.spring.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IUserWorkdateDao;
import com.wenyuankeji.spring.model.UserWorkdateModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class UserWorkdateDaoImpl implements IUserWorkdateDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Override
	public UserWorkdateModel searchUserWorkdate(int wdid) throws BaseException{
		Session session = sessionFactory.getCurrentSession();
		UserWorkdateModel userWorkdateModel = null;
		
		try {
			String hql = "FROM UserWorkdateModel WHERE wdid=? ";
			Query query = session.createQuery(hql);
			query.setInteger(0, wdid);
			
			@SuppressWarnings("unchecked")
			List<Object> objList = query.list();
			
			if (null != objList && objList.size() > 0) {
				userWorkdateModel = (UserWorkdateModel) objList.get(0);
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
		return userWorkdateModel;
	}
	
	@Override
	public List<UserWorkdateModel> searchUserWorkdateByUserID(int userID)throws BaseException{
		try {
			
			Session session = sessionFactory.getCurrentSession();
			
			//String hql = "select wdid,userid,year,month,day from user_workdate where concat(`year`,if(`month`<10,concat('0',`month`),`month`),if(`day`<10,concat('0',`day`),`day`)) > DATE_FORMAT(SYSDATE(),'%Y%m%d')  AND userid = "+ userID  + " ORDER BY year,month,day ASC limit 7";
			
			String hql = "SELECT wdid,userid,year,month,day FROM user_workdate WHERE 1=1";

			hql += " AND concat(`year`,if(`month`<10,concat('0',`month`),`month`),if(`day`<10,concat('0',`day`),`day`)) >=";
			hql += " DATE_FORMAT(SYSDATE(),'%Y%m%d') ";
			hql += " AND concat(`year`,if(`month`<10,concat('0',`month`),`month`),if(`day`<10,concat('0',`day`),`day`)) <= ";
			hql += " DATE_FORMAT(DATE_ADD(SYSDATE(), INTERVAL 7 DAY),'%Y%m%d') ";
			hql += " AND userid = "+ userID;
			hql += " ORDER BY year,month,day ASC ";
			Query query = session.createSQLQuery(hql);
			
			@SuppressWarnings("unchecked")
			List<Object[]> objList = (List<Object[]>) query.list();
			List<UserWorkdateModel> userWorkdateList = new ArrayList<UserWorkdateModel>();
			for (Object[] obj : objList) {
				UserWorkdateModel userWorkdateModel = new UserWorkdateModel();
				userWorkdateModel.setWdid(Integer.parseInt(obj[0].toString()));
				userWorkdateModel.setUserid(Integer.parseInt(obj[1].toString()));
				userWorkdateModel.setYear(Integer.parseInt(obj[2].toString()));
				userWorkdateModel.setMonth(Integer.parseInt(obj[3].toString()));
				userWorkdateModel.setDay(Integer.parseInt(obj[4].toString()));
				userWorkdateList.add(userWorkdateModel);
			}

			return userWorkdateList;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
	}
	
	@Override
	public UserWorkdateModel searchUserWorkdate(int userID,int year,int month,int day) throws BaseException{
		Session session = sessionFactory.getCurrentSession();
		UserWorkdateModel userWorkdateModel = null;
		
		try {
			String hql = "FROM UserWorkdateModel WHERE year = ? and month = ? and day = ? and userid=? ";
			Query query = session.createQuery(hql);
			query.setInteger(0, year);
			query.setInteger(1, month);
			query.setInteger(2, day);
			query.setInteger(3, userID);
			
			@SuppressWarnings("unchecked")
			List<Object> objList = query.list();
			
			if (null != objList && objList.size() > 0) {
				userWorkdateModel = (UserWorkdateModel) objList.get(0);
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
		return userWorkdateModel;
	}

	@Override
	public int addUserWorkdate(UserWorkdateModel o) throws BaseException {
		
		try {
			Session session = sessionFactory.getCurrentSession();
			
			Integer wdid = (Integer) session.save(o);
			
			return wdid;
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
	}

	@Override
	public boolean searchUserWorkdateByUserId(int userID, String year, String month,
			String day) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		
		try {
			String hql = "SELECT SUM(state) FROM `user_workhour` WHERE wdid = (";
			hql += " SELECT wdid FROM `user_workdate` WHERE user_workdate.`year`=" + year;
			hql += " AND user_workdate.`month`=" + month;
			hql += " AND user_workdate.`day`=" + day;
			hql += " AND user_workdate.`userid`=" + userID;
			hql += ")";
			Query query = session.createSQLQuery(hql);
			@SuppressWarnings("unchecked")
			List<Object> objList = query.list();
			
			if (null != objList && objList.size() > 0) {
				int sum = Integer.parseInt(objList.get(0).toString());
				if(sum > -12){
					return true;
				}
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}		
		return false;
	}

	@Override
	public List<UserWorkdateModel> searchUserWorkdateByUserIDAndNum(int ID,
			int num) throws BaseException {
		
	try {
			//String dateNow = date.replaceAll("-","");
			Session session = sessionFactory.getCurrentSession();
			
			//String hql = "select wdid,userid,year,month,day from user_workdate where concat(`year`,if(`month`<10,concat('0',`month`),`month`),if(`day`<10,concat('0',`day`),`day`)) > DATE_FORMAT(SYSDATE(),'%Y%m%d')  AND userid = "+ userID  + " ORDER BY year,month,day ASC limit num";
			
			String hql = "SELECT wdid,userid,year,month,day FROM user_workdate WHERE 1=1";

			hql += " AND concat(`year`,if(`month`<10,concat('0',`month`),`month`),if(`day`<10,concat('0',`day`),`day`)) >=";
			hql += " DATE_FORMAT(SYSDATE() ,'%Y%m%d') ";
			hql += " AND concat(`year`,if(`month`<10,concat('0',`month`),`month`),if(`day`<10,concat('0',`day`),`day`)) <= ";
			hql += " DATE_FORMAT(DATE_ADD(SYSDATE() , INTERVAL "+num+" DAY),'%Y%m%d') ";
			hql += " AND userid = "+ ID;
			hql += " ORDER BY year,month,day ASC ";
			Query query = session.createSQLQuery(hql);
			
			@SuppressWarnings("unchecked")
			List<Object[]> objList = (List<Object[]>) query.list();
			List<UserWorkdateModel> userWorkdateList = new ArrayList<UserWorkdateModel>();
			for (Object[] obj : objList) {
				UserWorkdateModel userWorkdateModel = new UserWorkdateModel();
				userWorkdateModel.setWdid(Integer.parseInt(obj[0].toString()));
				userWorkdateModel.setUserid(Integer.parseInt(obj[1].toString()));
				userWorkdateModel.setYear(Integer.parseInt(obj[2].toString()));
				userWorkdateModel.setMonth(Integer.parseInt(obj[3].toString()));
				userWorkdateModel.setDay(Integer.parseInt(obj[4].toString()));
				userWorkdateList.add(userWorkdateModel);
			}

			return userWorkdateList;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
	}

}
