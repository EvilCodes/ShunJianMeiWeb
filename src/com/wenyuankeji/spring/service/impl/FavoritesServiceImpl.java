package com.wenyuankeji.spring.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IFavoritesDao;
import com.wenyuankeji.spring.model.FavoritesModel;
import com.wenyuankeji.spring.service.IFavoritesService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class FavoritesServiceImpl implements IFavoritesService {

	@Autowired
	private IFavoritesDao favoritesDao;

	@Override
	public FavoritesModel searchFavorites(int userid, int type, int item) throws BaseException {

		return favoritesDao.searchFavorites(userid, type, item);
	}

	@Override
	public boolean updateFavorites(String userid, String type, String item,
			String state) throws BaseException {
		
		if ("0".equals(state)) {
			//删除
			return favoritesDao.deleteFavorite(userid, type, item);
		}else {
			//添加
			FavoritesModel o = new FavoritesModel();
			o.setUserid(Integer.parseInt(userid));
			o.setType(Integer.parseInt(type));
			o.setItem(Integer.parseInt(item));
			o.setCreatetime(new Date());
			return favoritesDao.addFavorite(o);
		}
	}

}
