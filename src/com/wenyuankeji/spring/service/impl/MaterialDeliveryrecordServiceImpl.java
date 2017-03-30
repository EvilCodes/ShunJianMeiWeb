package com.wenyuankeji.spring.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IMaterialDeliveryrecordDao;
import com.wenyuankeji.spring.dao.IMaterialDeliveryrecordDetailDao;
import com.wenyuankeji.spring.model.MaterialDeliveryrecordDetailModel;
import com.wenyuankeji.spring.model.MaterialDeliveryrecordModel;
import com.wenyuankeji.spring.service.IMaterialDeliveryrecordService;
import com.wenyuankeji.spring.util.BaseException;
import com.wenyuankeji.spring.util.ShunJianMeiUtil;

@Service
public class MaterialDeliveryrecordServiceImpl implements
		IMaterialDeliveryrecordService {

	@Autowired
	private IMaterialDeliveryrecordDao materialDeliveryrecordDao;
	@Autowired
	private IMaterialDeliveryrecordDetailDao materialDeliveryrecordDetailDao;

	@Override
	public MaterialDeliveryrecordModel searchMaterialDeliveryrecordById(
			int recordid) throws BaseException {

		return materialDeliveryrecordDao
				.searchMaterialDeliveryrecordById(recordid);
	}

	@Override
	public List<MaterialDeliveryrecordModel> searchmaterialDeliveryrecord(
			int userid) throws BaseException {

		return materialDeliveryrecordDao.searchmaterialDeliveryrecord(userid);
	}

	@Override
	public List<MaterialDeliveryrecordModel> searchmaterialDeliveryrecord(
			int userid, int state) throws BaseException {
		return materialDeliveryrecordDao.searchmaterialDeliveryrecord(userid,
				state);
	}

	@Override
	public List<MaterialDeliveryrecordModel> searchmaterialDeliveryrecord(
			int recordid, int userid, int state) throws BaseException {
		return materialDeliveryrecordDao.searchmaterialDeliveryrecord(recordid, userid, state);
	}

	@Override
	public boolean updateMaterialDeliveryrecordModel(int recordid)
			throws BaseException {

		return materialDeliveryrecordDao
				.updateMaterialDeliveryrecordModel(recordid);
	}

	@Override
	public boolean addMaterialDeliveryrecord(String userID,
			String deliverytime, String addressID,
			List<Map<String, Object>> itemList) throws BaseException {

		Date date = new Date();
		// 设置提取信息
		MaterialDeliveryrecordModel o = new MaterialDeliveryrecordModel();
		o.setUserid(Integer.parseInt(userID));
		o.setStationid(Integer.parseInt(addressID));

		o.setAppointmenttime(ShunJianMeiUtil.stringToDate(deliverytime));
		o.setState(1);

		//o.setDeliverytime(date);
		o.setCreatetime(date);

		// 保存
		int recordid = materialDeliveryrecordDao.addMaterialDeliveryrecord(o);

		if (recordid > 0) {

			int count = 0;

			// 添加提取记录明细
			for (Map<String, Object> itemMap : itemList) {

				count++;

				MaterialDeliveryrecordDetailModel mddm = new MaterialDeliveryrecordDetailModel();
				mddm.setRecordid(recordid);
				mddm.setMaterialid(Integer.parseInt(itemMap.get("MaterialID")
						.toString()));
				mddm.setQuantity(Integer
						.parseInt(itemMap.get("Num").toString()));
				mddm.setCreatetime(date);
				// 执行添加
				materialDeliveryrecordDetailDao
						.addMaterialDeliveryrecordDetail(mddm);
			}

			if (count == itemList.size()) {
				return true;
			}
		}

		return false;
	}

}
