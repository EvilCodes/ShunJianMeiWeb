package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "base_exception")
public class BaseException {

	@Id
	private int id;
	private String source;
	private String paras;
	private String message;
	private Date createtime;
	
	public int getId() {
		return id;
	}
	@Column(name = "source")
	public String getSource() {
		return source;
	}
	@Column(name = "paras")
	public String getParas() {
		return paras;
	}
	@Column(name = "message")
	public String getMessage() {
		return message;
	}
	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public void setParas(String paras) {
		this.paras = paras;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	
}
