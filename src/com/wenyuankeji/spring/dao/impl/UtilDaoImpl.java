package com.wenyuankeji.spring.dao.impl;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.wenyuankeji.spring.dao.IUtilDao;
import com.wenyuankeji.spring.model.BaseRequestlog;
import com.wenyuankeji.spring.model.BaseException;

@Repository
public class UtilDaoImpl implements IUtilDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@Override
	public void addBaseRequestlog(BaseRequestlog o) {
		try {
			
			Session session = sessionFactory.getCurrentSession();
			session.save(o);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int addBaseException(BaseException o) {
		
		try {
			
			Session session = sessionFactory.getCurrentSession();
			session.save(o);
			//查询异常id
			String sql = "SELECT id from  base_exception ORDER BY createtime DESC LIMIT 1";
			Query query = session.createSQLQuery(sql);

			@SuppressWarnings("unchecked")
			List<Object> oList = query.list();
			
			if (null != oList && oList.size() > 0) {
				return 1 + Integer.parseInt(oList.get(0).toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
}
