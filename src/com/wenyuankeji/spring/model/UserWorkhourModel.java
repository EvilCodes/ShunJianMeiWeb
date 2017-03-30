package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_workhour")
public class UserWorkhourModel {
	@Id
	private int whid;
	private int wdid;
	private int hour;
	private int state;
	private int orderid;
	private Date createtime;
	// add by jiazhaohui
	// 时间点对应的订单类型
	private int ordertype;
	
	@ManyToOne
	@JoinColumn(name = "wdid", insertable = false, updatable = false)
	private UserWorkdateModel userWorkdate;
	
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
	// add by jiazhaohui
	@Column(name = "ordertype")
	public int getOrdertype() {
		return ordertype;
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
	public UserWorkdateModel getUserWorkdate() {
		return userWorkdate;
	}
	public void setUserWorkdate(UserWorkdateModel userWorkdate) {
		this.userWorkdate = userWorkdate;
	}
	
	// add by jiazhaohui
	public void setOrdertype(int ordertype) {
		this.ordertype = ordertype;
	}
}
