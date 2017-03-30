package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "base_requestlog")
public class BaseRequestlog {
	@Id
	private int logid;
	private String url;
	private String para1;
	private String para2;
	private String result;
	private Date createtime;
	
	public int getLogid() {
		return logid;
	}
	
	@Column(name = "url")
	public String getUrl() {
		return url;
	}
	@Column(name = "para1")
	public String getPara1() {
		return para1;
	}
	@Column(name = "para2")
	public String getPara2() {
		return para2;
	}
	@Column(name = "result")
	public String getResult() {
		return result;
	}
	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}

	public void setLogid(int logid) {
		this.logid = logid;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setPara1(String para1) {
		this.para1 = para1;
	}

	public void setPara2(String para2) {
		this.para2 = para2;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
}
