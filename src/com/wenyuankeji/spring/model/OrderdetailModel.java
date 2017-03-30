package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orderdetail")
public class OrderdetailModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int orderid;
	private String servicecode;
	private double serviceprice;
	private int price;
	private int times;
	private int materialid;
	private String materialname;
	private double materialidprice;
	private Date createtime;
	public int getId() {
		return id;
	}
	@Column(name = "orderid")
	public int getOrderid() {
		return orderid;
	}
	@Column(name = "servicecode")
	public String getServicecode() {
		return servicecode;
	}
	@Column(name = "price")
	public int getPrice() {
		return price;
	}
	@Column(name = "times")
	public int getTimes() {
		return times;
	}
	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public void setServicecode(String servicecode) {
		this.servicecode = servicecode;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@Column(name = "serviceprice")
	public double getServiceprice() {
		return serviceprice;
	}
	public void setServiceprice(double serviceprice) {
		this.serviceprice = serviceprice;
	}
	@Column(name = "materialid")
	public int getMaterialid() {
		return materialid;
	}
	@Column(name = "materialname")
	public String getMaterialname() {
		return materialname;
	}
	@Column(name = "materialidprice")
	public double getMaterialidprice() {
		return materialidprice;
	}
	public void setMaterialid(int materialid) {
		this.materialid = materialid;
	}
	public void setMaterialname(String materialname) {
		this.materialname = materialname;
	}
	public void setMaterialidprice(double materialidprice) {
		this.materialidprice = materialidprice;
	}
	
}
