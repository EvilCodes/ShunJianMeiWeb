package com.wenyuankeji.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "serviceattribute")
public class ServiceattributeModel {
	@Id
	private String servicecode;
	private String servicename;

	public String getServicecode() {
		return servicecode;
	}

	@Column(name = "servicename")
	public String getServicename() {
		return servicename;
	}

	public void setServicecode(String servicecode) {
		this.servicecode = servicecode;
	}

	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

}