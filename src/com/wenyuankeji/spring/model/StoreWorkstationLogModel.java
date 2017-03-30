package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "store_workstation_log")
public class StoreWorkstationLogModel {

	@Id
	private int logid;
	private int wsid;
	private int storeid;
	private int year;
	private int month;
	private int day;
	private int hour;
	private int state;
	private int orderid;
	private Date createtime;
	
	public int getLogid() {
		return logid;
	}
	@Column(name = "wsid")
	public int getWsid() {
		return wsid;
	}
	@Column(name = "storeid")
	public int getStoreid() {
		return storeid;
	}
	@Column(name = "year")
	public int getYear() {
		return year;
	}
	@Column(name = "month")
	public int getMonth() {
		return month;
	}
	@Column(name = "day")
	public int getDay() {
		return day;
	}
	@Column(name = "hour")
	public int getHour() {
		return hour;
	}
	@Column(name = "state")
	public int getState() {
		return state;
	}
	@Column(name = "orderid")
	public int getOrderid() {
		return orderid;
	}
	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}
	public void setLogid(int logid) {
		this.logid = logid;
	}
	public void setWsid(int wsid) {
		this.wsid = wsid;
	}
	public void setStoreid(int storeid) {
		this.storeid = storeid;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public void setState(int state) {
		this.state = state;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
}
