package com.wenyuankeji.spring.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.wenyuankeji.spring.util.BaseException;

/**
 * @ClassName: IUserHairpackedService
 * @Description: 发型套餐业务处理
 * @author lnn
 * @date 2016年1月11日
 */
public interface IUserHairpackedService {
	/* 美发店相关方法 */
	/**
	 * * @param storeid
	 *            理发店id
	 * @param name
	 *            套餐名称
	 * @param intro
	 *            套餐介绍
	 * @param times
	 *            服务时长
	 * @param style
	 *            套餐类型，0普通套餐
	 * @param hairdresserids
	 *            美发师ids。用,（逗号）分割，如 1,2,3,4
	 * @param prices
	 * 			  Map<头发类型,价格> 不同头发不同价格
	 * @return
	 * @throws BaseException   
	 * @throws
	 * @author lnn
	 * @date 2016年1月11日
	 */
	public JSONObject addUserHairpacked(Integer storeid, String name,
			String intro, Integer times, Integer style, List<String> hairdresserids,Map<String,String> prices) throws BaseException;
	/**
	 * 根据美发店id查询发型套餐列表
	 * @param storedid 店铺列表
	 * @return JSONObject 
	 * @throws BaseException
	 * @author lnn
	 * @date 2016年1月11日
	 */
	public JSONObject searchUserHairpackedByStoreid(String storeid, int page, int rows) throws BaseException;
	
	
	/**
	 * 根据美发店id查询发型套餐详情列表
	 * @param storedid 店铺列表
	 * @return JSONObject
	 * @throws BaseException
	 * @author lnn
	 * @date 2016年1月11日
	 */
	public JSONObject searchUserHairpackedDetailsByStoreid(String storeid, int page, int rows) throws BaseException;
	/**
	 * 根据 套餐id,查询套餐详情
	 * @param hairpackedId
	 * @return
	 * @throws BaseException   
	 * @author lnn
	 * @date 2016年1月12日
	 */
	public JSONObject searchUserHairpackedByid(String hairpackedId ) throws BaseException;
	/* 美发店相关方法 end */

	/* 美发师相关方法 */
	/**
	 * 根据美发师(hairdresser)id查询发型套餐列表
	 * @param hairdresserid
	 * @return 
	 * @throws BaseException   
	 * @author lnn
	 * @date 2016年1月11日
	 */
	public JSONObject searchUserHairpackedByHairdresserid(String hairdresserid)throws BaseException;
	
	/* 美发师相关方法 end */
	
	/**
	 * 修改套餐状态接口
	 * @param packed_id
	 * @param packed_state
	 * @return
	 * @throws NumberFormatException
	 * @throws BaseException
	 * @author raymond
	 * @date 2016-01-12
	 */
	public boolean updateHairPackedForType(String packed_id,String packed_state) throws NumberFormatException, BaseException;
	
	/**
	 * 增加或删除美发师与套餐关联记录
	 * @param packed_id
	 * @param opration
	 * @param userid
	 * @return
	 * @throws NumberFormatException
	 * @throws BaseException
	 */
	public boolean updateHairPackedForUserId(String packed_id,String opration,String userid) throws NumberFormatException, BaseException;

	public int getPackedLength(String storeid);
	
	/**
	 * 获取集合
	 * @param storeid
	 * @param page
	 * @param rows
	 * @return
	 * @throws BaseException
	 */
	public List searchUserHairpackedByStoreidList(String storeid, int page,int rows) throws BaseException;
}
