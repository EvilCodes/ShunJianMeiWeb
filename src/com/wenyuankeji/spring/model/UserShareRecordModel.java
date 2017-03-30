package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_sharerecord")
public class UserShareRecordModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int recordid;
	private int userid;
	private int evaid;
	private String picid;
	private Date createtime;
	
	@Column(name = "recordid")
	public int getRecordid() {
		return recordid;
	}
	@Column(name = "userid")
	public int getUserid() {
		return userid;
	}
	@Column(name = "evaid")
	public int getEvaid() {
		return evaid;
	}
	@Column(name = "picid")
	public String getPicid() {
		return picid;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public void setEvaid(int evaid) {
		this.evaid = evaid;
	}
	public void setPicid(String picid) {
		this.picid = picid;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	
}
