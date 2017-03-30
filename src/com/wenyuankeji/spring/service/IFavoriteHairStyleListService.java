package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.FavoritesModel;
import com.wenyuankeji.spring.model.UserMyhairstyleModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IFavoriteHairStyleListService {

	/**
	 * 查询我的收藏
	 * @param userid 用户ID
	 * @param type 1发型
	 * @return
	 */
	public List<FavoritesModel> searchFavorite(int userid, int type,int pageSize,int pageIndex)throws BaseException;
	
	/**
	 * 查询我的发型
	 * @param mystyleid发型ID
	 * @return
	 */
	public UserMyhairstyleModel searchUserMyhairstyle(int mystyleid)throws BaseException;
	
}
