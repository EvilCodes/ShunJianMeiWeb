package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.FavoritesModel;
import com.wenyuankeji.spring.model.StoreinfoModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IFavoriteStoreListService {

	/**
	 * 查询我的收藏
	 * @param userid 用户ID
	 * @param type 1发型
	 * @return
	 */
	public List<FavoritesModel> searchFavorite(int userid, int type,int pageSize,int pageIndex)throws BaseException;
	
	/**
	 * 根据店铺id查询店铺信息
	 * @param storeid
	 * @return
	 * @throws BaseException
	 */
	public StoreinfoModel searchStoreinfo(int storeid) throws BaseException;
	
}
