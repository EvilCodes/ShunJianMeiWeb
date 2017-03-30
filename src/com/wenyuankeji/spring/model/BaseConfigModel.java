package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "base_config")
public class BaseConfigModel {

	@Id
	private int configid;
	private String configcode;
	private String configvalue;
	private String value1;
	private String value2;
	private float sortnum;
	private String remark;
	private Date createtime;
	
	public int getConfigid() {
		return configid;
	}
	@Column(name = "configcode")
	public String getConfigcode() {
		return configcode;
	}
	@Column(name = "configvalue")
	public String getConfigvalue() {
		return configvalue;
	}
	@Column(name = "value1")
	public String getValue1() {
		return value1;
	}
	@Column(name = "value2")
	public String getValue2() {
		return value2;
	}
	@Column(name = "sortnum")
	public float getSortnum() {
		return sortnum;
	}
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}
	public void setConfigid(int configid) {
		this.configid = configid;
	}
	public void setConfigcode(String configcode) {
		this.configcode = configcode;
	}
	public void setConfigvalue(String configvalue) {
		this.configvalue = configvalue;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	public void setSortnum(float sortnum) {
		this.sortnum = sortnum;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
}
