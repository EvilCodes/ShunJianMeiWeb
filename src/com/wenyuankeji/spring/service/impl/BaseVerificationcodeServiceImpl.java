package com.wenyuankeji.spring.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IBaseVerificationcodeDao;
import com.wenyuankeji.spring.model.BaseVerificationcodeModel;
import com.wenyuankeji.spring.service.IBaseVerificationcodeService;
import com.wenyuankeji.spring.util.BaseException;
import com.wenyuankeji.spring.util.ShunJianMeiUtil;
import com.wenyuankeji.spring.util.SmsHelper;

@Service
public class BaseVerificationcodeServiceImpl implements
		IBaseVerificationcodeService {
	@Autowired
	private IBaseVerificationcodeDao baseVerificationcodeDao;
	
	@Value("#{configProperties['simulationt']}")
	private String simulationt;
	
	@Override
	public boolean addBaseVerificationcode(String mobile,
			int type) throws BaseException{
		Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(new Date());
        rightNow.set(Calendar.MINUTE,rightNow.get(Calendar.MINUTE)+10);
        Date expirationtime=rightNow.getTime();
        
        BaseVerificationcodeModel baseVerificationcodeModel = new BaseVerificationcodeModel();
        baseVerificationcodeModel.setMobile(mobile);
        //生成验证码的随机数
        String vCode = ShunJianMeiUtil.getRandomVCode();
        baseVerificationcodeModel.setCode(vCode);
        baseVerificationcodeModel.setType(type);
        baseVerificationcodeModel.setCreatetime(new Date());
        baseVerificationcodeModel.setExpirationtime(expirationtime);
		
        //给手机发送短信
        SmsHelper.sendMessage(mobile, vCode);
        
        return baseVerificationcodeDao.addBaseVerificationcode(baseVerificationcodeModel);
	}

	@Override
	public BaseVerificationcodeModel searchBaseVerificationcode(String mobile) throws BaseException{
		return baseVerificationcodeDao.searchBaseVerificationcode(mobile);
	}

}
