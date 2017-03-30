package com.wenyuankeji.spring.dao;

import com.wenyuankeji.spring.model.BaseVerificationcodeModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IBaseVerificationcodeDao {

	/**
	 * 根据手机号码和验证码类类别发送验证信息
	 * @param mobile手机号码
	 * @param type验证码类别
	 * @return
	 */
	public boolean addBaseVerificationcode(BaseVerificationcodeModel baseVerificationcodeModel)throws BaseException;
	
	/**
	 * 根据手机号查询最近生成的验证码信息
	 * @param mobile
	 * @return
	 */
	public BaseVerificationcodeModel searchBaseVerificationcode(String mobile)throws BaseException;
}
