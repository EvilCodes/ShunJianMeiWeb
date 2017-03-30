package com.wenyuankeji.spring.service;

import com.wenyuankeji.spring.model.UserinfoModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserinfoService {
	
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
	
	
	
	/***********UP  mupengyuan*****************/
	
	
	
	
	/**
	 * 根据用户名和密码查询用户信息
	 * @param userName用户名
	 * @param passWord密码
	 * @return 用户信息对象
	 */
	public UserinfoModel searchUserinfo(String userName,String passWord,int userType) throws BaseException;
	/**
	 * 更新用户最后登录时间
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public boolean updatelLastlogintimeById(int id) throws BaseException;
	public boolean updateUserinfo(UserinfoModel userinfo)throws BaseException;
	public UserinfoModel searchUserinfoById(int userid)throws BaseException;
	//public boolean UserRegister(String UserName,String PassWord);
	public UserinfoModel addUserinfo(String userid,String nickName,String sex)throws BaseException;
	public boolean updateUserinfo(int userID, String oldPassWord, String newPassWord, int userState)throws BaseException;
	/**
	 * 重设密码
	 * @param userName手机号
	 * @param passWord新密码
	 * @return
	 */
	public boolean updateFindPassWord(String userName, String passWord, int userState)throws BaseException;
	
	/**
	 * 根据用户名查询用户信息
	 * @param userName
	 * @return
	 */
	public UserinfoModel searchUserinfoByUserName(String userName,int type)throws BaseException;
	
	public boolean updateFindUserinfo(UserinfoModel userinfo)throws BaseException;
	
	/**
	 * 注册
	 * @param userinfo
	 * @return
	 */
	boolean addUserinfo(UserinfoModel userinfo)throws BaseException;
	/**
	 * 根据城市ID查询用户信息
	 * @param cityid城市ID
	 * @return 用户信息对象集合
	 */
	public String searchUserinfoByCityId(int cityid,String version) throws BaseException;

}
