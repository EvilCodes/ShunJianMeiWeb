package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "base_businessarea")
public class BaseBusinessareaModel {

	@Id
	private int areaid;
	private int cityid;
	private int districtid;
	private String areaname;
	private Date createtime;
	
	public int getAreaid() {
		return areaid;
	}
	@Column(name = "cityid")
	public int getCityid() {
		return cityid;
	}
	@Column(name = "districtid")
	public int getDistrictid() {
		return districtid;
	}
	@Column(name = "areaname")
	public String getAreaname() {
		return areaname;
	}
	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}
	public void setAreaid(int areaid) {
		this.areaid = areaid;
	}
	public void setCityid(int cityid) {
		this.cityid = cityid;
	}
	public void setDistrictid(int districtid) {
		this.districtid = districtid;
	}
	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
}
