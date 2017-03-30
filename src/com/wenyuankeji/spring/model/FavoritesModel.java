package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "favorites")
public class FavoritesModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int userid;
	private int type;
	private int item;
	private Date createtime;

	public int getId() {
		return id;
	}

	@Column(name = "userid")
	public int getUserid() {
		return userid;
	}

	@Column(name = "type")
	public int getType() {
		return type;
	}

	@Column(name = "item")
	public int getItem() {
		return item;
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

	public void setType(int type) {
		this.type = type;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
