package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IFavoritesDao;
import com.wenyuankeji.spring.dao.IUsersubinfoDao;
import com.wenyuankeji.spring.model.FavoritesModel;
import com.wenyuankeji.spring.model.UsersubinfoModel;
import com.wenyuankeji.spring.service.IFavoriteHairdresserListService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class FavoriteHairdresserListServiceImpl implements IFavoriteHairdresserListService{

	@Autowired
	private IFavoritesDao favoritesDao;
	@Autowired
	private IUsersubinfoDao usersubinfoDao;
	
	@Override
	public List<FavoritesModel> searchFavorite(int userid, int type,int pageSize,int pageIndex)
			throws BaseException {
		return favoritesDao.searchFavorite(userid, type, pageSize, pageIndex);
	}

	@Override
	public UsersubinfoModel searchUsersubinfo(int userid) throws BaseException {
		return usersubinfoDao.searchUsersubinfo(userid);
	}

}
