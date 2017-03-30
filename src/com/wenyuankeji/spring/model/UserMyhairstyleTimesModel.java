package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_myhairstyle_times")
public class UserMyhairstyleTimesModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int mystyleid;
	private String servicecode;
	private int times;
	private Date createtime;

	public int getId() {
		return id;
	}

	@Column(name = "mystyleid")
	public int getMystyleid() {
		return mystyleid;
	}

	@Column(name = "servicecode")
	public String getServicecode() {
		return servicecode;
	}

	@Column(name = "times")
	public int getTimes() {
		return times;
	}

	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMystyleid(int mystyleid) {
		this.mystyleid = mystyleid;
	}

	public void setServicecode(String servicecode) {
		this.servicecode = servicecode;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
