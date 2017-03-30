package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_hairstyle_material")
public class UserHairstyleMaterialModel {

	@Id
	private int id;
	private int styleid;
	private String servicecode;
	private int materialid;
	private float price;
	private Date createtime;

	public int getId() {
		return id;
	}

	@Column(name = "styleid")
	public int getStyleid() {
		return styleid;
	}

	@Column(name = "servicecode")
	public String getServicecode() {
		return servicecode;
	}

	@Column(name = "materialid")
	public int getMaterialid() {
		return materialid;
	}

	@Column(name = "price")
	public float getPrice() {
		return price;
	}

	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStyleid(int styleid) {
		this.styleid = styleid;
	}

	public void setServicecode(String servicecode) {
		this.servicecode = servicecode;
	}

	public void setMaterialid(int materialid) {
		this.materialid = materialid;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
