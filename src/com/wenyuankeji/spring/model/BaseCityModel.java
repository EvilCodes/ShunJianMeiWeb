package com.wenyuankeji.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "base_city")
public class BaseCityModel {

	@Id
	private int cityid;
	private String cityname;
	private String zipcode;
	private int provinceid;
	public int getCityid() {
		return cityid;
	}
	@Column(name = "cityname")
	public String getCityname() {
		return cityname;
	}
	@Column(name = "zipcode")
	public String getZipcode() {
		return zipcode;
	}
	@Column(name = "provinceid")
	public int getProvinceid() {
		return provinceid;
	}
	public void setCityid(int cityid) {
		this.cityid = cityid;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public void setProvinceid(int provinceid) {
		this.provinceid = provinceid;
	}
	
	
}
