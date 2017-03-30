package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "base_verificationcode")
public class BaseVerificationcodeModel {

	@Id
	private int id;
	private String mobile;
	private String code;
	private int type;
	private Date createtime;
	private Date expirationtime;
	
	public int getId() {
		return id;
	}
	@Column(name = "mobile")
	public String getMobile() {
		return mobile;
	}
	@Column(name = "code")
	public String getCode() {
		return code;
	}
	@Column(name = "type")
	public int getType() {
		return type;
	}
	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}
	@Column(name = "expirationtime")
	public Date getExpirationtime() {
		return expirationtime;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public void setExpirationtime(Date expirationtime) {
		this.expirationtime = expirationtime;
	}
	
	
}
