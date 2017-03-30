package com.wenyuankeji.spring.service;

import java.util.List;
import java.util.Map;

import com.wenyuankeji.spring.model.UserEvaluateModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserEvaluateService {

	public UserEvaluateModel searchUserEvaluate(int userid,String content)throws BaseException;
	

	public List<UserEvaluateModel> searchUserEvaluate(int item, int type)throws BaseException;
	
	/**
	 * 查看美发师或者美发店的评论
	 * @param type
	 * @param item
	 * @return
	 * @throws BaseException
	 */
	public List<UserEvaluateModel> searchCommentNum(int type,int item)
			throws BaseException;
	
	/**
	 * 根据用户查找评论
	 * @param userid
	 * @return
	 */
	public List<UserEvaluateModel> searchUserEvaluate(int userid)throws BaseException;
	
	/**
	 * 保存评价
	 * @param userID用户ID
	 * @param orderID订单ID
	 * @param type是	类型 1发型师2店铺
	 * @param content评价内容
	 * @param item服务评分
	 * @param image图片ID
	 * @return
	 * @throws BaseException
	 */
	public int addUserEvaluate(String userID,String orderID,String type,String content,List<Map<String, Object>> itemList,String image)throws BaseException;
}
