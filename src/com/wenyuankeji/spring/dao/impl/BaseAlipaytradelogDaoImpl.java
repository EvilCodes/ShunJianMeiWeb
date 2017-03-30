package com.wenyuankeji.spring.dao.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IBaseAlipaytradelogDao;
import com.wenyuankeji.spring.model.BaseAlipaytradelogModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class BaseAlipaytradelogDaoImpl implements IBaseAlipaytradelogDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public boolean addBaseAlipaytradelog(String orderid, String content, int quickpay)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			
			BaseAlipaytradelogModel o = new BaseAlipaytradelogModel();
			o.setOrderid(orderid);
			o.setContent(content);
			o.setQuickpay(quickpay);
			o.setCreatetime(new Date());
			
			Integer id = (Integer) session.save(o);
			
			System.out.println("支付宝请求添加条数-------------------------："+id);
			
			return id > 0;
		}catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

}
