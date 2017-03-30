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
@Table(name = "store_photo_mapping")
public class StorePhotoMappingModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int storeid;
	private int picid;
	private int type;
	private Date createtime;
	@OneToOne
	@JoinColumn(name = "picid", insertable = false, updatable = false)
	private BasePictureModel basePicture;

	public int getId() {
		return id;
	}

	@Column(name = "storeid")
	public int getStoreid() {
		return storeid;
	}

	@Column(name = "picid")
	public int getPicid() {
		return picid;
	}

	@Column(name = "type")
	public int getType() {
		return type;
	}

	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStoreid(int storeid) {
		this.storeid = storeid;
	}

	public void setPicid(int picid) {
		this.picid = picid;
	}

	public void setType(int type) {
		this.type = type;
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
