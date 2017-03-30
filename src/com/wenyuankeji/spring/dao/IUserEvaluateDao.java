package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.UserEvaluateModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserEvaluateDao {

	public UserEvaluateModel searchUserEvaluate(int userid,String content)throws BaseException;
	

	/**
	 * 根据美发师ID和分类查询用户评价
	 * @param item
	 * @param type
	 * @return
	 * @throws BaseException
	 */
	public List<UserEvaluateModel> searchUserEvaluate(int item, int type)throws BaseException;
	
	/**
	 * 根据用户查找评论
	 * @param userid
	 * @return
	 */
	public List<UserEvaluateModel> searchUserEvaluate(int userid)throws BaseException;
	
	/**
	 * 添加评价
	 * @param o
	 * @return
	 * @throws BaseException
	 */
	public int addUserEvaluate(UserEvaluateModel o)throws BaseException;
	
	/**
	 * 根据订单查询
	 * @param userid评论者id
	 * @param item评论对象id
	 * @param orderid订单id
	 * @param type 1美发师2店铺
	 * @return
	 */
	public UserEvaluateModel searchUserEvaluate(int userid,int item,int orderid,int type)throws BaseException;
	
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
	 * 根据美发师ID、评论、发型项目ID，查询用户评价
	 * @param item 美发师ID
	 * @param evaid 评论ID
	 * @param styleID 发型项目ID
	 * @return
	 */
	public List<UserEvaluateModel> searchUserEvaluateByItem(int item,int evaid,int styleID)throws BaseException;
	
	/**
	 * 根据店铺ID和评论，查询用户评价
	 * @param item 美发师ID
	 * @param evaid 评论ID
	 * @return
	 */
	public List<UserEvaluateModel> searchUserEvaluateByStore(int item,int evaid)throws BaseException;
	
	public List<Object []> searchUserEvaluateCount(String item,String type)throws BaseException;
	
	/**
	 * 更新用户评价分数
	 * @param o
	 * @return
	 * @throws BaseException
	 */
	public boolean updateUserEvaluate(int evaid, int score)throws BaseException;
}
