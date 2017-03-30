package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "material_deliveryrecord")
public class MaterialDeliveryrecordModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int recordid;
	private int userid;
	private int stationid;
	private Date appointmenttime;
	private int state;
	private Date deliverytime;
	private Date createtime;
	
	public int getRecordid() {
		return recordid;
	}
	@Column(name = "userid")
	public int getUserid() {
		return userid;
	}
	@Column(name = "stationid")
	public int getStationid() {
		return stationid;
	}
	@Column(name = "appointmenttime")
	public Date getAppointmenttime() {
		return appointmenttime;
	}
	@Column(name = "state")
	public int getState() {
		return state;
	}
	@Column(name = "deliverytime")
	public Date getDeliverytime() {
		return deliverytime;
	}
	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}
	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public void setStationid(int stationid) {
		this.stationid = stationid;
	}
	public void setAppointmenttime(Date appointmenttime) {
		this.appointmenttime = appointmenttime;
	}
	public void setState(int state) {
		this.state = state;
	}
	public void setDeliverytime(Date deliverytime) {
		this.deliverytime = deliverytime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
}
