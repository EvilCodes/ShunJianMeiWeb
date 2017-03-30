package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IFavoritesDao;
import com.wenyuankeji.spring.dao.IUserMyhairstyleDao;
import com.wenyuankeji.spring.model.FavoritesModel;
import com.wenyuankeji.spring.model.UserMyhairstyleModel;
import com.wenyuankeji.spring.service.IFavoriteHairStyleListService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class FavoriteHairStyleListServiceImpl implements IFavoriteHairStyleListService{

	@Autowired
	private IFavoritesDao favoritesDao;
	@Autowired
	private IUserMyhairstyleDao userMyhairstyleDao;
	
	@Override
	public List<FavoritesModel> searchFavorite(int userid, int type,int pageSize,int pageIndex)
			throws BaseException {
		return favoritesDao.searchFavorite(userid, type, pageSize, pageIndex);
	}

	@Override
	public UserMyhairstyleModel searchUserMyhairstyle(int mystyleid) throws BaseException{
		return userMyhairstyleDao.searchUserMyhairstyle(mystyleid);
	}
	
}
