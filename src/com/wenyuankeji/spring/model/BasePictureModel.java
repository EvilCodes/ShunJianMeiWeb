package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "base_picture")
public class BasePictureModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int picid;
	private String picturepath;
	private Date createtime;
	public int getPicid() {
		return picid;
	}
	@Column(name = "picturepath")
	public String getPicturepath() {
		return picturepath;
	}
	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}
	public void setPicid(int picid) {
		this.picid = picid;
	}
	public void setPicturepath(String picturepath) {
		this.picturepath = picturepath;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	
}
