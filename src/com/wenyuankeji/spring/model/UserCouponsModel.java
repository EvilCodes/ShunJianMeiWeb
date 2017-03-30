package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_coupons")
public class UserCouponsModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int userid;
	private int couponid;
	private int orderid;
	private String mobile;
	private Date createtime;
	private String source;
	
	@OneToOne
	@JoinColumn(name="couponid",insertable = false,updatable = false)
	private CouponsModel coupons;
	
	public int getId() {
		return id;
	}
	@Column(name = "userid")
	public int getUserid() {
		return userid;
	}
	@Column(name = "couponid")
	public int getCouponid() {
		return couponid;
	}
	@Column(name = "orderid")
	public int getOrderid() {
		return orderid;
	}
	@Column(name = "mobile")
	public String getMobile() {
		return mobile;
	}
	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}
	@Column(name = "source")
	public String getSource() {
		return source;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public void setCouponid(int couponid) {
		this.couponid = couponid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public CouponsModel getCoupons() {
		return coupons;
	}
	public void setCoupons(CouponsModel coupons) {
		this.coupons = coupons;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	
}
