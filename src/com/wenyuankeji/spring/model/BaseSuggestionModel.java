package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "base_suggestion")
public class BaseSuggestionModel {
	@Id
	private int sid;
	private int userid;
	private String email;
	private String content;
	private int source;
	private Date createtime;

	public int getSid() {
		return sid;
	}

	@Column(name = "userid")
	public int getUserid() {
		return userid;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	@Column(name = "content")
	public String getContent() {
		return content;
	}

	@Column(name = "source")
	public int getSource() {
		return source;
	}

	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
