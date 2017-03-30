package com.wenyuankeji.spring.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IUtilDao;
import com.wenyuankeji.spring.model.BaseException;
import com.wenyuankeji.spring.model.BaseRequestlog;
import com.wenyuankeji.spring.service.IUtilService;

@Service
public class UtilServiceImpl implements IUtilService {

	@Autowired
	private IUtilDao utilDao;
	

	@Override
	public void addBaseRequestlog(String url, String para1, String para2,
			String result) {
		
		//设置访问信息
		BaseRequestlog o = new BaseRequestlog();
		o.setUrl(url);
		o.setPara1(para1);
		o.setPara2(para2);
		o.setResult(result);
		o.setCreatetime(new Date());
//		if (null == utilDao) {
//			utilDao = new UtilDaoImpl();
//		}
		utilDao.addBaseRequestlog(o);
	}
	
	@Override
	public int addBaseException(String source,String paras,String message){
		
		//设置异常信息
		BaseException o = new BaseException();
		o.setSource(source);
		o.setParas(paras);
		if (message == null) {
			message = "null Object";
		}
		o.setMessage(message);
		o.setCreatetime(new Date());
		
		//异常异常信息
		return utilDao.addBaseException(o);
	}
	
}
