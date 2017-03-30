package com.wenyuankeji.spring.model;


public class UserTradelogSearchModel {

	private String ordercode;
	private String stylename;
	private String amount;
	private String appointmenttime;
	private String createtime;

	public String getOrdercode() {
		return ordercode;
	}

	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}

	public String getStylename() {
		return stylename;
	}

	public void setStylename(String stylename) {
		this.stylename = stylename;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAppointmenttime() {
		return appointmenttime;
	}

	public void setAppointmenttime(String appointmenttime) {
		this.appointmenttime = appointmenttime;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

}
