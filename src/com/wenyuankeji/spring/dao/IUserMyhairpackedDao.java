package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.UserHairpackedModel;
import com.wenyuankeji.spring.model.UserMyhairPackedModel;
import com.wenyuankeji.spring.util.BaseException;

/**
 * @ClassName: IUserMyhairpackedDao
 * @Description: 理发师发型套餐表处理
 * @author lnn
 * @date 2016年1月11日
 */
public interface IUserMyhairpackedDao {
	/**
	 * 为美发师绑定套餐
	 * @param myhairPackedModel
	 * @return
	 * @throws BaseException   
	 * @throws
	 * @author lnn
	 * @date 2016年1月14日
	 */
	public boolean addUserMyhairpacked(UserMyhairPackedModel myhairPackedModel)throws BaseException ;
	/**
	 * 删除美发师绑定的套餐
	 * @param hairdresserPackedid
	 * @return
	 * @throws BaseException   
	 * @throws
	 * @author lnn
	 * @date 2016年1月14日
	 */
	public boolean deleteUserMyhairpacked(Integer hairdresserPackedid)throws BaseException ;
	/**
	 * 为美发师批量绑定套餐
	 * @param UserMyhairPackedModels
	 * @return true 成功
	 * @throws BaseException   
	 * @throws
	 * @author lnn
	 * @date 2016年1月13日
	 */
	public boolean addUserMyhairpackedBatch(List<UserMyhairPackedModel> UserMyhairPackedModels)throws BaseException ;
	//通过美发师id查询美发师的
	public List<UserMyhairPackedModel> findAllpackedByid(int id)throws BaseException ;
	
	/**
	 * 根据美发师id和套餐id加载实体
	 * @param userid 用户id
	 * @param packed_id 套餐id
	 * @return
	 * @throws BaseException   
	 * @throws
	 * @author lnn
	 * @date 2016年1月13日
	 */
	public UserMyhairPackedModel getUserMyHairPackedModel(int userid,int packed_id)throws BaseException;
	/**
	 * 根据套餐id 获取美发师套餐
	 * @param userHairpacked
	 * @return
	 */
	public List<UserMyhairPackedModel> searchUserMyHairpacked(Integer hairpackedid) throws BaseException ;
	/**
	 * 批量删除美发师套餐
	 * @param hairdresserPackedids
	 * @return
	 * @throws BaseException   
	 * @throws
	 * @author lnn
	 * @date 2016年1月14日
	 */
	boolean deleteUserMyhairpackedBatch(List<String> hairdresserPackedids)
			throws BaseException;
}
