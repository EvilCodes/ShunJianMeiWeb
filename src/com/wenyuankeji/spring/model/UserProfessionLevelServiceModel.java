package com.wenyuankeji.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_profession_level_service")
public class UserProfessionLevelServiceModel {
	@Id
	private int id;
	private int levelid;
	private String servicecode;
	private int price;
	private int times;

	public int getId() {
		return id;
	}

	@Column(name = "levelid")
	public int getLevelid() {
		return levelid;
	}

	@Column(name = "servicecode")
	public String getServicecode() {
		return servicecode;
	}

	@Column(name = "price")
	public int getPrice() {
		return price;
	}

	@Column(name = "times")
	public int getTimes() {
		return times;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLevelid(int levelid) {
		this.levelid = levelid;
	}

	public void setServicecode(String servicecode) {
		this.servicecode = servicecode;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setTimes(int times) {
		this.times = times;
	}

}
