package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.FavoritesModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IFavoritesDao {
	// 查询收藏
	public FavoritesModel searchFavorites(int userid, int type, int item)
			throws BaseException;;

	/**
	 * 添加我是的收藏
	 * @param o
	 * @return
	 * @throws BaseException
	 */
	public boolean addFavorite(FavoritesModel o)
			throws BaseException;
	
	/**
	 * 修改添加我是的收藏
	 * @param userid
	 * @param type
	 * @param item
	 * @return
	 * @throws BaseException
	 */
	public boolean updateFavorite(String userid, String type, String item)
				throws BaseException;
	/**
	 * 删除我的收藏
	 * @param userid
	 * @param type
	 * @param item
	 * @return
	 * @throws BaseException
	 */
	public boolean deleteFavorite(String userid, String type, String item)
			throws BaseException;
	
	/**
	 * 查询我的收藏
	 * @param userid 用户ID
	 * @param type 收藏类型
	 * @return
	 */
	public List<FavoritesModel> searchFavorite(int userid, int type,int pageSize,int pageIndex)throws BaseException;
	
	
	/**
	 * 查询我的收藏个数
	 * @param userid 用户ID
	 * @param type 0:所有 1:发型2:美发师3:商户
	 * @return
	 */
	public int searchFavoriteCount(int userid, int type)throws BaseException;

	
}
