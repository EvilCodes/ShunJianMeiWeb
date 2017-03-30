package com.wenyuankeji.spring.service;

import com.wenyuankeji.spring.model.BaseVerificationcodeModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IBaseVerificationcodeService {

	/**
	 * 根据手机号码和验证类型发送验证信息
	 * @param mobile手机号码
	 * @param type验证码类别
	 * @return
	 */
	public boolean addBaseVerificationcode(String mobile,int type)throws BaseException;
	/**
	 * 根据手机号查询最近生成的验证码信息
	 * @param mobile
	 * @return
	 */
	public BaseVerificationcodeModel searchBaseVerificationcode(String mobile)throws BaseException;
}
