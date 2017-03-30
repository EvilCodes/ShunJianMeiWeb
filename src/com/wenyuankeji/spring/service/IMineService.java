package com.wenyuankeji.spring.service;

import com.wenyuankeji.spring.model.UserWalletModel;
import com.wenyuankeji.spring.model.UserinfoModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IMineService {

	/**
	 * 根据用户ID查询用户实体
	 * @param userid
	 * @return
	 */
	public UserinfoModel searchUserinfoById(int userid)throws BaseException;
	
	/**
	 * 根据用户查询钱包的卡片
	 * @param userid 用户ID
	 * @param ownertype 所有者类型: 1用户2美发师3店铺
	 * @return
	 * @throws BaseException
	 */
	public UserWalletModel searchUserWallet(int userid,int ownertype) throws BaseException;
	
	/**
	 * 查询我的收藏个数
	 * @param userid 用户ID
	 * @param type 0:所有 1:发型2:美发师3:商户
	 * @return
	 */
	public int searchFavoriteCount(int userid, int type)throws BaseException;
	
	
	/**
	 * 查询我的优惠券数
	 * @param userid 用户ID
	 * @return
	 * @throws BaseException
	 */
	public int searchUserCouponsCount(int userid) throws BaseException;
	
	
}
