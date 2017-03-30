/**
 * 
 */
package com.wenyuankeji.spring.service;

import com.wenyuankeji.spring.model.StoreWorkdateModel;

/**
 * @author Administrator
 *
 */
public interface IStoreWorkDateService {

	public StoreWorkdateModel searchStoreWorkdate(int storeId,int year,int month,int day );
}
