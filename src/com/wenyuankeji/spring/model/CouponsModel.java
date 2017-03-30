package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coupons")
public class CouponsModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int couponid;
	private String couponname;
	private String couponcode;
	private int amount;
	private String usecondition;
	private Date starttime;
	private Date endtime;
	private boolean deleted;
	private String remark;
	private Date createtime;
	
	//追加
	private String servicecode;
	
//	@ManyToOne
//	@JoinColumn(name="couponid",insertable = false,updatable = false)
//	private UserCouponsModel userCoupons;
	
	public int getCouponid() {
		return couponid;
	}
	@Column(name = "couponname")
	public String getCouponname() {
		return couponname;
	}
	@Column(name = "couponcode")
	public String getCouponcode() {
		return couponcode;
	}
	@Column(name = "amount")
	public int getAmount() {
		return amount;
	}
	@Column(name = "usecondition")
	public String getUsecondition() {
		return usecondition;
	}
	@Column(name = "starttime")
	public Date getStarttime() {
		return starttime;
	}
	@Column(name = "endtime")
	public Date getEndtime() {
		return endtime;
	}
	@Column(name = "deleted")
	public boolean isDeleted() {
		return deleted;
	}
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}
	@Column(name = "servicecode")
	public String getServicecode() {
		return servicecode;
	}
	public void setCouponid(int couponid) {
		this.couponid = couponid;
	}
	public void setCouponname(String couponname) {
		this.couponname = couponname;
	}
	public void setCouponcode(String couponcode) {
		this.couponcode = couponcode;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public void setUsecondition(String usecondition) {
		this.usecondition = usecondition;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public void setServicecode(String servicecode) {
		this.servicecode = servicecode;
	}
	
//	public UserCouponsModel getUserCoupons() {
//		return userCoupons;
//	}
//	public void setUserCoupons(UserCouponsModel userCoupons) {
//		this.userCoupons = userCoupons;
//	}
	
}
