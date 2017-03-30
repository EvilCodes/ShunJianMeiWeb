package com.wenyuankeji.spring.dao.impl;

import javax.annotation.Resource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.wenyuankeji.spring.dao.IBaseWechatTradelogDao;
import com.wenyuankeji.spring.model.BaseWechatTradelogModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class BaseWechatTradelogDaoImpl implements IBaseWechatTradelogDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public boolean addBaseWechatTradelog(BaseWechatTradelogModel o) throws BaseException {
		try {
			
			Session session = sessionFactory.getCurrentSession();
			Integer id = (Integer) session.save(o);
			System.out.println("微信支付添加条数-------------------------："+id);
			return id > 0;
			
		}catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

}
