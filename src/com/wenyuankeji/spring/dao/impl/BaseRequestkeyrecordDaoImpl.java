package com.wenyuankeji.spring.dao.impl;

import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.wenyuankeji.spring.dao.IBaseRequestKeyRecordDao;
import com.wenyuankeji.spring.model.BaseRequestKeyRecord;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class BaseRequestkeyrecordDaoImpl implements IBaseRequestKeyRecordDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public int addBaseRequestKeyRecord(BaseRequestKeyRecord o) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			Integer id = (Integer) session.save(o);
			return id;
		}catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public boolean selectBaseRequestKeyRecord(String keystring) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM BaseRequestKeyRecord WHERE keystring=? ";
			Query query = session.createQuery(hql);
			query.setString(0, keystring);
			
			if (query.list() !=null && query.list().size() > 0) {
				return true;
			}
		}catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		return false;
	}

}
