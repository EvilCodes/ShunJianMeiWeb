package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_photo_mapping")
public class UserPhotoMappingModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int userid;
	private int picid;
	private int type;
	private String remark;
	private int sortnum;
	private Date createtime;
	@OneToOne
	@JoinColumn(name="picid",insertable=false,updatable=false)
	private BasePictureModel basePicture;
	
	
	public int getId() {
		return id;
	}
	@Column(name = "userid")
	public int getUserid() {
		return userid;
	}
	public int getPicid() {
		return picid;
	}
	@Column(name = "type")
	public int getType() {
		return type;
	}
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	@Column(name = "sortnum")
	public int getSortnum() {
		return sortnum;
	}
	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public void setPicid(int picid) {
		this.picid = picid;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setSortnum(int sortnum) {
		this.sortnum = sortnum;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public BasePictureModel getBasePicture() {
		return basePicture;
	}
	public void setBasePicture(BasePictureModel basePicture) {
		this.basePicture = basePicture;
	}
	
}
