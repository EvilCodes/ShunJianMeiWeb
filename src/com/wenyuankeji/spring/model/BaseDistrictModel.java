package com.wenyuankeji.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "base_district")
public class BaseDistrictModel {

	@Id
	private int districtid;
	private String districtname;
	private int cityid;
	
	public int getDistrictid() {
		return districtid;
	}
	@Column(name = "districtname")
	public String getDistrictname() {
		return districtname;
	}
	@Column(name = "cityid")
	public int getCityid() {
		return cityid;
	}
	public void setDistrictid(int districtid) {
		this.districtid = districtid;
	}
	public void setDistrictname(String districtname) {
		this.districtname = districtname;
	}
	public void setCityid(int cityid) {
		this.cityid = cityid;
	}
	
}
