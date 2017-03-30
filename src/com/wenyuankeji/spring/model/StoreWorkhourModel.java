package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "store_workhour")
public class StoreWorkhourModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int whid;
	private int wdid;
	private int chairid;
	private int hour;
	private int state;
	private int orderid;
	private Date createtime;
	
	@ManyToOne
	@JoinColumn(name = "wdid", insertable = false, updatable = false)
	private StoreWorkdateModel StoreWorkdate;
	
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
	public StoreWorkdateModel getStoreWorkdate() {
		return StoreWorkdate;
	}
	public void setStoreWorkdate(StoreWorkdateModel storeWorkdate) {
		StoreWorkdate = storeWorkdate;
	}
	public int getChairid() {
		return chairid;
	}
	@Column(name = "chairid")
	public void setChairid(int chairid) {
		this.chairid = chairid;
	}
	

}
