package com.wenyuankeji.spring.service;

import com.wenyuankeji.spring.model.BaseConfigModel;
import com.wenyuankeji.spring.model.UserWalletModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IMyBalanceService {

	/**
	 * 根据用户查询钱包的卡片
	 * @param userid 用户ID
	 * @param ownertype 所有者类型: 1用户2美发师3店铺
	 * @return
	 * @throws BaseException
	 */
	public UserWalletModel searchUserWallet(int userid,int ownertype) throws BaseException;
	
	/**
	 * 查询美发师押金
	 * @param configcode cashfund:美发师押金
	 * @return
	 */
	public BaseConfigModel searchBaseConfig(String configcode)throws BaseException;
}
