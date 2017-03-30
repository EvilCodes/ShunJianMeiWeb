package com.wenyuankeji.spring.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IOrderinfoDao;
import com.wenyuankeji.spring.dao.IStoreWorkHourDao;
import com.wenyuankeji.spring.dao.IStoreWorkdateDao;
import com.wenyuankeji.spring.dao.IUserWorkdateDao;
import com.wenyuankeji.spring.dao.IUserWorkhourDao;
import com.wenyuankeji.spring.model.OrderdetailModel;
import com.wenyuankeji.spring.model.OrderinfoModel;
import com.wenyuankeji.spring.model.StoreWorkdateModel;
import com.wenyuankeji.spring.model.StoreWorkhourModel;
import com.wenyuankeji.spring.model.UserTradelogSearchModel;
import com.wenyuankeji.spring.model.UserWorkdateModel;
import com.wenyuankeji.spring.model.UserWorkhourModel;
import com.wenyuankeji.spring.util.BaseException;
import com.wenyuankeji.spring.util.ShunJianMeiUtil;
import com.wenyuankeji.spring.util.Constant;
@Repository
public class OrderinfoDaoImpl implements IOrderinfoDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	private IStoreWorkHourDao storeWorkHourDao;

	@Autowired
	private IUserWorkhourDao userWorkhourDao;
	
	@Autowired
	private IUserWorkdateDao userWorkdateDao;
	
	@Autowired
	private IStoreWorkdateDao storeWorkdateDao;
	

	@Override
	public OrderinfoModel searchOrderinfo(int orderid) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM OrderinfoModel WHERE orderid=?";
			Query query = session.createQuery(hql);
			query.setInteger(0, orderid);

			OrderinfoModel orderinfoModel = null;
			if (null != query.list() && query.list().size() > 0) {
				orderinfoModel = (OrderinfoModel) query.list().get(0);
			}
			return orderinfoModel;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public List<OrderinfoModel> searchOrderinfo(String orderids)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM OrderinfoModel WHERE orderid in (" + orderids
					+ ")";
			Query query = session.createQuery(hql);

			@SuppressWarnings("unchecked")
			List<OrderinfoModel> orderinfoList = query.list();

			return orderinfoList;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public List<OrderdetailModel> searchOrderdetail(int orderid)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM OrderdetailModel WHERE orderid=?";
			Query query = session.createQuery(hql);
			query.setInteger(0, orderid);

			@SuppressWarnings("unchecked")
			List<OrderdetailModel> orderdetailModels = query.list();
			return orderdetailModels;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public int addOrderinfo(OrderinfoModel orderinfo, String StoreID, double Times, String WHID) throws BaseException {

		// 判断工时是否被占用
		// 预约时间
		UserWorkhourModel uwh = userWorkhourDao
				.searchUserWorkhour(Integer.parseInt(WHID));
		int hours = ShunJianMeiUtil.getHour(Times);
		for (int i = 0; i < hours; i++) {
			UserWorkhourModel checkUwh = userWorkhourDao
					.searchUserWorkhour(0, uwh.getWdid(), uwh.getHour()
							+ i);
			// 9点以后为最后一个时间段不需要判断工时是否被占用。
			if ((uwh.getHour() + i) <= 21) {
				if (null == checkUwh || checkUwh.getState() != 0) {
					// 工时已经被占用
					return -1;
				}
			}
		}
		
//		UserWorkdateModel userWorkdateModel = userWorkdateDao
//				.searchUserWorkdate(uwh.getWdid());
//		StoreWorkdateModel storeWorkdateModel = storeWorkdateDao
//				.searchStoreWorkdate(Integer.valueOf(StoreID),
//						userWorkdateModel.getYear(),
//						userWorkdateModel.getMonth(),
//						userWorkdateModel.getDay());
//		if (storeWorkdateModel == null) {
//			return -2;
//		}
//		for (int i = 0; i < hours; i++) {
//			List<StoreWorkhourModel> storeWorkhourModelList = storeWorkHourDao
//					.searchStoreHourInfo(storeWorkdateModel.getWdid(),
//							uwh.getHour() + i);
			// 9点以后为最后一个时间段不需要判断工时是否被占用。
//			if ((uwh.getHour() + i) <= 21) {
//				if (null == storeWorkhourModelList
//						|| storeWorkhourModelList.size() == 0) {
//					// 工位已经被占用
//					return -2;
//				}
//			}
//		}
		
		int orderid = 0;
		try {
			Session session = sessionFactory.getCurrentSession();
			orderid = (Integer) session.save(orderinfo);
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}

		return orderid;
	}

	@Override
	public boolean addOrderdetail(Set<OrderdetailModel> orderdetails,
			int orderid) throws BaseException {
		int count = 0;

		try {
			Session session = sessionFactory.getCurrentSession();
			for (OrderdetailModel o : orderdetails) {
				o.setOrderid(orderid);
				session.save(o);
				count++;
			}

		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}

		return count == orderdetails.size();
	}

	@Override
	public List<OrderinfoModel> searchOrderinfoStoreId(int storeid, int page,
			int rows) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "FROM OrderinfoModel WHERE storeid=" + storeid;

			Query query = session.createQuery(hql);

			query.setFirstResult((page - 1) * rows);
			query.setMaxResults(rows);

			@SuppressWarnings("unchecked")
			List<OrderinfoModel> orderinfoList = (List<OrderinfoModel>) query
					.list();

			return orderinfoList;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public int searchOrderinfoByStoreId(int storeid,
			int orderByType, int orderByVal) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = " SELECT b.`ordercode`,b.`stylename`,b.`appointmenttime`,a.`amount`,a.`createtime`";
			hql += " FROM user_tradelog AS a";
			hql += " INNER JOIN orderinfo AS b ON a.`orderid`=b.`orderid`";
			hql += " INNER JOIN user_wallet c ON a.`walletid`=c.`walletid`";
			hql += " WHERE logtype=3 AND c.`ownertype`=3 ";
			hql += " AND b.`storeid`=" + storeid;

			if (orderByType != 0) {
				// 状态
				hql += " and b.`paystate` = " + orderByType;
			}

			if (orderByVal == 1) {
				// 升序
				hql += " order by a.`createtime` desc ";
			} else {
				// 降序
				hql += " order by a.`createtime` asc ";
			}

			Query query = session.createSQLQuery(hql);

			@SuppressWarnings("unchecked")
			List<Object[]> objList = (List<Object[]>) query.list();
			
			if(objList != null && objList.size() > 0){
				return objList.size();
			}else{
				return 0;
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	@Override
	public List<UserTradelogSearchModel> searchOrderinfoByStoreId(int storeid,
			int orderByType, int orderByVal, int page,
			int rows) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			
			String hql = " SELECT b.`ordercode`,b.`stylename`,b.`appointmenttime`,a.`amount`,a.`createtime`";
			hql += " FROM user_tradelog AS a";
			hql += " INNER JOIN orderinfo AS b ON a.`orderid`=b.`orderid`";
			hql += " INNER JOIN user_wallet c ON a.`walletid`=c.`walletid`";
			hql += " WHERE logtype=3 AND c.`ownertype`=3 ";
			hql += " AND b.`storeid`=" + storeid;

			if (orderByType != 0) {
				// 状态
				hql += " and b.`paystate` = " + orderByType;
			}

			if (orderByVal == 1) {
				// 升序
				hql += " order by a.`createtime` desc ";
			} else {
				// 降序
				hql += " order by a.`createtime` asc ";
			}

			Query query = session.createSQLQuery(hql);
			query.setFirstResult((page - 1) * rows);
			query.setMaxResults(rows);

			@SuppressWarnings("unchecked")
			List<Object[]> objList = (List<Object[]>) query.list();
			List<UserTradelogSearchModel> searchModelList = new ArrayList<UserTradelogSearchModel>();
			for (Object[] obj : objList) {
				UserTradelogSearchModel searchModel = new UserTradelogSearchModel();
				searchModel.setOrdercode(obj[0].toString());
				searchModel.setStylename(obj[1].toString());
				searchModel.setAppointmenttime(obj[2].toString().substring(0, obj[2].toString().length() - 2));
				searchModel.setAmount(obj[3].toString());
				searchModel.setCreatetime(obj[4].toString().substring(0, obj[4].toString().length() - 2));
				searchModelList.add(searchModel);
			}

			return searchModelList;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public int searchOrderinfoStoreId(int storeid) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "FROM OrderinfoModel WHERE storeid=" + storeid;

			Query query = session.createQuery(hql);

			@SuppressWarnings("unchecked")
			List<OrderinfoModel> orderinfoList = (List<OrderinfoModel>) query
					.list();

			return orderinfoList.size();
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	// 更新订单状态
	@Override
	public boolean updateOrderinfoModel(int orderid, int paystate)
			throws BaseException {

		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "";

			switch (paystate) {
				//支付
				case 3:
					hql = "UPDATE OrderinfoModel SET paystate=?,paytime=SYSDATE() WHERE orderid = ? ";
					break;
				//签到
				case 4:
					hql = "UPDATE OrderinfoModel SET paystate=?,signtime=SYSDATE() WHERE orderid = ? ";
					break;
				//服务开始
				case 5:
					hql = "UPDATE OrderinfoModel SET paystate=?,servicebegintime=SYSDATE() WHERE orderid = ? ";
					break;
				//服务结束
				case 6:
					hql = "UPDATE OrderinfoModel SET paystate=?,serviceendtime=SYSDATE() WHERE orderid = ? ";
					break;
				//完成
				case 8:
					hql = "UPDATE OrderinfoModel SET paystate=?,completetime=SYSDATE() WHERE orderid = ? ";
					break;
				default:
					hql = "UPDATE OrderinfoModel SET paystate=? WHERE orderid = ? ";
					break;
			}

			Query query = session.createQuery(hql);

			query.setInteger(0, paystate);
			query.setInteger(1, orderid);

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
	public List<OrderinfoModel> searchOrderList(String userID, String state,
			String sort, int pageIndex, int pageSize) throws BaseException {

		try {

			Session session = sessionFactory.getCurrentSession();

			String hql = "FROM OrderinfoModel WHERE customerid='" + userID
					+"' and paystate != "+Constant.PAYSTE_CANCELL_09 ;
			if (!"0".equals(state)) {
				hql += " AND paystate = " + state ;
			}

			hql += " order by appointmenttime  ";

			if ("1".equals(sort)) {
				hql += " desc ";
			} else {
				hql += " asc ";
			}

			Query query = session.createQuery(hql);

			query.setFirstResult((pageIndex - 1) * pageSize);
			query.setMaxResults(pageSize);

			@SuppressWarnings("unchecked")
			List<OrderinfoModel> orderinfoList = (List<OrderinfoModel>) query
					.list();

			return orderinfoList;

		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}

	}

	// 加单
	@Override
	public boolean updateOrderinfo(int orderid, int customerid,
			String additionalcontent, double additionalamount)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "UPDATE OrderinfoModel SET additionalcontent=?,additionalamount=?,additionalstate=1, additionalcode=? WHERE orderid = ? AND userid=? ";
			Query query = session.createQuery(hql);

			query.setString(0, additionalcontent);
			query.setDouble(1, additionalamount);
			query.setString(2, ShunJianMeiUtil.getRandomCode());
			query.setInteger(3, orderid);
			query.setInteger(4, customerid);
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
	public List<String> searchOrderServicenameList(String orderid)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();

			String sql = "select t2.servicename, t1.materialname from  orderdetail t1 ";
			sql += "INNER JOIN serviceattribute t2 ON ";
			sql += "t1.servicecode = t2.servicecode ";
			sql += "where t1.orderid = '" + orderid + "' ";

			Query query = session.createSQLQuery(sql);

			List<String> servicenameList = new ArrayList<String>();
			@SuppressWarnings("unchecked")
			List<Object[]> objList = (List<Object[]>) query.list();
			// List<String> servicenameList = query.list();
			for (Object[] obj : objList) {
				if (obj[1] == null) {
					servicenameList.add(obj[0].toString());
				} else {
					servicenameList.add(obj[0].toString() + "("
							+ obj[1].toString() + ")");
				}
			}

			return servicenameList;

		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public List<OrderinfoModel> searchOrderListByUserID(String userID,
			String state, String sort, int pageIndex, int pageSize)
			throws BaseException {
		try {

			Session session = sessionFactory.getCurrentSession();

			String hql = "FROM OrderinfoModel WHERE userid=" + userID+"and paystate!="+Constant.PAYSTE_CANCELL_09;
			if (!"0".equals(state)) {
				hql += " AND paystate = " + state;
			}

			hql += " order by appointmenttime  ";

			if ("1".equals(sort)) {
				hql += " desc ";
			} else {
				hql += " asc ";
			}

			Query query = session.createQuery(hql);
			query.setFirstResult((pageIndex - 1) * pageSize);
			query.setMaxResults(pageSize);

			@SuppressWarnings("unchecked")
			List<OrderinfoModel> orderinfoList = (List<OrderinfoModel>) query
					.list();

			return orderinfoList;

		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public List<OrderinfoModel> searchOrderinfoByUseridAndAppointmenttime(
			String userID, String appointmenttime) throws BaseException {

		try {

			Session session = sessionFactory.getCurrentSession();

			String hql = "FROM OrderinfoModel WHERE DATE_FORMAT(appointmenttime,'%Y-%c-%d') = DATE_FORMAT('"+ appointmenttime +"','%Y-%c-%d') and userid = ? ";

			Query query = session.createQuery(hql);

			query.setString(0, userID);

			@SuppressWarnings("unchecked")
			List<OrderinfoModel> orderinfoList = (List<OrderinfoModel>) query
					.list();

			return orderinfoList;

		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public List<Integer> searchOrderIdByWdid(int wdid) throws BaseException {
		try {

			Session session = sessionFactory.getCurrentSession();

			String sql = "select orderid  from store_workhour where wdid = '"
					+ wdid
					+ "'  and orderid != '0' GROUP BY orderid ORDER BY orderid";

			Query query = session.createSQLQuery(sql);

			@SuppressWarnings("unchecked")
			List<Integer> orderids = query.list();
			return orderids;

		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}

	}

	@Override
	public boolean updateOrderinfoCouponAmount(int orderid,
			double couponamount, double amount) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "UPDATE OrderinfoModel SET couponamount=?, amount=? WHERE orderid=? ";
			Query query = session.createQuery(hql);

			query.setDouble(0, couponamount);
			query.setDouble(1, amount);
			query.setInteger(2, orderid);
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
	public boolean updateCountOrderNum(int userid) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "SELECT COUNT(1) FROM orderinfo WHERE userid=? AND paystate IN (6,7,8)";
			Query query = session.createSQLQuery(hql);
			query.setInteger(0, userid);
			@SuppressWarnings("unchecked")
			List<Object> numList = query.list();
			if (numList.size() > 0) {
				int num = Integer.parseInt(numList.get(0).toString());
				String hqlUpdate = "UPDATE usersubinfo SET ordernum=" + num + " WHERE userid= " + userid;
				Query queryUpdate = session.createSQLQuery(hqlUpdate);
				int isok = queryUpdate.executeUpdate();
				if(isok > 0){
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public boolean updateCountStoreNum(int storeid) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "SELECT COUNT(1) FROM orderinfo WHERE storeid=? AND paystate IN (6,7,8)";
			Query query = session.createSQLQuery(hql);
			query.setInteger(0, storeid);
			@SuppressWarnings("unchecked")
			List<Object> numList = query.list();
			if (numList.size() > 0) {
				int num = Integer.parseInt(numList.get(0).toString());
				String hqlUpdate = "UPDATE storeinfo SET orderquantity=" + num + " WHERE storeid= " + storeid;
				Query queryUpdate = session.createSQLQuery(hqlUpdate);
				int isok = queryUpdate.executeUpdate();
				if(isok > 0){
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public boolean updateCountUsedNum(int hairstyleid) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "SELECT COUNT(1) FROM orderinfo WHERE hairstyleid = ? AND paystate IN (6,7,8)";
			Query query = session.createSQLQuery(hql);
			query.setInteger(0, hairstyleid);
			@SuppressWarnings("unchecked")
			List<Object> numList = query.list();
			if (numList.size() > 0) {
				int num = Integer.parseInt(numList.get(0).toString());
				String hqlUpdate = "UPDATE user_myhairstyle SET usednum=" + num + " WHERE mystyleid= " + hairstyleid;
				Query queryUpdate = session.createSQLQuery(hqlUpdate);
				int isok = queryUpdate.executeUpdate();
				if(isok > 0){
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderinfoModel> searchOrderInfoWaitTime(String ordertime) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM OrderinfoModel WHERE paystate = 1 AND (UNIX_TIMESTAMP(SYSDATE()) - UNIX_TIMESTAMP(createtime)) / 60 >= ?";
			Query query = session.createQuery(hql);
			query.setString(0, ordertime);
			
			List<OrderinfoModel> orderinfoModels = null;
			
			if (null != query.list() && query.list().size() > 0) {
				orderinfoModels = query.list();
			}
			return orderinfoModels;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public boolean updateAdditionalInfo(String additionalcode) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hqlUpdate = "UPDATE orderinfo SET additionalstate=2,additionaltime=NOW()  WHERE additionalcode= " + additionalcode;
			Query queryUpdate = session.createSQLQuery(hqlUpdate);
			int isok = queryUpdate.executeUpdate();
			if(isok > 0){
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public OrderinfoModel searchOrderinfoByAdditionalcode(String additionalcode) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM OrderinfoModel WHERE additionalcode=?";
			Query query = session.createQuery(hql);
			query.setString(0, additionalcode);

			OrderinfoModel orderinfoModel = null;
			if (null != query.list() && query.list().size() > 0) {
				orderinfoModel = (OrderinfoModel) query.list().get(0);
			}
			return orderinfoModel;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public boolean updateOrderPayType(int orderid, int type)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hqlUpdate = "UPDATE orderinfo SET paytype=" + type + " WHERE orderid= " + orderid;
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

	@Override
	public boolean updateAdditionalPaytype(String additionalcode, int additionalpaytype)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hqlUpdate = "UPDATE orderinfo SET additionalpaytype=" + additionalpaytype + " WHERE additionalcode= " + additionalcode;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderinfoModel> searchOrderInfoByAllocationtime(String allocationtime) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			//1800等于30分钟
			//86400等于24小时
			String hql = "FROM OrderinfoModel WHERE paystate IN (6,7) AND UNIX_TIMESTAMP(SYSDATE()) - UNIX_TIMESTAMP(serviceendtime) > ?";
			Query query = session.createQuery(hql);
			query.setString(0, allocationtime);
			
			List<OrderinfoModel> orderinfoModels = null;
			
			if (null != query.list() && query.list().size() > 0) {
				orderinfoModels = query.list();
			}
			return orderinfoModels;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public int getMaterialidpriceTotal(int orderid) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "SELECT CAST(SUM(materialidprice) as signed) as materialidpriceTotal,orderid FROM orderdetail WHERE orderid=?";
			Query query = session.createSQLQuery(hql);
			query.setInteger(0, orderid);
			int materialidpriceTotal = 0;	
			
			@SuppressWarnings("unchecked")
			List<Object[]> priceList = (List<Object[]>) query.list();
			for (Object[] obj : priceList) {
				materialidpriceTotal = Integer.parseInt(obj[0].toString());
			}
			return materialidpriceTotal;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public boolean updateOrderinfoPayState(int orderid, int paystate) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hqlUpdate = "UPDATE orderinfo SET paystate=" + paystate + ",completetime=SYSDATE() WHERE orderid= " + orderid;
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

	@Override
	public List<OrderinfoModel> searchFinishOrderListByUserID(String userID,
			String state, String sort, int pageIndex, int pageSize)
			throws BaseException {
		try {

			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM OrderinfoModel WHERE userid='" + userID+"' and (paystate='"+Constant.PAYSTE_SERVICE_FINISH_06+"' or paystate='"+Constant.PAYSTE_EVALUATE_FINISH_07+"' or paystate='"+Constant.PAYSTE_ORDER_FINISH_08+"')";
//			if (!"0".equals(state)) {
//				hql += " AND paystate = " + state;
//			}

			hql += " order by appointmenttime  ";

			if ("1".equals(sort)) {
				hql += " desc ";
			} else {
				hql += " asc ";
			}

			Query query = session.createQuery(hql);
			query.setFirstResult((pageIndex - 1) * pageSize);
			query.setMaxResults(pageSize);

			@SuppressWarnings("unchecked")
			List<OrderinfoModel> orderinfoList = (List<OrderinfoModel>) query
					.list();

			return orderinfoList;

		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
}
