package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "materialinfo")
public class MaterialinfoModel {

	@Id
	private int materialid;
	private int brandid;
	private int categoryid;
	private String materialname;
	private Date createtime;
	
	
	public int getMaterialid() {
		return materialid;
	}
	@Column(name = "brandid")
	public int getBrandid() {
		return brandid;
	}
	@Column(name = "categoryid")
	public int getCategoryid() {
		return categoryid;
	}
	@Column(name = "materialname")
	public String getMaterialname() {
		return materialname;
	}
	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}
	public void setMaterialid(int materialid) {
		this.materialid = materialid;
	}
	public void setBrandid(int brandid) {
		this.brandid = brandid;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	public void setMaterialname(String materialname) {
		this.materialname = materialname;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	
	
}
