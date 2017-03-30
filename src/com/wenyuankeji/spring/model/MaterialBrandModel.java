package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "material_brand")
public class MaterialBrandModel {

	@Id
	private int brandid;
	private String brandname;
	private Date createtime;
	
	public int getBrandid() {
		return brandid;
	}
	@Column(name = "brandname")
	public String getBrandname() {
		return brandname;
	}
	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}
	public void setBrandid(int brandid) {
		this.brandid = brandid;
	}
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
}
