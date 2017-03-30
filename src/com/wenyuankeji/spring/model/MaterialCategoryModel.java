package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "material_category")
public class MaterialCategoryModel {

	@Id
	private int categoryid;
	private String categoryname;
	private Date createtime;
	
	public int getCategoryid() {
		return categoryid;
	}
	@Column(name = "categoryname")
	public String getCategoryname() {
		return categoryname;
	}
	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
}
