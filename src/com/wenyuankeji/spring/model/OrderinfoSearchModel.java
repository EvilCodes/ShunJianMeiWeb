package com.wenyuankeji.spring.model;

import java.util.Date;

public class OrderinfoSearchModel {

	/**
	 * 查询显示字段
	 */
	private int orderid;
	private String user_hairstyle_name;
	private String usersubinfo_name;
	private String user_profession_level_name;
	private String appointmenttime;
	private String usersubinfo_contactmobile;
	private String storeinfo_name;
	private String storeinfo_tel;
	private String storeinfo_address;
	private String usersubinfo_pic;
	private float amount;
	private int paystate;
	private int ishairpacked;
	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public String getUser_hairstyle_name() {
		return user_hairstyle_name;
	}

	public void setUser_hairstyle_name(String user_hairstyle_name) {
		this.user_hairstyle_name = user_hairstyle_name;
	}

	public String getUsersubinfo_name() {
		return usersubinfo_name;
	}

	public void setUsersubinfo_name(String usersubinfo_name) {
		this.usersubinfo_name = usersubinfo_name;
	}

	public String getUser_profession_level_name() {
		return user_profession_level_name;
	}

	public void setUser_profession_level_name(String user_profession_level_name) {
		this.user_profession_level_name = user_profession_level_name;
	}

	public String getAppointmenttime() {
		return appointmenttime;
	}

	public void setAppointmenttime(String appointmenttime) {
		this.appointmenttime = appointmenttime;
	}

	public String getUsersubinfo_contactmobile() {
		return usersubinfo_contactmobile;
	}

	public void setUsersubinfo_contactmobile(String usersubinfo_contactmobile) {
		this.usersubinfo_contactmobile = usersubinfo_contactmobile;
	}

	public String getStoreinfo_name() {
		return storeinfo_name;
	}

	public void setStoreinfo_name(String storeinfo_name) {
		this.storeinfo_name = storeinfo_name;
	}

	public String getStoreinfo_tel() {
		return storeinfo_tel;
	}

	public void setStoreinfo_tel(String storeinfo_tel) {
		this.storeinfo_tel = storeinfo_tel;
	}

	public String getStoreinfo_address() {
		return storeinfo_address;
	}

	public void setStoreinfo_address(String storeinfo_address) {
		this.storeinfo_address = storeinfo_address;
	}

	public String getUsersubinfo_pic() {
		return usersubinfo_pic;
	}

	public void setUsersubinfo_pic(String usersubinfo_pic) {
		this.usersubinfo_pic = usersubinfo_pic;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getPaystate() {
		return paystate;
	}

	public void setPaystate(int paystate) {
		this.paystate = paystate;
	}

	public int getIshairpacked() {
		return ishairpacked;
	}

	public void setIshairpacked(int ishairpacked) {
		this.ishairpacked = ishairpacked;
	}

}
