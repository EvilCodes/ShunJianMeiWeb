package com.wenyuankeji.spring.dao;


import java.util.List;

import com.wenyuankeji.spring.model.UserinfoModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserinfoDao {

	/**
	 * 根据用户名和密码查询用户信息
	 * @param userName用户名
	 * @param passWord密码
	 * @return 用户信息对象
	 */
	public UserinfoModel searchUserinfo(String userName,String passWord,int usertype) throws BaseException;
	
	
	/**
	 * 根据userId修改用户基本信息
	 * @param userInfo
	 * @return
	 */
	public boolean updateUserInfoByUserId(UserinfoModel userInfo) throws BaseException;
	
	/**
	 * 注册新用户
	 * @param userinfo
	 * @return 新增之后的userId(-1代表用户已存在，0代表新增失败)
	 */
	public int addUserInfo(UserinfoModel userinfo)throws BaseException;
	
	
	/**
	 * 更新最后登录时间
	 * @param id
	 * @return
	 */
	public boolean updatelLastlogintimeById(int id) throws BaseException;
	public boolean updateUserinfo(UserinfoModel userinfo)throws BaseException;
	/**
	 * 根据用户ID查询用户实体
	 * @param userid
	 * @return
	 */
	public UserinfoModel searchUserinfoById(int userid)throws BaseException;
	/**
	 * 根据用户名查询用户信息
	 * @param userName
	 * @return
	 */
	public UserinfoModel searchUserinfoByUserName(String userName,int type)throws BaseException;
	public UserinfoModel addUserinfo(String userid,String nickName,String sex)throws BaseException;
	public boolean updateUserinfo(int userID, String oldPassWord, String newPassWord, int userState)throws BaseException;
	/**
	 * 重设密码
	 * @param userName手机号
	 * @param passWord新密码
	 * @return
	 */
	public boolean updateFindPassWord(String userName, String passWord, int userState)throws BaseException;
	
	public boolean updateFindUserinfo(UserinfoModel userinfo)throws BaseException;
	/**
	 * 注册
	 * @param userinfo
	 * @return
	 */
	public boolean addUserinfo(UserinfoModel userinfo)throws BaseException;
	
	/**
	 * 根据城市ID查询用户信息
	 * @param cityid城市ID
	 * @return 用户信息对象集合
	 */
	public List<Integer> searchUserinfoByCityId(int cityid,String version) throws BaseException;
	
	/************管理端************/
	/**
	 * 查询美发师
	 * @param cityid 城市ID
	 * @param userid 美发师ID
	 * @param tel    电话
	 * @param startTime  注册开始时间
	 * @param endTime    注册结束时间
	 * @param checkstate 审核状态
	 * @param page
	 * @param rows
	 * @return
	 * @throws BaseException
	 */
	public List<UserinfoModel> searchUserinfo(String cityid,String userid,String tel,String startTime,String endTime,String checkstate,int page, int rows) throws BaseException;
	/**
	 * 查询美发师
	 * @param cityid 城市ID
	 * @param userid 美发师ID
	 * @param tel    电话
	 * @param startTime  注册开始时间
	 * @param endTime    注册结束时间
	 * @param checkstate 审核状态
	 * @return
	 * @throws BaseException
	 */
	public int searchUserinfoCount(String cityid,String userid,String tel,String startTime,String endTime,String checkstate) throws BaseException;



}
