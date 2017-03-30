package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_workdate")
public class UserWorkdateModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int wdid;
	private int userid;
	private int year;
	private int month;
	private int day;
	private Date createtime;
	
	public int getWdid() {
		return wdid;
	}
	@Column(name = "userid")
	public int getUserid() {
		return userid;
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
	public void setWdid(int wdid) {
		this.wdid = wdid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
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
