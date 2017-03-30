package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "store_workdate")
public class StoreWorkdateModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int wdid;
	private int storeid;
	private int year;
	private int month;
	private int day;
	private Date createtime;
	
//	@OneToMany
//	@JoinColumn(name = "wdid", insertable = false, updatable = false)
//	@OrderBy("chairid ASC,hour ASC")
//	private Set<StoreWorkhourModel> storeWorkhours = new HashSet<StoreWorkhourModel>();
	
	public int getWdid() {
		return wdid;
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
	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}
//	public Set<StoreWorkhourModel> getStoreWorkhours() {
//		return storeWorkhours;
//	}
//	public void setStoreWorkhours(Set<StoreWorkhourModel> storeWorkhours) {
//		this.storeWorkhours = storeWorkhours;
//	}
	public void setWdid(int wdid) {
		this.wdid = wdid;
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
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	

}
