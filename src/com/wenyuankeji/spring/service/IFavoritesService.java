package com.wenyuankeji.spring.service;

import com.wenyuankeji.spring.model.FavoritesModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IFavoritesService {
	// 查询收藏
	public FavoritesModel searchFavorites(int userid, int type, int item) throws BaseException;

	/**
	 * 更新我的收藏
	 * @param userid
	 * @param type
	 * @param item
	 * @param state 0取消收藏1添加收藏
	 * @return
	 * @throws BaseException
	 */
	public boolean updateFavorites(String userid, String type, String item,String state) throws BaseException;
}
