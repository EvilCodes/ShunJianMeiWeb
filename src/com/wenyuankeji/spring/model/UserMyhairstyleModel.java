package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_myhairstyle")
public class UserMyhairstyleModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mystyleid;
	private int userid;
	private int styleid;
	private String name;
	private float price;
	private int times;
	private int deleted;
	private int usednum;
	private Date createtime;
	private String intro;
	private int state;

	public int getMystyleid() {
		return mystyleid;
	}
	@Column(name = "userid")
	public int getUserid() {
		return userid;
	}
	@Column(name = "styleid")
	public int getStyleid() {
		return styleid;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	@Column(name = "price")
	public float getPrice() {
		return price;
	}
	@Column(name = "times")
	public int getTimes() {
		return times;
	}
	@Column(name = "deleted")
	public int getDeleted() {
		return deleted;
	}
	@Column(name = "usednum")
	public int getUsednum() {
		return usednum;
	}
	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}
	@Column(name = "intro")
	public String getIntro() {
		return intro;
	}

	public void setMystyleid(int mystyleid) {
		this.mystyleid = mystyleid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public void setStyleid(int styleid) {
		this.styleid = styleid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public void setUsednum(int usednum) {
		this.usednum = usednum;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
	@Column(name = "state")
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

}
