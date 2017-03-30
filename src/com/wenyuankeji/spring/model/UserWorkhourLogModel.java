package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_workhour_log")
public class UserWorkhourLogModel {

	@Id
	private int logid;
	private int whid;
	private int wdid;
	private int hour;
	private int state;
	private int orderid;
	private Date createtime;
	
	public int getLogid() {
		return logid;
	}
	@Column(name = "whid")
	public int getWhid() {
		return whid;
	}
	@Column(name = "wdid")
	public int getWdid() {
		return wdid;
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
	public void setWhid(int whid) {
		this.whid = whid;
	}
	public void setWdid(int wdid) {
		this.wdid = wdid;
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
