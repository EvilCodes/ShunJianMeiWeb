package com.wenyuankeji.spring.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

// import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IUsersubinfoDao;
import com.wenyuankeji.spring.model.UserinfoModel;
import com.wenyuankeji.spring.model.UsersubinfoModel;
import com.wenyuankeji.spring.util.BaseException;
import com.wenyuankeji.spring.util.Constant;

@Repository
public class UsersubinfoDaoImpl implements IUsersubinfoDao {

	@Override
	public boolean updateUserSubInfoByUserId(UsersubinfoModel userSubInfo)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(userSubInfo);
			return true;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}

		/*
		 * String hql =
		 * "update UsersubinfoModel set levelid = ?,intro=?,workinglife=?,checkstate=?,checktime=?,score=?,ordernum=?,hairstyle=?,hobbies=?,starid=?,email=?,household=?,truename=?,idcard=?,address=?,contact=?,contactmobile=?,relationship=?,createtime=? where userid = ? "
		 * ; Query query = session.createQuery(hql); int i = 0;
		 * query.setInteger(i, userSubInfo.getLevelid()); query.setString(++i,
		 * userSubInfo.getIntro()); query.setInteger(++i,
		 * userSubInfo.getWorkinglife()); query.setInteger(++i,
		 * userSubInfo.getCheckstate()); query.setDate(++i,
		 * userSubInfo.getChecktime()); query.setFloat(++i,
		 * userSubInfo.getScore()); query.setInteger(++i,
		 * userSubInfo.getOrdernum()); query.setString(++i,
		 * userSubInfo.getHairstyle()); query.setString(++i,
		 * userSubInfo.getHobbies()); query.setInteger(++i,
		 * userSubInfo.getStarid()); query.setString(++i,
		 * userSubInfo.getEmail()); query.setString(++i,
		 * userSubInfo.getHousehold()); query.setString(++i,
		 * userSubInfo.getTruename()); query.setString(++i,
		 * userSubInfo.getIdcard()); query.setString(++i,
		 * userSubInfo.getAddress()); query.setString(++i,
		 * userSubInfo.getContact()); query.setString(++i,
		 * userSubInfo.getContactmobile()); query.setString(++i,
		 * userSubInfo.getRelationship()); query.setDate(++i,
		 * userSubInfo.getCreatetime()); query.setInteger(++i,
		 * userSubInfo.getUserid());
		 * 
		 * int isok = query.executeUpdate();
		 * 
		 * if (isok > 0) { return true; } return false;
		 */
	}

	@Override
	public int addUserSubInfo(UsersubinfoModel userSubInfo) {
		int result = 0;
		Session session = sessionFactory.getCurrentSession();
		Serializable isok = session.save(userSubInfo);
		if (Integer.parseInt(isok.toString()) > 0) {
			result = Integer.parseInt(isok.toString());
		}
		return result;
	}

	@Override
	public int addUserSubInfo(String sql) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(sql);

		return query.executeUpdate();

	}

	@Override
	public UsersubinfoModel searchUserSubInfoByUserId(int userId) {
		UsersubinfoModel usersubinfo = new UsersubinfoModel();
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM UsersubinfoModel WHERE userid=?";
		Query query = session.createQuery(hql);
		query.setInteger(0, userId);
		if (null != query.list() && query.list().size() > 0) {
			usersubinfo = (UsersubinfoModel) query.list().get(0);
		}
		return usersubinfo;
	}

	/*********** UP mupengyuan *****************/

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public UsersubinfoModel searchUsersubinfo(int userid) throws BaseException {

		UsersubinfoModel usersubinfo = null;

		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UsersubinfoModel WHERE userid=?";

			Query query = session.createQuery(hql);
			query.setInteger(0, userid);

			@SuppressWarnings("unchecked")
			List<Object> oList = query.list();

			if (null != oList && oList.size() > 0) {
				usersubinfo = (UsersubinfoModel) oList.get(0);
			}

			return usersubinfo;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public Set<String> searchUsersubinfoIdList(int AreaID, String futureDay)
			throws BaseException {
		// 用户id
		Set<String> useridList = new HashSet<String>();

		try {

			Session session = sessionFactory.getCurrentSession();

			// 判断是否要需要商圈成为过滤条件
			// 查询到商圈下的店铺id
			String sql = "select distinct userid from user_workdate where wdid in "
					+ "(select wdid from user_workplace where storeid in ";

			if (0 != AreaID) {
				sql = sql + "(select storeid from  storeinfo where areaid = "
						+ AreaID + ")) ";
			} else {
				sql = sql + "(select storeid from  storeinfo )) ";
			}

			// 工作日
			if (null != futureDay && futureDay.length() > 0) {

				// 根据工作日查询美发师id
				sql = sql
						+ " and concat(`year`,if(`month`<10,concat('0',`month`),`month`),if(`day`<10,concat('0',`day`),`day`)) = ("
						+ futureDay + ")";
			}

			Query query = session.createSQLQuery(sql);

			@SuppressWarnings("unchecked")
			List<Object> workdateListByAreaid = query.list();
			if (null != workdateListByAreaid && workdateListByAreaid.size() > 0) {
				for (int i = 0; i < workdateListByAreaid.size(); i++) {

					useridList.add(workdateListByAreaid.get(i).toString());
				}
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}

		return useridList;
	}

	@Override
	public List<UsersubinfoModel> searchUsersubinfoList(int Sort, int CityID,
			int PageSize, int PageIndex, Set<String> userids, String Infversion)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			StringBuffer sbBuffer;
			if (Sort == Constant.SELECT_MAN) {
				sbBuffer = new StringBuffer(
						"FROM Usersubinfo,Userinfo where checkstate = 2 and userinfoModel.cityid = ? and "
								+ " Usersubinfo.userid=Userinfo.userid and Userinfo.sex=1  ");
			} else if (Sort == Constant.SELECT_WOMAN) {
				sbBuffer = new StringBuffer(
						"FROM Usersubinfo,Userinfo where checkstate = 2 and userinfoModel.cityid = ? and "
								+ " Usersubinfo.userid=Userinfo.userid and Userinfo.sex=2  ");

			} else {
				sbBuffer = new StringBuffer(
						"FROM UsersubinfoModel where checkstate = 2 and userinfoModel.cityid = ?  ");
			}
			if (null != userids && userids.size() > 0) {

				StringBuffer useridSB = new StringBuffer();

				for (String userid : userids) {
					useridSB.append("'").append(userid).append("',");
				}
				// 生成where条件
				String useridStr = useridSB.substring(0, useridSB.length() - 1);

				sbBuffer.append("and userid in (" + useridStr + ")");

			}
			// 版本更新，只查询资深类型的数据
			if (Infversion != null && "1.0".equals(Infversion)) {
				sbBuffer.append("and (istype = 0 or istype=null)");
			}
			if (Sort == Constant.ORDER_BY_LEVEL) {
				sbBuffer.append(" order by userProfessionLevel.level desc,sort DESC");
			} else if (Sort == Constant.ORDER_BY_ORDERNUM) {
				sbBuffer.append(" order by ordernum desc,sort DESC");
			} else if (Sort == Constant.ORDER_BY_SCORE) {
				sbBuffer.append(" order by score desc,sort DESC");
			}

			Query query = session.createQuery(sbBuffer.toString());

			query.setInteger(0, CityID);

			query.setFirstResult((PageIndex - 1) * PageSize);
			query.setMaxResults(PageSize);

			@SuppressWarnings("unchecked")
			List<UsersubinfoModel> userMyhairstyleModels = (List<UsersubinfoModel>) query
					.list();
			return userMyhairstyleModels;

		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}

	}

	@Override
	public boolean updateStoreinfo(UsersubinfoModel usersubinfo)
			throws BaseException {
		try {
			UserinfoModel userinfoModel = new UserinfoModel();
			Session session = sessionFactory.getCurrentSession();

			StringBuffer hql = new StringBuffer();
			/**
			 * 
			 * @param request
			 * @param model
			 * @param truename
			 *            姓名
			 * @param email
			 *            邮箱
			 * @param cityId
			 *            城市
			 * @param address
			 *            地址
			 * @param household
			 *            籍贯
			 * @param contact
			 *            紧急联系人
			 * @param contactMobile
			 *            紧急联系人电话
			 * @param relationship
			 *            紧急联系人关系
			 * @param idCard
			 *            身份证
			 * @param nickName
			 *            昵称
			 * @param starId
			 *            星座
			 * @param intro
			 *            简介
			 * @param hairstyle
			 *            擅长
			 * @param hobbies
			 *            个人爱好
			 * @return
			 * @throws BaseException
			 */
			hql.append("UPDATE UsersubinfoModel SET ");
			hql.append("truename=?, ");
			hql.append("email=?, ");
			hql.append("userinfoModel.cityId=?, ");
			hql.append("address=?, ");
			hql.append("household=?, ");
			hql.append("contact=?, ");
			hql.append("contactMobile=?, ");
			hql.append("relationship=?, ");
			hql.append("idCard=?, ");
			hql.append("userinfoModel.nickName=?, ");
			hql.append("starId=?, ");
			hql.append("intro=?, ");
			hql.append("hairstyle=?, ");
			hql.append("hobbies=? ");
			hql.append("WHERE ");
			hql.append("userinfoModel.bindphone=? ");
			Query query = session.createQuery(hql.toString());

			query.setString(0, usersubinfo.getTruename());
			query.setString(1, usersubinfo.getEmail());
			query.setInteger(2, userinfoModel.getCityid());
			query.setString(3, usersubinfo.getAddress());
			query.setString(4, usersubinfo.getHousehold());
			query.setString(5, usersubinfo.getContact());
			query.setString(6, usersubinfo.getContactmobile());
			query.setString(7, usersubinfo.getRelationship());
			query.setString(8, usersubinfo.getIdcard());
			query.setString(9, userinfoModel.getNickname());
			query.setInteger(10, usersubinfo.getStarid());
			query.setString(11, usersubinfo.getIntro());
			query.setString(12, usersubinfo.getHairstyle());
			query.setString(13, usersubinfo.getHobbies());
			query.setString(15, userinfoModel.getBindphone());

			int isok = query.executeUpdate();

			if (isok > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}

	}

	@Override
	public boolean updateOrdernum(int userid) throws BaseException {
		try {

			Session session = sessionFactory.getCurrentSession();
			String sql = "update usersubinfo set ordernum = ordernum + 1 where userid = '"
					+ userid + "'";

			Query query = session.createSQLQuery(sql);

			if (query.executeUpdate() > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public boolean updateScore(int userid, int score) throws BaseException {
		try {

			Session session = sessionFactory.getCurrentSession();
			String sql = "update usersubinfo set score = " + score
					+ " where userid = '" + userid + "'";

			Query query = session.createSQLQuery(sql);

			if (query.executeUpdate() > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	// add by jiazhaohui
	@Override
	public boolean updateMasterDefaultSetting(int userid, String workDay,
			String workDayHour, String StoreId, String WorkShop, String Address)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();

			String sql = "update usersubinfo set workday = '" + workDay
					+ "' , dayworkhour = '" + workDayHour + "' , storeid = '"
					+ StoreId + "' , storename = '" + WorkShop
					+ "' , storeaddress = '" + Address + "' where userid = "
					+ userid + ";";

			Query query = session.createSQLQuery(sql);

			if (query.executeUpdate() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public List<UsersubinfoModel> searchSubUserinfoByStoreId(int storeId)
			throws BaseException {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();

		String hql = "From UsersubinfoModel where storeid=?";
		Query query = session.createQuery(hql);
		query.setInteger(0, storeId);
		List<UsersubinfoModel> list = query.list();
		List<UserinfoModel> l = new ArrayList<UserinfoModel>();
		for (UsersubinfoModel usersubinfoModel : list) {
			String hql1 = "from UserinfoModel where userid=?";
			Query q = session.createQuery(hql1);
			q.setInteger(0, usersubinfoModel.getUserid());
			UserinfoModel userinfoModel = (UserinfoModel) q.list().get(0);
			usersubinfoModel.setNickname(userinfoModel.getNickname());
		}
		return list;
	}

}
