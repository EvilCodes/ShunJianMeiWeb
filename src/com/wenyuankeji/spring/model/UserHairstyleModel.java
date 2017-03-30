package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_hairstyle")
public class UserHairstyleModel {

	@Id
	private int styleid;
	private String name;
	private float displayorder;
	private Date createtime;
	
	public int getStyleid() {
		return styleid;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	@Column(name = "displayorder")
	public float getDisplayorder() {
		return displayorder;
	}
	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}
	
	public void setId(int styleid) {
		this.styleid = styleid;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDisplayorder(float displayorder) {
		this.displayorder = displayorder;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	
}
