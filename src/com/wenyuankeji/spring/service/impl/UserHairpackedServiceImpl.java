package com.wenyuankeji.spring.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.ListView;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.wenyuankeji.spring.dao.IUserHairpackedDao;
import com.wenyuankeji.spring.dao.IUserHairpackedItemDao;
import com.wenyuankeji.spring.dao.IUserHairstyleDao;
import com.wenyuankeji.spring.dao.IUserMyhairpackedDao;
import com.wenyuankeji.spring.dao.IUserinfoDao;
import com.wenyuankeji.spring.model.UserHairpackedItemModel;
import com.wenyuankeji.spring.model.UserHairpackedModel;
import com.wenyuankeji.spring.model.UserHairstyleModel;
import com.wenyuankeji.spring.model.UserMyhairPackedModel;
import com.wenyuankeji.spring.model.UserinfoModel;
import com.wenyuankeji.spring.service.IUserHairpackedService;
import com.wenyuankeji.spring.util.BaseException;
import com.wenyuankeji.spring.util.Constant;

/**
 * @ClassName: UserHairpackedServiceImpl
 * @Description: 发型套餐业务处理
 * @author lnn
 * @date 2016年1月11日
 */
@Service
public class UserHairpackedServiceImpl implements IUserHairpackedService {
	@Autowired
	private IUserHairpackedItemDao userHairpackedItemDao;
	@Autowired
	private IUserHairpackedDao userHairpackedDao;
	@Autowired
	private IUserMyhairpackedDao userMyhairpackedDao;
	@Autowired
	private IUserHairstyleDao userHairstyleDao;
	@Autowired
	private IUserinfoDao userinfoDao;

	/**
	 * 美发店添加发型套餐
	 */
	@Override
	public JSONObject addUserHairpacked(Integer storeid, String name,
			String intro, Integer times, Integer style,
			List<String> hairdresserids, Map<String, String> prices)
			throws BaseException {
		JSONObject json = new JSONObject();
		UserHairpackedModel userHairpackedModel = new UserHairpackedModel();
		List<UserHairpackedItemModel> hairpackedItemList = new ArrayList<UserHairpackedItemModel>();
		List<UserMyhairPackedModel> myhairPackedModelList = new ArrayList<UserMyhairPackedModel>();
		// 添加套餐
		userHairpackedModel.setName(name);
		userHairpackedModel.setStoreid(storeid);
		userHairpackedModel.setIntro(intro);
		userHairpackedModel.setTimes(times);
		userHairpackedModel.setStyle(style);
		userHairpackedModel.setState(1);
		userHairpackedModel.setCreatetime(new Date());
		userHairpackedDao.addUserHairpacked(userHairpackedModel);
		// 添加套餐项
		for (String key : prices.keySet()) {
			UserHairpackedItemModel userHairpackedItemModel = new UserHairpackedItemModel();
			userHairpackedItemModel.setCreatetime(new Date());
			userHairpackedItemModel.setHairpackedid(userHairpackedModel.getId());
			userHairpackedItemModel.setPrice(Double.parseDouble(prices.get(key)));
			userHairpackedItemModel.setStyle(Integer.parseInt(key));
			userHairpackedItemModel.setState(1);
			hairpackedItemList.add(userHairpackedItemModel);
		}
		userHairpackedItemDao.addHairpackedItemBatch(hairpackedItemList);
		myhairPackedModelList=packMyhairPackedModel(userHairpackedModel.getId(), hairdresserids);
		userMyhairpackedDao.addUserMyhairpackedBatch(myhairPackedModelList);
		json.put("code", "1000");
		json.put("msg", "0|操作成功");
		json.put("data", "");
		return json;
	}

	/**
	 * 根据美发师(hairdresser)id查询发型套餐列表
	 */
	@Override
	public JSONObject searchUserHairpackedByHairdresserid(String hairdresserid)
			throws BaseException {
		List list=new ArrayList();
		//根据美发师id，查找套餐id
		int id=Integer.parseInt(hairdresserid);
		List<UserMyhairPackedModel> umpList = this.userMyhairpackedDao.findAllpackedByid(id);
		//通过id查找套餐列表
		for (UserMyhairPackedModel userMyhairPackedModel : umpList) {
			UserHairpackedModel uhm = this.userHairpackedDao.searchUserHairpackedById(userMyhairPackedModel.getHairpackedid());
			if(uhm!=null){
			//根据套餐列表的id，找到所有item
			List<UserHairpackedItemModel> hpiList = this.userHairpackedItemDao.getHairpackedItemByHairId(uhm.getId());
	//			uhm.setLonghair(hpiList.get);
				for (UserHairpackedItemModel userHairpackedItemModel : hpiList) {
					switch(userHairpackedItemModel.getStyle()){
					case 1:
						uhm.setLonghair(userHairpackedItemModel.getPrice().toString());
						break;
					case 2:
						uhm.setInhair(userHairpackedItemModel.getPrice().toString());
						break;
					case 3:
						uhm.setShorthair(userHairpackedItemModel.getPrice().toString());
						break;
					}
				}
				uhm.setUhiList(hpiList);
				list.add(uhm);
			}
		}
		JSONObject json = new JSONObject();
		json.put( Constant.DATA, list);
		json.put(Constant.CODE, Constant.CODE_1000);
		json.put(Constant.MSG, Constant.MSG_0);
		return json;
	}
	
	@Override
	public int getPackedLength(String storeid){
		UserHairpackedModel userHairpacked = new UserHairpackedModel();
		userHairpacked.setStoreid(Integer.parseInt(storeid));
		List<UserHairpackedModel> searchUserHairpackedLength = userHairpackedDao.searchUserHairpackedLength(userHairpacked);
		return searchUserHairpackedLength.size();
	}

	@Override
	public JSONObject searchUserHairpackedByStoreid(String storeid, int page,
			int rows) throws BaseException {
		JSONObject json = new JSONObject();
		UserHairpackedModel userHairpacked = new UserHairpackedModel();
		userHairpacked.setStoreid(Integer.parseInt(storeid));
		List<UserHairpackedModel> userHairpackedModels = userHairpackedDao
				.searchUserHairpacked(userHairpacked, page, rows);
		for (int i = 0; i < userHairpackedModels.size(); i++) {
			UserHairpackedModel userHairpackedModel = userHairpackedModels.get(i);
			userHairpackedModel.setUserHairpackedItemModels(userHairpackedItemDao.searchUserHairpackedItem(userHairpackedModel.getId()));
			userHairpackedModel.setUserMyhairPackedModels(userMyhairpackedDao
					.searchUserMyHairpacked(userHairpackedModel.getId()));
			StringBuffer items = new StringBuffer("(");
			StringBuffer hairdressers = new StringBuffer();
			for (int j = 0; j < userHairpackedModel.getUserHairpackedItemModels().size(); j++) {
				items.append(userHairpackedModel.getUserHairpackedItemModels()
						.get(j).getPrice());
				items.append(",");
			}
			for (int j = 0; j < userHairpackedModel.getUserMyhairPackedModels().size(); j++) {
				hairdressers.append(userHairpackedModel
						.getUserMyhairPackedModels().get(j).getNickname());
				hairdressers.append(",");
			}
			String itemsStr = items.substring(0, items.length() - 1);
			userHairpackedModel.setItems(itemsStr + ")");
			userHairpackedModel.setHairdressers(hairdressers.substring(0,
					hairdressers.length() - 1));
		}
		json.put( Constant.DATA, userHairpackedModels);
		json.put(Constant.CODE, Constant.CODE_1000);
		json.put(Constant.MSG, Constant.MSG_0);
		return json;
	}

	@Override
	public JSONObject searchUserHairpackedDetailsByStoreid(String storeid,
			int page, int rows) throws BaseException {
		JSONObject json = new JSONObject();
		UserHairpackedModel userHairpacked = new UserHairpackedModel();
		userHairpacked.setStoreid(Integer.parseInt(storeid));
		List<UserHairpackedModel> userHairpackedModels = userHairpackedDao
				.searchUserHairpackedAll(userHairpacked, page, rows);
		for (int i = 0; i < userHairpackedModels.size(); i++) {
			UserHairpackedModel userHairpackedModel = userHairpackedModels.get(i);
			userHairpackedModel.setUserHairpackedItemModels(userHairpackedItemDao.searchUserHairpackedItem(userHairpackedModel.getId()));
			userHairpackedModel.setUserMyhairPackedModels(userMyhairpackedDao
					.searchUserMyHairpacked(userHairpackedModel.getId()));
			StringBuffer items = new StringBuffer("(");
			StringBuffer hairdressers = new StringBuffer();
			for (int j = userHairpackedModel.getUserHairpackedItemModels().size(); j >=0; j--) {
				items.append(userHairpackedModel.getUserHairpackedItemModels().get(j).getPrice());
				items.append(",");
			}
			for (int j = 0; j < userHairpackedModel
					.getUserMyhairPackedModels().size(); j++) {
				hairdressers.append(userHairpackedModel
						.getUserMyhairPackedModels().get(j).getNickname());
				hairdressers.append(",");
			}
			String itemsStr = items.substring(0, items.length() - 1);
			userHairpackedModel.setItems(itemsStr + ")");
			userHairpackedModel.setHairdressers(hairdressers.substring(0,
					hairdressers.length() - 1));
		}
		json.put( Constant.DATA, userHairpackedModels);
		json.put(Constant.CODE, Constant.CODE_1000);
		json.put(Constant.MSG, Constant.MSG_0);
		return json;
	}

	@Override
	public JSONObject searchUserHairpackedByid(String hairpackedId)
			throws BaseException {
		JSONObject json = new JSONObject();
		//获取套餐数据
		UserHairpackedModel userHairpackedModel = userHairpackedDao.searchUserHairpackedByIdnoState(Integer.parseInt(hairpackedId));
		//获取套餐项数据
		userHairpackedModel.setUserHairpackedItemModels(userHairpackedItemDao.searchUserHairpackedItem(userHairpackedModel.getId()));
		//获取套餐美发师数据
		userHairpackedModel.setUserMyhairPackedModels(userMyhairpackedDao.searchUserMyHairpacked(userHairpackedModel.getId()));
		StringBuffer items = new StringBuffer("(");
		StringBuffer hairdressers = new StringBuffer();
		for (int j = 0; j < userHairpackedModel.getUserHairpackedItemModels().size(); j++) {
			items.append(userHairpackedModel.getUserHairpackedItemModels().get(j).getPrice());
			items.append(",");
			UserHairpackedItemModel item = userHairpackedModel.getUserHairpackedItemModels().get(j);
			if(item.getStyle()==1){
				userHairpackedModel.setLonghair(item.getPrice().toString());
			}
			if(item.getStyle()==2){
				userHairpackedModel.setInhair(item.getPrice().toString());
			}
			if(item.getStyle()==3){
				userHairpackedModel.setShorthair(item.getPrice().toString());
			}
		}
		for (int j = 0; j < userHairpackedModel.getUserMyhairPackedModels()
				.size(); j++) {
			hairdressers.append(userHairpackedModel.getUserMyhairPackedModels().get(j).getNickname());
			hairdressers.append(",");
		}
		String itemsStr = items.substring(0, items.length() - 1);
		userHairpackedModel.setItems(itemsStr + ")");
		if(hairdressers.length()!=0){
			userHairpackedModel.setHairdressers(hairdressers.substring(0,hairdressers.length() - 1));
		}
		json.put( Constant.DATA, userHairpackedModel);
		json.put(Constant.CODE, Constant.CODE_1000);
		json.put(Constant.MSG, Constant.MSG_0);
		return json;
	}


	/**
	 * 套餐管理
	 * @param packed_id
	 * @param packed_state
	 * @return
	 * @throws BaseException
	 * @throws NumberFormatException
	 */
	public boolean updateHairPackedForType(String packed_id, String packed_state)
			throws NumberFormatException, BaseException {
		boolean flag = true;
		if (!packed_id.isEmpty()) {
			UserHairpackedModel userHairpacked = this.userHairpackedDao
					.searchUserHairpackedByIdnoState(Integer.parseInt(packed_id));
			userHairpacked.setState(Integer.parseInt(packed_state));
			this.userHairpackedDao.updateUserHairpacked(userHairpacked);
		} else {
			flag = false;
		}
		return flag;
	}

	/**
	 * 异步修改美发师与套餐关系
	 * 
	 * @param packed_id
	 * @param opration
	 * @param userid
	 * @return
	 */
	@Override
	public boolean updateHairPackedForUserId(String packed_id, String opration,
			String userid) throws NumberFormatException, BaseException {
		boolean flag = true;
		if (!packed_id.isEmpty() && !userid.isEmpty()) {
			if ("1".equals(opration)) {
				UserMyhairPackedModel model = new UserMyhairPackedModel();
				UserinfoModel userinfoModel = userinfoDao
						.searchUserinfoById(Integer.parseInt(userid));
				model.setHairpackedid(Integer.parseInt(packed_id));
				model.setUserid(Integer.parseInt(userid));
				model.setNickname(userinfoModel.getNickname());
				model.setUsednum(0);
				model.setState(1);
				model.setCreatetime(new Date());
				this.userMyhairpackedDao.addUserMyhairpacked(model);
			} else {
				UserMyhairPackedModel model = this.userMyhairpackedDao
						.getUserMyHairPackedModel(Integer.parseInt(userid),
								Integer.parseInt(packed_id));
				if (model != null ) {
					this.userMyhairpackedDao.deleteUserMyhairpacked(model.getId());
				} else {
					flag = false;
				}
			}
		} else {
			flag = false;
		}
		return flag;
	}
	/**
	 * 封装美发师套餐列表
	 * @return   
	 * @throws BaseException 
	 * @throws NumberFormatException 
	 * @throws
	 * @author lnn
	 * @date 2016年1月14日
	 */
	public List<UserMyhairPackedModel> packMyhairPackedModel(Integer hairpackedid,List<String> hairdresserids) throws NumberFormatException, BaseException{
		List<UserMyhairPackedModel> myhairPackedModelList = new ArrayList<UserMyhairPackedModel>();
		// 添加美发师可做的套餐
		for (String str : hairdresserids) {
			UserinfoModel userinfoModel = userinfoDao
					.searchUserinfoById(Integer.parseInt(str));
			UserMyhairPackedModel userMyhairPackedModel = new UserMyhairPackedModel();
			userMyhairPackedModel.setHairpackedid(hairpackedid);
			userMyhairPackedModel.setUserid(Integer.parseInt(str));
			userMyhairPackedModel.setNickname(userinfoModel.getNickname());
			userMyhairPackedModel.setUsednum(0);
			userMyhairPackedModel.setState(1);
			userMyhairPackedModel.setCreatetime(new Date());
			myhairPackedModelList.add(userMyhairPackedModel);
		}		
		return myhairPackedModelList;
	}

	@Override
	public List searchUserHairpackedByStoreidList(String storeid, int page,
			int rows) throws BaseException {
		UserHairpackedModel userHairpacked = new UserHairpackedModel();
		userHairpacked.setStoreid(Integer.parseInt(storeid));
		List<UserHairpackedModel> userHairpackedModels = userHairpackedDao
				.searchUserHairpackedAll(userHairpacked, page, rows);
		for (int i = 0; i < userHairpackedModels.size(); i++) {
			UserHairpackedModel userHairpackedModel = userHairpackedModels.get(i);
			userHairpackedModel.setUserHairpackedItemModels(userHairpackedItemDao.searchUserHairpackedItem(userHairpackedModel.getId()));
			userHairpackedModel.setUserMyhairPackedModels(userMyhairpackedDao
					.searchUserMyHairpacked(userHairpackedModel.getId()));
			StringBuffer items = new StringBuffer("(");
			StringBuffer hairdressers = new StringBuffer();
			for (int j = 0; j < userHairpackedModel.getUserHairpackedItemModels().size(); j++) {
				items.append(userHairpackedModel.getUserHairpackedItemModels().get(j).getPrice());
				items.append(",");
			}
			
			for (int j = 0; j < userHairpackedModel.getUserMyhairPackedModels().size(); j++) {
				hairdressers.append(userHairpackedModel	
						.getUserMyhairPackedModels().get(j).getNickname());
				hairdressers.append(",");
			}
			String itemsStr = items.substring(0, items.length() - 1);
			userHairpackedModel.setItems(itemsStr + ")");
			if(hairdressers.length()!=0){
				userHairpackedModel.setHairdressers(hairdressers.substring(0,
					hairdressers.length() - 1));
			}
			if(userHairpackedModel.getState()==1){
				userHairpackedModel.setStateString("可用");
			}else if(userHairpackedModel.getState()==0){
				userHairpackedModel.setStateString("不可用");
			}
		}
		return userHairpackedModels;
	}
}
