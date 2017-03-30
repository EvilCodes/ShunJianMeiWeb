package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IFavoritesDao;
import com.wenyuankeji.spring.dao.IStoreinfoDao;
import com.wenyuankeji.spring.model.FavoritesModel;
import com.wenyuankeji.spring.model.StoreinfoModel;
import com.wenyuankeji.spring.service.IFavoriteStoreListService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class FavoriteStoreListServiceImpl implements IFavoriteStoreListService{

	@Autowired
	private IFavoritesDao favoritesDao;
	@Autowired
	private IStoreinfoDao storeinfoDao;
	
	@Override
	public List<FavoritesModel> searchFavorite(int userid, int type,int pageSize,int pageIndex)
			throws BaseException {
		return favoritesDao.searchFavorite(userid, type, pageSize, pageIndex);
	}

	@Override
	public StoreinfoModel searchStoreinfo(int storeid) throws BaseException {
		return storeinfoDao.searchStoreinfo(storeid);
	}

	


	
}
