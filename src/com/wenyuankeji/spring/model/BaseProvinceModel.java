package com.wenyuankeji.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "base_province")
public class BaseProvinceModel {

	@Id
	private int provinceid;
	private String provincename;
	
	public int getProvinceid() {
		return provinceid;
	}
	@Column(name = "provincename")
	public String getProvincename() {
		return provincename;
	}
	public void setProvinceid(int provinceid) {
		this.provinceid = provinceid;
	}
	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}
	
}
