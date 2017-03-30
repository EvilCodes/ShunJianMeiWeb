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

import com.wenyuankeji.spring.dao.IUserTradelogDao;
import com.wenyuankeji.spring.model.UserTradelogModel;
import com.wenyuankeji.spring.model.UserTradelogSearchTXModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class UserTradelogDaoImpl implements IUserTradelogDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<UserTradelogModel> searchUserTradelog(int walletid,int pageSize,int pageIndex,int type,int sort) throws BaseException{
		try {
			Session session = sessionFactory.getCurrentSession();
			StringBuffer sql = new StringBuffer("SELECT logid,IFNULL(walletid,0) AS walletid,IFNULL(orderid,0) AS orderid, ");
			sql.append(" CAST(amount as signed) as amount,logtype,remark,createtime, quickpay FROM user_tradelog ");
			sql.append(" WHERE walletid=? ");
			//交易类型
			if (type == 1) {
				sql.append(" and logtype in (1,2) ");//1支付
			}
			if (type == 2) {
				sql.append(" and logtype=6 ");//2赔偿，  不存在赔偿纪录
			}
			if (type == 3) {
				sql.append(" and logtype=5 ");//3退款
			}
			if (type == 4) {
				sql.append(" and logtype=4 ");//3提现
			}
			//排序
			if (sort == 0) {
				sql.append(" order by createtime desc");
			}else{
				sql.append(" order by createtime asc");
			}
			
			Query query = session.createSQLQuery(sql.toString());
			query.setInteger(0, walletid);
			
			query.setFirstResult((pageIndex - 1) * pageSize);
			query.setMaxResults(pageSize);
			
			@SuppressWarnings("unchecked")
			List<Object[]> objList = (List<Object[]>) query.list();
			List<UserTradelogModel> userTradelogList = new ArrayList<UserTradelogModel>();
			for (Object[] obj : objList) {
				UserTradelogModel userTradelogModel = new UserTradelogModel();
				userTradelogModel.setLogid(Integer.parseInt(obj[0].toString()));
				userTradelogModel.setWalletid(Integer.parseInt(obj[1].toString()));
				userTradelogModel.setOrderid(Integer.parseInt(obj[2].toString()));
				userTradelogModel.setAmount((float)Integer.parseInt(obj[3].toString()));
				userTradelogModel.setLogtype(Integer.parseInt(obj[4].toString()));
				userTradelogModel.setRemark(obj[5].toString());
				// add by jiazhaohui
				// 闪惠标记
				userTradelogModel.setQuickpay(Integer.parseInt(obj[7].toString()));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = sdf.parse(obj[6].toString());
				userTradelogModel.setCreatetime(date);
				
				userTradelogList.add(userTradelogModel);
			}
			//List<UserTradelogModel> userTradelogModels = query.list();
			return userTradelogList;
		}catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public Boolean addUserTradelog(UserTradelogModel o) throws BaseException {

		try {
			Session session = sessionFactory.getCurrentSession();
			String sql = "";
			if (o.getWalletid() == 0) {//钱包ID不存在，不进行添加
				sql = "insert into user_tradelog (orderid,amount,logtype,remark,quickpay,createtime) "
						+ "values("+o.getOrderid()+",'"+o.getAmount()+"','"+o.getLogtype()+"','"+o.getRemark()+
						"','" + o.getQuickpay()+"',SYSDATE())";
		
			}else if (o.getOrderid() == 0) {//订单ID不存在，不进行添加
				sql = "insert into user_tradelog (walletid,amount,logtype,remark,quickpay,createtime) "
						+ "values("+o.getWalletid()+",'"+o.getAmount()+"','"+o.getLogtype()+"','"+o.getRemark()+
						"','" + o.getQuickpay()+"',SYSDATE())";
		
			}else{
				sql = "insert into user_tradelog (walletid,orderid,amount,logtype,remark,quickpay,createtime) "
						+ "values("+o.getWalletid()+","+o.getOrderid()+",'"+o.getAmount()+"','"+o.getLogtype()+"','"+o.getRemark()+
						"','" + o.getQuickpay()+"',SYSDATE())";
			
			}
			Query query = session.createSQLQuery(sql);

			int isok = query.executeUpdate();
			if (isok > 0) {
				return true;
			}
		}catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		return false;	
	}

	@Override
	public List<UserTradelogSearchTXModel> searchUserTradelogTX(int ownerid, int page,
			int rows) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String sql = " SELECT c.`ownername`,c.`bank`, c.`cardnumber` ,b.`amount`,b.`remark`,b.`createtime`";
			sql += " FROM user_wallet AS a";
			sql += " LEFT JOIN user_tradelog AS b";
			sql += " ON a.`walletid` = b.`walletid`";
			sql += " LEFT JOIN storeinfo AS c";
			sql += " ON a.`ownerid` = c.`storeid`";
			sql += " WHERE b.`logtype` IN (4,6)";
			sql += " AND b.`walletid`=" + ownerid;
			sql += " ORDER BY b.`createtime` DESC";
			
			Query query = session.createSQLQuery(sql);
			
			query.setFirstResult((page - 1) * rows);
			query.setMaxResults(rows);
			
			@SuppressWarnings("unchecked")
			List<Object[]> objList = (List<Object[]>) query.list();
			List<UserTradelogSearchTXModel> searchList = new ArrayList<UserTradelogSearchTXModel>();
			for (Object[] obj : objList) {
				UserTradelogSearchTXModel searchModel = new UserTradelogSearchTXModel();
				searchModel.setOwnername(obj[0].toString());
				searchModel.setBank(obj[1].toString());
				searchModel.setCardnumber(obj[2].toString());
				searchModel.setAmount(obj[3].toString());
				//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//Date date = sdf.parse(obj[4].toString());
				searchModel.setRemark(obj[4].toString());
				searchModel.setCreatetime(obj[5].toString().substring(0, obj[5].toString().length() - 2));				
				searchList.add(searchModel);
			}
			return searchList;
		}catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public int searchUserTradelogTX(int ownerid) throws BaseException {
		int result = 0;
		try {
			Session session = sessionFactory.getCurrentSession();
			String sql = " SELECT c.`ownername`,c.`bank`, c.`cardnumber` ,b.`amount`,b.`createtime`";
			sql += " FROM user_wallet AS a";
			sql += " LEFT JOIN user_tradelog AS b";
			sql += " ON a.`walletid` = b.`walletid`";
			sql += " LEFT JOIN storeinfo AS c";
			sql += " ON a.`ownerid` = c.`storeid`";
			sql += " WHERE b.`logtype`=4";
			sql += " AND a.`ownerid`=" + ownerid;
			
			Query query = session.createSQLQuery(sql);
			
			@SuppressWarnings("unchecked")
			List<Object[]> objList = (List<Object[]>) query.list();
			if(objList != null && objList.size() > 0){
				result = objList.size();
			}
		}catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		return result;
	}

	@Override
	public boolean searchUserTradelogByOrderId(int orderid, int logtype) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserTradelogModel WHERE orderid=? and logtype = ? ";
			Query query = session.createQuery(hql);
			query.setInteger(0, orderid);
			query.setInteger(1, logtype);
			
			if (null != query.list() && query.list().size() > 0) {
				return true;
			}
		}catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		return false;
	}

}
