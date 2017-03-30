package com.wenyuankeji.spring.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @ClassNameUserHairpacked
 * @Description 发型套餐
 * @date 2016-01-11 13:19:17
 * @author lnn
 */
@Entity
@Table(name = "user_hairpacked")
public class UserHairpackedModel {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private Integer id; // 套餐id
	private Integer storeid; // 理发店id
	private String name; // 套餐名称
	private String intro; // 套餐介绍
	private Integer times; // 服务时长
	private Integer state; // 服务状态 0不可以 1 可用
	private Integer style; // 套餐类型，0普通套餐
	private Date createtime; // 创建时间
	
	@Transient
	private List<UserHairpackedItemModel> userHairpackedItemModels;
	@Transient
	private List<UserMyhairPackedModel> userMyhairPackedModels;
	@Transient
	private String items;
	@Transient
	private String hairdressers;
	
	@Transient
	private List<UserHairpackedItemModel> uhiList;
	@Transient
	private String longhair;
	@Transient
	private String inhair;
	@Transient
	private String shorthair;
	@Transient
	private String stateString;
	
	public String getStateString() {
		return stateString;
	}

	public void setStateString(String stateString) {
		this.stateString = stateString;
	}

	public String getLonghair() {
		return longhair;
	}

	public void setLonghair(String longhair) {
		this.longhair = longhair;
	}

	public String getInhair() {
		return inhair;
	}

	public void setInhair(String inhair) {
		this.inhair = inhair;
	}

	public String getShorthair() {
		return shorthair;
	}

	public void setShorthair(String shorthair) {
		this.shorthair = shorthair;
	}

	public List<UserHairpackedItemModel> getUhiList() {
		return uhiList;
	}

	public void setUhiList(List<UserHairpackedItemModel> uhiList) {
		this.uhiList = uhiList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	
	
	@Column(name = "storeid")
	public Integer getStoreid() {
		return storeid;
	}

	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}

	@Column(name = "intro")
	public String getIntro() {
		return intro;
	}
	@Column(name = "times")
	public Integer getTimes() {
		return times;
	}
	@Column(name = "state")
	public Integer getState() {
		return state;
	}
	@Column(name = "style")
	public Integer getStyle() {
		return style;
	}
	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	public void setTimes(Integer times) {
		this.times = times;
	}
	
	public void setState(Integer state) {
		this.state = state;
	}
	
	public void setStyle(Integer style) {
		this.style = style;
	}
	

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public List<UserHairpackedItemModel> getUserHairpackedItemModels() {
		return userHairpackedItemModels;
	}

	public void setUserHairpackedItemModels(
			List<UserHairpackedItemModel> userHairpackedItemModels) {
		this.userHairpackedItemModels = userHairpackedItemModels;
	}

	public List<UserMyhairPackedModel> getUserMyhairPackedModels() {
		return userMyhairPackedModels;
	}

	public void setUserMyhairPackedModels(
			List<UserMyhairPackedModel> userMyhairPackedModels) {
		this.userMyhairPackedModels = userMyhairPackedModels;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public String getHairdressers() {
		return hairdressers;
	}

	public void setHairdressers(String hairdressers) {
		this.hairdressers = hairdressers;
	}
}
