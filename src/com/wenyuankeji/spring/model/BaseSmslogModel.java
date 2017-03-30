package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "base_smslog")
public class BaseSmslogModel {

	@Id
	private int logid;
	private String mobile;
	private String smscontent;
	private int messagetype;
	private String remark;
	private Date sendtime;
	private boolean state;
	
	public int getLogid() {
		return logid;
	}
	@Column(name = "mobile")
	public String getMobile() {
		return mobile;
	}
	@Column(name = "smscontent")
	public String getSmscontent() {
		return smscontent;
	}
	@Column(name = "messagetype")
	public int getMessagetype() {
		return messagetype;
	}
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	@Column(name = "sendtime")
	public Date getSendtime() {
		return sendtime;
	}
	@Column(name = "state")
	public boolean isState() {
		return state;
	}
	public void setLogid(int logid) {
		this.logid = logid;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setSmscontent(String smscontent) {
		this.smscontent = smscontent;
	}
	public void setMessagetype(int messagetype) {
		this.messagetype = messagetype;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	
}
