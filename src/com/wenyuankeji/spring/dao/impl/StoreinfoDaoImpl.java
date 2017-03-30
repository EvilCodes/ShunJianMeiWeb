package com.wenyuankeji.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IStoreinfoDao;
import com.wenyuankeji.spring.model.StoreWorkdateModel;
import com.wenyuankeji.spring.model.StoreWorkhourModel;
import com.wenyuankeji.spring.model.StoreinfoModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class StoreinfoDaoImpl implements IStoreinfoDao{

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	
	@Override
	public StoreinfoModel searchStoreinfo(int storeid) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "FROM StoreinfoModel WHERE storeid=? ";
			Query query = session.createQuery(hql);

			query.setInteger(0, storeid);

			StoreinfoModel storeinfo = null;
			
			@SuppressWarnings("unchecked")
			List<Object> objList = query.list();
			
			if (null != objList && objList.size() > 0) {
				storeinfo = (StoreinfoModel) objList.get(0);
			}

			return storeinfo;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	@Override
	public List<StoreinfoModel> searchStoreinfo(String cityid, String score,
			String orderquantity, double longitude, double latitude, String carnumber,
			int pageSize, int pageIndex,String Type,String Infversion) throws BaseException {
	  try{
		  
	  
         Session session = sessionFactory.getCurrentSession();
		
         StringBuffer sbBuffer = new StringBuffer("FROM StoreinfoModel where state=2 AND cityid=?");
         if(Infversion.equals("1.0")){
 			sbBuffer.append(" AND istype=?");
         } 
		
		//排序类型 
        if (Type.equals("1")) {//按星级
        	if(score.equals("1")){
    			sbBuffer.append(" order by Score desc,sort DESC");
    		}else{
    			sbBuffer.append(" order by Score asc,sort DESC");
    		}
		}else if (Type.equals("2")) {//按订单
			if(orderquantity.equals("1")){
				sbBuffer.append(" order by orderquantity desc,sort DESC");
			}else{
				sbBuffer.append(" order by orderquantity asc,sort DESC");
			}
		}else if (Type.equals("3")) {//按距离
			//sbBuffer.append(" order by orderquantity desc");
			//sbBuffer.append(" order by sort DESC");
		}else if (Type.equals("4")) {//按车位数
			if(carnumber.equals("1")){
				sbBuffer.append(" order by carnumber desc,sort DESC");
			}else{
				sbBuffer.append(" order by carnumber asc,sort DESC");
			}
		}
		
		Query query = session.createQuery(sbBuffer.toString());
		query.setString(0, cityid);
		if(Infversion.equals("1.0")){
			query.setString(1, "0");
        }
		query.setFirstResult((pageIndex - 1) * pageSize);
		query.setMaxResults(pageSize);
		
		@SuppressWarnings("unchecked")
		List<StoreinfoModel> storeinfoModel = (List<StoreinfoModel>)query.list();
		return storeinfoModel;
	         
       } catch(Exception e) {
	         throw new BaseException(e.getMessage());
       }
    }

	@Override
	public boolean updateStoreinfo(StoreinfoModel storeinfo)
			throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			StringBuffer hql = new StringBuffer();
			hql.append("UPDATE StoreinfoModel SET ");
			hql.append("name=?, ");
			hql.append("provinceid=?, ");
			hql.append("cityid=?, ");
			hql.append("districtid=?, ");
			hql.append("address=?, ");
			hql.append("tel=?, ");
			hql.append("carnumber=?, ");
			hql.append("businesshours=?, ");
			hql.append("areaid=?, ");
			hql.append("empiricalmode=?, ");
			hql.append("intro=?, ");
			hql.append("bossname=?, ");
			hql.append("bossmobile=?, ");
			hql.append("storemanagername=?, ");
			hql.append("storemanagermobile=?, ");
			hql.append("state=?, ");
			hql.append("longitude=?, ");
			hql.append("latitude=?, ");
			hql.append("ownername=?, ");
			hql.append("bank=?, ");
			hql.append("cardnumber=? ");
			hql.append("WHERE ");
			hql.append("storeid=? ");
			Query query = session.createQuery(hql.toString());

			query.setString(0, storeinfo.getName());
			query.setInteger(1, storeinfo.getProvinceid());
			query.setInteger(2, storeinfo.getCityid());
			query.setInteger(3, storeinfo.getDistrictid());
			query.setString(4, storeinfo.getAddress());
			query.setString(5, storeinfo.getTel());
			query.setInteger(6, storeinfo.getCarnumber());
			query.setString(7, storeinfo.getBusinesshours());
			query.setInteger(8, storeinfo.getAreaid());		
			query.setInteger(9, storeinfo.getEmpiricalmode());
			query.setString(10, storeinfo.getIntro());
			query.setString(11, storeinfo.getBossname());
			query.setString(12, storeinfo.getBossmobile());
			query.setString(13, storeinfo.getStoremanagername());
			query.setString(14, storeinfo.getStoremanagermobile());
			query.setInteger(15, storeinfo.getState());
			query.setString(16, storeinfo.getLongitude());
			query.setString(17, storeinfo.getLatitude());
			query.setString(18, storeinfo.getOwnername());
			query.setString(19, storeinfo.getBank());
			query.setString(20, storeinfo.getCardnumber());
			
			query.setInteger(21, storeinfo.getStoreid());

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
	public boolean updateScore(int storeid,int score) throws BaseException {
		try {
			
			Session session = sessionFactory.getCurrentSession();
			String sql = "update storeinfo set score = "+score+" where storeid = '"+storeid+"'";
			
			Query query = session.createSQLQuery(sql);
			
			if (query.executeUpdate() > 0) {
				return true;
			}else {
				return false;
			}
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	
	@Override
	public List<StoreinfoModel> searchStoreinfoByAreaid(String areaid, int state, String storeIdStr)
			throws BaseException {
		
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM StoreinfoModel WHERE state=" + state;
			
			/*if(storeIdStr.equals("")){
				hql += " AND storeid IN ('')";
			}else{
				hql += " AND storeid IN (" + storeIdStr + ")";
			}*/
			
			if (!"0".equals(areaid)) {
				hql += "AND areaid = " + areaid;
			}
			
			Query query = session.createQuery(hql);


			@SuppressWarnings("unchecked")
			List<StoreinfoModel> storeinfos = query.list();
			
			if (null != storeinfos && storeinfos.size() > 0) {
				return storeinfos;
			}

			return null;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public int addStoreWorkdate(StoreWorkdateModel o) throws BaseException {
		try {
			
			Session session = sessionFactory.getCurrentSession();
			Integer wdid = (Integer) session.save(o);
			return wdid;
					
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public boolean addStoreWorkhour(StoreWorkhourModel o) throws BaseException {
		try {
			
			Session session = sessionFactory.getCurrentSession();
			session.save(o);
			return true;
					
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public StoreWorkdateModel searchStoreWorkdate(String year, String month, String day,
			int storeid) throws BaseException {
		try {
			
			Session session = sessionFactory.getCurrentSession();
			String hql = "from StoreWorkdateModel where year = ? and month = ? and day = ? and storeid = ? ";
			Query query = session.createQuery(hql);
			query.setString(0, year);
			query.setString(1, month);
			query.setString(2, day);
			query.setInteger(3, storeid);
			
			@SuppressWarnings("unchecked")
			List<Object> objects = query.list();
			if (objects != null && objects.size() > 0) {
				return (StoreWorkdateModel) objects.get(0);
			}
			
			return null;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public List<Object[]> searchGongWeiByGongZuoRi(String year, String month, String day,
			int storeid) throws BaseException {
		try {
			
			Session session = sessionFactory.getCurrentSession();
			String sql = "select `hour`,COUNT(chairid) as chairidCount ,SUM(orderid) as orderid,wdid from store_workhour  ";
			sql += "where wdid in (select wdid from store_workdate where `year` = '"+year+"' and `month` = '"+month+"' and `day` = '"+day+"' and storeid = '"+storeid+"') ";
			sql += "GROUP BY `hour` ORDER BY chairid,`hour` ";
			Query query = session.createSQLQuery(sql);
			
			@SuppressWarnings("unchecked")
			List<Object[]> objects = query.list();
			
			return objects;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	
	
	@Override
	public boolean deleteStoreWorkhour(int wdid, int chairid)
			throws BaseException {
		try {
			
			Session session = sessionFactory.getCurrentSession();
			String sql = "delete from store_workhour where state = 0 and wdid = '"+wdid+"' and chairid = '"+chairid+"' ";
			Query query = session.createSQLQuery(sql);
			
			if (query.executeUpdate() > 0) {
				return true;
			}
			
			return false;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public boolean deleteStoreWorkhourByHour(int wdid,int hour)throws BaseException {
		try {
			
			Session session = sessionFactory.getCurrentSession();
			String sql = "delete from store_workhour where  wdid = '"+wdid+"' and hour = '"+hour+"' ";
			Query query = session.createSQLQuery(sql);
			
			query.executeUpdate();
			return true;
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	@Override
	public List<StoreWorkhourModel> searchStoreWorkhourByState(int wdid)
			throws BaseException {
		try {
			
			Session session = sessionFactory.getCurrentSession();
			String hql = "from StoreWorkhourModel where state != 0 and wdid = ?  ";
			Query query = session.createQuery(hql);
			
			query.setInteger(0, wdid);
			
			@SuppressWarnings("unchecked")
			List<StoreWorkhourModel> objects = query.list();
			if (objects != null && objects.size() > 0) {
				return objects;
			}
			
			return null;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	@Override
	public boolean deleteStoreWorkhourByWdids(String wdids)
			throws BaseException {
		try {
			
			try {
				
				Session session = sessionFactory.getCurrentSession();
				String sql = "delete from store_workhour where wdid in("+wdids+")";
				Query query = session.createSQLQuery(sql);
				
				if (query.executeUpdate() > 0) {
					return true;
				}
				
				return false;
			} catch (Exception e) {
				throw new BaseException(e.getMessage());
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	@Override
	public boolean deleteStoreWorkDateByWdids(String wdids)
			throws BaseException {
		try {
			
			try {
				
				Session session = sessionFactory.getCurrentSession();
				String sql = "delete from store_workdate where wdid in("+wdids+")";
				Query query = session.createSQLQuery(sql);
				
				if (query.executeUpdate() > 0) {
					return true;
				}
				
				return false;
			} catch (Exception e) {
				throw new BaseException(e.getMessage());
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	@Override
	public List<StoreWorkhourModel> searchStoreWorkhour(int wdid)
			throws BaseException {
		try {
			
			Session session = sessionFactory.getCurrentSession();
			String hql = "from StoreWorkhourModel where wdid = ?  ";
			Query query = session.createQuery(hql);
			
			query.setInteger(0, wdid);
			
			@SuppressWarnings("unchecked")
			List<StoreWorkhourModel> objects = query.list();
			if (objects != null && objects.size() > 0) {
				return objects;
			}
			
			return null;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public boolean deleteStoreWorkdate(StoreWorkdateModel o) throws BaseException {
		try {
			
			Session session = sessionFactory.getCurrentSession();
			session.delete(o);
			return true;
					
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public StoreWorkhourModel searchStoreWorkhour(int wdid, int chairid,
			int hour) throws BaseException {
		try {
			
			Session session = sessionFactory.getCurrentSession();
			String hql = "from StoreWorkhourModel where wdid = ? and chairid = ? and hour = ? ";
			Query query = session.createQuery(hql);
			
			query.setInteger(0, wdid);
			query.setInteger(1, chairid);
			query.setInteger(2, hour);
			
			@SuppressWarnings("unchecked")
			List<StoreWorkhourModel> objects = query.list();
			if (objects != null && objects.size() > 0) {
				return objects.get(0);
			}
			
			return null;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public StoreinfoModel searchStoreinfoLogin(String username, String password)
			throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "FROM StoreinfoModel WHERE username=? and password=?";
			Query query = session.createQuery(hql);

			query.setString(0, username);
			query.setString(1, password);

			StoreinfoModel storeinfo = null;
			
			@SuppressWarnings("unchecked")
			List<Object> objList = query.list();
			
			if (null != objList && objList.size() > 0) {
				storeinfo = (StoreinfoModel) objList.get(0);
			}

			return storeinfo;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public String searchMasterList(String storeid) throws BaseException {
		try {
			int isid = Integer.valueOf(storeid);
			StoreinfoModel storeinfo = searchStoreinfo(isid);
			if (storeinfo != null)
			{
				return storeinfo.getMasterlist();
			}
			return null;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
		
	}

}
