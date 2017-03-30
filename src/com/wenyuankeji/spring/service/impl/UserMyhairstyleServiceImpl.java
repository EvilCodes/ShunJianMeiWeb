package com.wenyuankeji.spring.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IUserHairstyleDao;
import com.wenyuankeji.spring.dao.IUserMyhairstyleDao;
import com.wenyuankeji.spring.dao.IUserMyhairstylePhotoMappingDao;
import com.wenyuankeji.spring.dao.IUserMyhairstyleTimesDao;
import com.wenyuankeji.spring.dao.IUserProfessionLevelServiceDao;
import com.wenyuankeji.spring.dao.IUsersubinfoDao;
import com.wenyuankeji.spring.model.UserHairstyleModel;
import com.wenyuankeji.spring.model.UserMyhairstyleModel;
import com.wenyuankeji.spring.model.UserMyhairstylePhotoMappingModel;
import com.wenyuankeji.spring.model.UserMyhairstyleTimesModel;
import com.wenyuankeji.spring.model.UserProfessionLevelServiceModel;
import com.wenyuankeji.spring.model.UsersubinfoModel;
import com.wenyuankeji.spring.service.IUserMyhairstyleService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class UserMyhairstyleServiceImpl implements IUserMyhairstyleService {
	
	@Autowired
	private IUserMyhairstyleDao userMyhairstyleDao;
	@Autowired
	private IUserMyhairstylePhotoMappingDao userMyhairstylePhotoMappingDao;
	@Autowired
	private IUsersubinfoDao usersubinfoDao;
	@Autowired
	private IUserHairstyleDao userHairstyleDao;
	@Autowired
	private IUserProfessionLevelServiceDao userProfessionLevelServiceDao;
	@Autowired
	private IUserMyhairstyleTimesDao userMyhairstyleTimesDao;
	
	@Override
	public UserMyhairstyleModel searchUserMyhairstyle(int mystyleid) throws BaseException{
		
		return userMyhairstyleDao.searchUserMyhairstyle(mystyleid);
	}
	
	@Override
	public List<UserMyhairstyleModel> searchUserMyhairstyle(String userid,String styleid,String sort,int pageSize,int pageIndex)throws BaseException
	{
		return userMyhairstyleDao.searchUserMyhairstyle(userid, styleid, sort, pageSize, pageIndex);
	}
	
	
	//根据用户信息查询
	@Override
	public UserMyhairstyleModel searchUserMyhairstyleByUserId(int userid) throws BaseException{
		
		return userMyhairstyleDao.searchUserMyhairstyleByUserId(userid);
	}
	
	//根据用户查询
	@Override
	public List<UserMyhairstyleModel> searchUserMyhairstyleUserId(int userid)throws BaseException{
		return userMyhairstyleDao.searchUserMyhairstyleUserId(userid);
	}

	@Override
	public UserMyhairstylePhotoMappingModel searchUserMyhairstylePhotoMapping(
			int mystyleid, int type) throws BaseException{
		return userMyhairstylePhotoMappingDao.searchUserMyhairstylePhotoMapping(mystyleid, type);
	}
	
	@Override
	public boolean addUserHairstyle(String userID, String iD, String picID1,
			String picID2, String styleID, String remark,
			List<Map<String, Object>> itemList) throws BaseException {
		
		try {
			
			if ("0".equals(iD)) {
				//新增
				//我的发型表
				UserMyhairstyleModel myhairsty = new UserMyhairstyleModel();
				myhairsty.setUserid(Integer.parseInt(userID));
				myhairsty.setStyleid(Integer.parseInt(styleID));
				//查询发型项目名称
				UserHairstyleModel userHairstyle = userHairstyleDao.searchUserHairstyleById(Integer.parseInt(styleID));
				if (null != userHairstyle) {
					myhairsty.setName(userHairstyle.getName());
				}else {
					myhairsty.setName("");
				}
				
				//查询美发师级别
				UsersubinfoModel userInfo = usersubinfoDao.searchUsersubinfo((Integer.parseInt(userID)));
				//根据美发师等级查询洗剪吹的价格
				UserProfessionLevelServiceModel upls = userProfessionLevelServiceDao.searchUserProfessionLevelService(userInfo.getLevelid(), "xjc");
				
				myhairsty.setPrice(upls.getPrice());
				myhairsty.setTimes(upls.getTimes());
				
				myhairsty.setDeleted(0);
				myhairsty.setUsednum(0);
				Date createtime = new Date();
				myhairsty.setCreatetime(createtime);
				myhairsty.setIntro(remark);
				myhairsty.setState(1);
				
				//执行添加
				int mystyleid = userMyhairstyleDao.addUserMyhairstyle(myhairsty);
				
				//添加 我的发型时间表
				int count1 = 0;
				for (Map<String, Object> itemMap : itemList) {
					
					UserMyhairstyleTimesModel o = new UserMyhairstyleTimesModel();
					o.setMystyleid(mystyleid);
					o.setServicecode(itemMap.get("Code").toString());
					o.setTimes(Integer.parseInt(itemMap.get("Times").toString()));
					o.setCreatetime(createtime);
					
					if (userMyhairstyleTimesDao.addUserMyhairstyleTimes(o)) {
						count1++;
					}
				}
				
				//发型相册 user_myhairstyle_photo_mapping
				int conut2 = 0;
				
				UserMyhairstylePhotoMappingModel umhpm1 = new UserMyhairstylePhotoMappingModel();
				umhpm1.setMystyleid(mystyleid);
				umhpm1.setType(1);
				umhpm1.setPicid(Integer.parseInt(picID1));
				umhpm1.setCreatetime(createtime);
				
				if (userMyhairstylePhotoMappingDao.addUserMyhairstylePhotoMapping(umhpm1)) {
					conut2++;
				}
				
				//详情图
				UserMyhairstylePhotoMappingModel umhpm2 = new UserMyhairstylePhotoMappingModel();
				umhpm2.setMystyleid(mystyleid);
				umhpm2.setType(2);
				umhpm2.setPicid(Integer.parseInt(picID2));
				umhpm2.setCreatetime(createtime);
				
				if (userMyhairstylePhotoMappingDao.addUserMyhairstylePhotoMapping(umhpm2)) {
					conut2++;
				}
				
				
				if (mystyleid > 0 && count1 == itemList.size() && conut2 == 2) {
					return true;
				}else {
					return false;
				}
				
			}else {
				//修改
				
				//根据id查询现有的发型
				UserMyhairstyleModel myhairsty = userMyhairstyleDao.searchUserMyhairstyle(Integer.parseInt(iD));
				if (myhairsty != null) {
					
					myhairsty.setUserid(Integer.parseInt(userID));
					myhairsty.setStyleid(Integer.parseInt(styleID));
					//查询发型项目名称
					UserHairstyleModel userHairstyle = userHairstyleDao.searchUserHairstyleById(Integer.parseInt(styleID));
					if (null != userHairstyle) {
						myhairsty.setName(userHairstyle.getName());
					}else {
						myhairsty.setName("");
					}
					
					//查询美发师级别
					UsersubinfoModel userInfo = usersubinfoDao.searchUsersubinfo((Integer.parseInt(userID)));
					//根据美发师等级查询洗剪吹的价格
					UserProfessionLevelServiceModel upls = userProfessionLevelServiceDao.searchUserProfessionLevelService(userInfo.getLevelid(), "xjc");
					
					myhairsty.setPrice(upls.getPrice());
					myhairsty.setTimes(upls.getTimes());
					//备注
					myhairsty.setIntro(remark);
					//状态1审核中
					myhairsty.setState(1);
					
					//执行修改
					if (userMyhairstyleDao.updateUserMyhairstyle(myhairsty)) {
						int mystyleid = Integer.parseInt(iD);
						Date createtime = new Date();
						
						//修改 我的发型时间表
						userMyhairstyleTimesDao.deleteUserMyhairstyleTimes(mystyleid);
						int count1 = 0;
						for (Map<String, Object> itemMap : itemList) {
							
							UserMyhairstyleTimesModel o = new UserMyhairstyleTimesModel();
							o.setMystyleid(mystyleid);
							o.setServicecode(itemMap.get("Code").toString());
							o.setTimes(Integer.parseInt(itemMap.get("Times").toString()));
							o.setCreatetime(createtime);
							
							if (userMyhairstyleTimesDao.addUserMyhairstyleTimes(o)) {
								count1++;
							}
						}
						
						
						//发型相册 user_myhairstyle_photo_mapping
						userMyhairstylePhotoMappingDao.deleteUserMyhairstylePhotoMapping(mystyleid);
						
						int conut2 = 0;
						
						UserMyhairstylePhotoMappingModel umhpm1 = new UserMyhairstylePhotoMappingModel();
						umhpm1.setMystyleid(mystyleid);
						umhpm1.setType(1);
						umhpm1.setPicid(Integer.parseInt(picID1));
						umhpm1.setCreatetime(createtime);
						
						if (userMyhairstylePhotoMappingDao.addUserMyhairstylePhotoMapping(umhpm1)) {
							conut2++;
						}
						
						//详情图
						UserMyhairstylePhotoMappingModel umhpm2 = new UserMyhairstylePhotoMappingModel();
						umhpm2.setMystyleid(mystyleid);
						umhpm2.setType(2);
						umhpm2.setPicid(Integer.parseInt(picID2));
						umhpm2.setCreatetime(createtime);
						
						if (userMyhairstylePhotoMappingDao.addUserMyhairstylePhotoMapping(umhpm2)) {
							conut2++;
						}
						
						

						if (count1 == itemList.size() && conut2 == 2) {
							return true;
						}else {
							return false;
						}
					}
					
					
					
				}else {
					return false;
				}
			
			}
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
		return false;
	}

	@Override
	public boolean updateUserMyhairstyle(int mystyleid, int state, int oldState)
			throws BaseException {
		return userMyhairstyleDao.updateUserMyhairstyle(mystyleid, state, oldState);
	}

	@Override
	public List<UserMyhairstyleModel> searchUserMyhairstylesUserId(int userid)
			throws BaseException {
		return userMyhairstyleDao.searchUserMyhairstylesUserId(userid);
	}
}
