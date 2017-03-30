package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wenyuankeji.spring.util.ShunJianMeiUtil;

@Entity
@Table(name = "user_profession_level")
public class UserProfessionLevelModel {
	@Id
	private int levelid;
	private int cityid;
	private String levelname;
	private Date createtime;
	private int level;
	
	public int getLevelid() {
		return levelid;
	}
	
	@Column(name = "level")
	public int getLevel() {
		return level;
	}
	
	@Column(name = "cityid")
	public int getCityid() {
		return cityid;
	}
	@Column(name = "levelname")
	public String getLevelname() {
		if (ShunJianMeiUtil.checkNull(levelname)) {
			return "";
		}
		return levelname;
	}
	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}

	public void setLevelid(int levelid) {
		this.levelid = levelid;
	}

	public void setCityid(int cityid) {
		this.cityid = cityid;
	}

	public void setLevelname(String levelname) {
		this.levelname = levelname;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
