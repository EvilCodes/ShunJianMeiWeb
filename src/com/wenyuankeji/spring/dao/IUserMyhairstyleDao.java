package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.UserMyhairstyleModel;
import com.wenyuankeji.spring.model.UserMyhairstylePhotoMappingModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserMyhairstyleDao {

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
	 * 查询发型相册的卡片
	 * @param mystyleid 我的发型ID
	 * @return
	 */
	public UserMyhairstylePhotoMappingModel searchUserMyhairstylePhotoMapping(int mystyleid)throws BaseException;
	
	/**
	 * 根据用户查询我的发型
	 * @param userid
	 * @return
	 */
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
	 * 添加我的发型
	 * @param o
	 * @return
	 * @throws BaseException
	 */
	public int addUserMyhairstyle(UserMyhairstyleModel o) throws BaseException;
	
	/**
	 * 修改我的发型
	 * @param o
	 * @return
	 * @throws BaseException
	 */
	public boolean updateUserMyhairstyle(UserMyhairstyleModel o) throws BaseException;
	
	/**
	 * 更新我的发型状态
	 * @param mystyleid 发型ID
	 * @return
	 * @throws BaseException
	 */
	public boolean updateUserMyhairstyle(int mystyleid,int state,int oldState) throws BaseException;
	
	
}