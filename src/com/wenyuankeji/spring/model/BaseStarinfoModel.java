package com.wenyuankeji.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "base_starinfo")
public class BaseStarinfoModel {
	@Id
	private int starid;
	private String starname;
	private int beginmonth;
	private int beginday;
	private int endmonth;
	
	public int getStarid() {
		return starid;
	}
	
	@Column(name = "starname")
	public String getStarname() {
		return starname;
	}
	
	@Column(name = "beginmonth")
	public int getBeginmonth() {
		return beginmonth;
	}
	
	@Column(name = "beginday")
	public int getBeginday() {
		return beginday;
	}
	
	@Column(name = "endmonth")
	public int getEndmonth() {
		return endmonth;
	}

	public void setStarid(int starid) {
		this.starid = starid;
	}

	public void setStarname(String starname) {
		this.starname = starname;
	}

	public void setBeginmonth(int beginmonth) {
		this.beginmonth = beginmonth;
	}

	public void setBeginday(int beginday) {
		this.beginday = beginday;
	}

	public void setEndmonth(int endmonth) {
		this.endmonth = endmonth;
	}

}
