package com.wenyuankeji.spring.service;

import java.util.List;
import java.util.Map;

import com.wenyuankeji.spring.model.UserMyhairstyleModel;
import com.wenyuankeji.spring.model.UserMyhairstylePhotoMappingModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserMyhairstyleService {
	/**
	 * 查询我的发型
	 * @param mystyleid发型ID
	 * @return
	 */
	public UserMyhairstyleModel searchUserMyhairstyle(int mystyleid)throws BaseException;
	
	/**
	 * 发型列表
	 * @param userid 用户ID
	 * @param styleid 发型分类ID
	 * @param sort 价格排序 0低到高,1高到低
	 * @param pageSize 返回数据行数 
	 * @param pageIndex 当前页数 从1开始
	 * @return
	 */
	public List<UserMyhairstyleModel> searchUserMyhairstyle(String userid,String styleid,String sort,int pageSize,int pageIndex)throws BaseException;

	/**
	 * 查询发型相册
	 * @param mystyleid 我的发型ID
	 * @param type 类型1列表图,2相册
	 * @return
	 */
	public UserMyhairstylePhotoMappingModel searchUserMyhairstylePhotoMapping(int mystyleid,int type)throws BaseException;
	

	public UserMyhairstyleModel searchUserMyhairstyleByUserId(int userid)throws BaseException;
	
	/**
	 * 根据用户查找我的发型
	 * @param userid
	 * @return
	 */
	public List<UserMyhairstyleModel> searchUserMyhairstyleUserId(int userid)throws BaseException;
	
	/**
	 * 根据用户查找我的发型  美发师端
	 * @param userid
	 * @return
	 */
	public List<UserMyhairstyleModel> searchUserMyhairstylesUserId(int userid)throws BaseException;
	
	/**
	 * 保存我的发型
	 * @param userID美发师id
	 * @param iD我的发型项目ID 新增为0
	 * @param picID1列表图ID
	 * @param picID2详情图ID
	 * @param styleID发型项目ID
	 * @param remark备注说明
	 * @param itemListCode服务代码Times   时长
	 * @return
	 * @throws BaseException
	 */
	public boolean addUserHairstyle(String userID,String iD,String picID1,String picID2,String styleID,String remark,List<Map<String, Object>> itemList) throws BaseException;
	
	/**
	 * 更新我的发型状态
	 * @param mystyleid 发型ID
	 * @return
	 * @throws BaseException
	 */
	public boolean updateUserMyhairstyle(int mystyleid,int state,int oldState) throws BaseException;
	
}
