package com.wenyuankeji.spring.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IOrderinfoDao;
import com.wenyuankeji.spring.dao.IStoreinfoDao;
import com.wenyuankeji.spring.dao.IUserEvaluateDao;
import com.wenyuankeji.spring.dao.IUserEvaluatePhotoMappingDao;
import com.wenyuankeji.spring.dao.IUsersubinfoDao;
import com.wenyuankeji.spring.model.OrderinfoModel;
import com.wenyuankeji.spring.model.UserEvaluateModel;
import com.wenyuankeji.spring.model.UserEvaluatePhotoMappingModel;
import com.wenyuankeji.spring.service.IUserEvaluateService;
import com.wenyuankeji.spring.util.BaseException;
import com.wenyuankeji.spring.util.Constant;
import com.wenyuankeji.spring.util.ShunJianMeiUtil;

@Service
public class UserEvaluateServiceImpl implements IUserEvaluateService{

	@Autowired
	private IUserEvaluateDao userEvaluateDao;
	@Autowired
	private IOrderinfoDao orderinfoDao;
	@Autowired
	private IUserEvaluatePhotoMappingDao userEvaluatePhotoMappingDao;
	@Autowired
	private IUsersubinfoDao usersubinfoDao;
	@Autowired
	private IStoreinfoDao storeinfoDao;
	
	@Override
	public UserEvaluateModel searchUserEvaluate(int userid, String content) throws BaseException{
		
		return userEvaluateDao.searchUserEvaluate(userid, content);
	}

	@Override
	public List<UserEvaluateModel> searchUserEvaluate(int item, int type) throws BaseException{
		
		return userEvaluateDao.searchUserEvaluate(item, type);
	}

	@Override
	public List<UserEvaluateModel> searchUserEvaluate(int userid) throws BaseException{
		
		return userEvaluateDao.searchUserEvaluate(userid);
	}

	@Override
	public int addUserEvaluate(String userID, String orderID, String type,
			String content, List<Map<String, Object>> itemList, String image) throws BaseException {
		
		try {
			//保存用户评价
			UserEvaluateModel userEvaluateModel = new UserEvaluateModel();
			//查询订单信息
			OrderinfoModel orderinfo = orderinfoDao.searchOrderinfo(Integer.parseInt(orderID));
			if (null == orderinfo) {
				return 0;
			}
			Date createtime = new Date();
			//判断类型： 1发型师2店铺
			if ("1".equals(type)) {
				userEvaluateModel.setItem(String.valueOf(orderinfo.getUserid()));
			}else {
				userEvaluateModel.setItem(String.valueOf(orderinfo.getStoreid()));
			}
			userEvaluateModel.setUserid(Integer.parseInt(userID));
			userEvaluateModel.setType(Integer.parseInt(type));
			
	
			float js = 0;
			float zy = 0;
			float sh = 0;
			float hj = 0;
			float jt = 0;
			
			for (Map<String, Object> itemMap : itemList) {
				String code = itemMap.get("Code").toString();
				float score = Float.parseFloat(itemMap.get("Score").toString());
				if ("js".equals(code)) {
					js = score;
				} else if("zy".equals(code)) {
					zy = score;
				}else if("sh".equals(code)) {
					sh = score;
				}else if("hj".equals(code)) {
					hj = score;
				}else if("jt".equals(code)) {
					jt = score;
				}
			}

			userEvaluateModel.setScore(0);
			
			userEvaluateModel.setContent(content);
			userEvaluateModel.setOrderid(Integer.parseInt(orderID));
			userEvaluateModel.setDeleted(false);
			userEvaluateModel.setCreatetime(createtime);
			//执行添加
			int evaid = userEvaluateDao.addUserEvaluate(userEvaluateModel);
			
			if (evaid <= 0) {
				return 0;
			}
			
			//添加评价配图
			if (null != image && image.length() > 0) {
				
				//image = image.replaceAll("|", ",");
				String [] images = image.split("[|]");
				for (String picid : images) {
					
					UserEvaluatePhotoMappingModel o = new UserEvaluatePhotoMappingModel();
					o.setEvaid(evaid);
					o.setPicid(Integer.parseInt(picid));
					o.setCreatetime(createtime);
					
					userEvaluatePhotoMappingDao.addUserEvaluatePhotoMapping(o);
				}
			}
			
			//保存评价分数
			if (null != itemList && itemList.size() > 0) {
				for (Map<String, Object> itemMap : itemList) {
					String evaluate = itemMap.get("Code").toString();
					String score = itemMap.get("Score").toString();
					userEvaluatePhotoMappingDao.addUserEvaluateScore(evaid,evaluate,score);
				}
			}
			
			
			int jsALL = 1;
			int zyALL = 1;
			int shALL = 1;
			int hjALL = 1;
			int jtALL = 1;
			
			int jsCont = 1;
			int zyCont = 1;
			int shCont = 1;
			int hjCont = 1;
			int jtCont = 1;
			
			List<Object []> evaluateCont = userEvaluateDao.searchUserEvaluateCount(userEvaluateModel.getItem(), type);
			
			if (null != evaluateCont) {
				
				for (Object[] o : evaluateCont) {
					String evaluate = o[0].toString();
					Integer cont = Integer.parseInt(o[1].toString());
					Integer all = Integer.parseInt(o[2].toString());
					if ("js".equals(evaluate)) {
						jsCont = cont;
						jsALL = all;
					} else if("zy".equals(evaluate)) {
						zyCont = cont;
						zyALL = all;
					}else if("sh".equals(evaluate)) {
						shCont = cont;
						shALL = all;
					}else if("hj".equals(evaluate)) {
						hjCont = cont;
						hjALL = all;
					}else if("jt".equals(evaluate)) {
						jtCont = cont;
						jtALL = all;
					}
				}
			}
			
			int zf = 0;
			 
			if ("1".equals(type)) {
				zf = (int) Math.round((jsALL/jsCont*0.5) + (zyALL/zyCont*0.3) + (shALL/shCont*0.2));
			}else {
				zf = (int) Math.round((hjALL/hjCont* 0.5) + (jtALL/jtCont * 0.5));
			}
			
			
			//更新usersubinfo的评分和storeinfo的评分
			//判断类型： 1发型师2店铺
			if ("1".equals(type)) {
				//更新usersubinfo的评分
				usersubinfoDao.updateScore(orderinfo.getUserid(), zf);
			}else {
				//更新storeinfo的评分
				storeinfoDao.updateScore(orderinfo.getStoreid(), zf);
			}
			
			
			//更新用户评价
			int usersubScore = 0;
			if ("1".equals(type)) {
				usersubScore = (int) Math.round((js/1*0.5) + (zy/1*0.3) + (sh/1*0.2));
			}else {
				usersubScore = (int) Math.round((hj/1* 0.5) + (jt/1 * 0.5));
			}
			//执行更新评价
			userEvaluateDao.updateUserEvaluate(evaid, usersubScore);
			if(orderinfo.getPaystate()==Constant.PAYSTE_ORDER_FINISH_08){
				
				
			}else {
				orderinfoDao.updateOrderinfoModel(orderinfo.getOrderid(), Constant.PAYSTE_EVALUATE_FINISH_07);
			}
			
						
			return evaid;
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
	}

	@Override
	public List<UserEvaluateModel> searchCommentNum(int type, int item)
			throws BaseException {
		return userEvaluateDao.searchCommentNum(type, item);
	}

}
