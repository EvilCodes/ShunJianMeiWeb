package com.wenyuankeji.spring.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IUserEditWorkDao;
import com.wenyuankeji.spring.model.UserEditWorkModel;
import com.wenyuankeji.spring.util.BaseException;
import com.wenyuankeji.spring.util.Constant;

@Repository
public class UserEditWorkDaoImpl implements IUserEditWorkDao 
{

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	/**
	 * 根据订单ID查找订单信息
	 * @param orderid 订单ID
	 * @return
	 * @throws BaseException
	 */
	@Override
	public UserEditWorkModel searchUserEditWorkModel(int orderid)throws BaseException
	{
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "FROM UserEditWorkModel WHERE orderid = ? ";
			Query query = session.createQuery(hql);
			query.setInteger(0, orderid);
			
			@SuppressWarnings("unchecked")
			List<UserEditWorkModel> objList = query.list();
			
			if (null != objList && objList.size() > 0) {
				return objList.get(0);
			}
			return null;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	/**
	 * 更新自编辑订单
	 * @param orderid 订单ID
	 * @return
	 * @throws BaseException
	 */
	@Override
	public int updateOrder(Map<String, Object> inputJsonMap) throws BaseException
	{
		Session session = sessionFactory.getCurrentSession();
		try {
			String orderID = null;
			if (inputJsonMap.get("OrderID") != null)
			{
				orderID = inputJsonMap.get("OrderID").toString();
			}
			String content = inputJsonMap.get("Content").toString();
			int starthour = Integer.parseInt(inputJsonMap.get("StartHour").toString());
			int lasthour = Integer.parseInt(inputJsonMap.get("LastHour").toString());
			int endhour = starthour + lasthour;
			if (endhour > Constant.SERVICE_END_HOUR)
			{
				endhour = Constant.SERVICE_END_HOUR;
			}
			String name = inputJsonMap.get("Name").toString();
			String mobile = inputJsonMap.get("Mobile").toString();
			
			if (orderID == null)
			{
				// 新建
				UserEditWorkModel userEditWork = new UserEditWorkModel();
				Date createtime = new Date();
				userEditWork.setStarthour(starthour);
				userEditWork.setLasthour(lasthour);
				userEditWork.setEndhour(endhour);
				userEditWork.setName(name);
				userEditWork.setMobile(mobile);
				userEditWork.setContent(content);
				userEditWork.setCreatetime(createtime);
				
				Serializable dbid = session.save(userEditWork);
				return Integer.parseInt(dbid.toString());
				
			}
			else
			{
				// 更新
				int orderid = Integer.parseInt(orderID);
				String hqlUpdate = "UPDATE user_editwork SET starthour = " +"'"+ starthour+"'" +
						" , lasthour = " + "'"+lasthour+"'" +
						" , endhour = " + "'"+endhour +"'"+
						" , name = " +"'"+ name + "'"+
						" , mobile = " +"'"+ mobile + "'"+
						" , content = " +"'"+ content + "'"+
						" WHERE orderid = " + "'"+orderid+"'";
				
				Query queryUpdate = session.createSQLQuery(hqlUpdate);
				
				if(queryUpdate.executeUpdate() > 0){
					return orderid;
				}

			}
			
			return Constant.DB_INSERT_ERROR;
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
}
