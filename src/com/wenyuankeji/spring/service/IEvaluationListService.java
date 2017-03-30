package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.UserEvaluateModel;
import com.wenyuankeji.spring.model.UserEvaluatePhotoMappingModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IEvaluationListService {
	/**
	 * 根据美发师ID、评论、发型项目ID，查询用户评价
	 * @param item 美发师ID
	 * @param evaid 评论ID
	 * @param styleID 发型项目ID
	 * @return
	 */
	public List<UserEvaluateModel> searchUserEvaluateByItem(int item,int evaid,int styleID)throws BaseException;
	/**
	 * 查询评价配图
	 * @return
	 */
	public List<UserEvaluatePhotoMappingModel> searchUserEvaluatePhotoMapping(int evaid)throws BaseException;
	/**
	 * 根据店铺ID和评论，查询用户评价
	 * @param item 美发师ID
	 * @param evaid 评论ID
	 * @return
	 */
	public List<UserEvaluateModel> searchUserEvaluateByStore(int item,int evaid)throws BaseException;
	
}
