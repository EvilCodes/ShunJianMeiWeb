package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_evaluate_photo_mapping")
public class UserEvaluatePhotoMappingModel {

	@Id
	private int id;
	private int evaid;
	private int picid;
	private Date createtime;
	@OneToOne
	@JoinColumn(name = "picid", insertable = false, updatable = false)
	private BasePictureModel basePicture;

	public int getId() {
		return id;
	}

	@Column(name = "evaid")
	public int getEvaid() {
		return evaid;
	}

	@Column(name = "picid")
	public int getPicid() {
		return picid;
	}

	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setEvaid(int evaid) {
		this.evaid = evaid;
	}

	public void setPicid(int picid) {
		this.picid = picid;
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
