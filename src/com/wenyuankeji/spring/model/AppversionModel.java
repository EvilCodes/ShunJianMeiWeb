package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "appversion")
public class AppversionModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int vid;
	private String vcode;
	private String vname;
	private String intro;
	private String url;
	private int type;
	private int important;
	private int deleted;
	private Date createtime;

	public int getVid() {
		return vid;
	}

	@Column(name = "vcode")
	public String getVcode() {
		return vcode;
	}

	@Column(name = "vname")
	public String getVname() {
		return vname;
	}

	@Column(name = "intro")
	public String getIntro() {
		return intro;
	}

	@Column(name = "url")
	public String getUrl() {
		return url;
	}

	@Column(name = "type")
	public int getType() {
		return type;
	}

	@Column(name = "important")
	public int getImportant() {
		return important;
	}

	@Column(name = "deleted")
	public int getDeleted() {
		return deleted;
	}

	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}

	public void setVid(int vid) {
		this.vid = vid;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setImportant(int important) {
		this.important = important;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
