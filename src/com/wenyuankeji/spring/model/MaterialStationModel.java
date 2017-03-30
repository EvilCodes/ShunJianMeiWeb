package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "material_station")
public class MaterialStationModel {

	@Id
	private int stationid;
	private String stationname;
	private String address;
	private String tel;
	private int quantity;
	private Date createtime;
	
	public int getStationid() {
		return stationid;
	}
	@Column(name = "stationname")
	public String getStationname() {
		return stationname;
	}
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	@Column(name = "tel")
	public String getTel() {
		return tel;
	}
	@Column(name = "quantity")
	public int getQuantity() {
		return quantity;
	}
	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}
	public void setStationid(int stationid) {
		this.stationid = stationid;
	}
	public void setStationname(String stationname) {
		this.stationname = stationname;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
}
