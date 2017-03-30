package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.FavoritesModel;
import com.wenyuankeji.spring.model.UsersubinfoModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IFavoriteHairdresserListService {

	/**
	 * 查询我的收藏
	 * @param userid 用户ID
	 * @param type 1发型
	 * @return
	 */
	public List<FavoritesModel> searchFavorite(int userid, int type,int pageSize,int pageIndex)throws BaseException;
	
	/**
	 * 根据美发师id查询美发师信息
	 * @param userid
	 * @return
	 * @throws BaseException
	 */
	public UsersubinfoModel searchUsersubinfo(int userid)throws BaseException;
}
