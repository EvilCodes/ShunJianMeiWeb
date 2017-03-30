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
@Table(name = "user_tradelog")
public class UserTradelogModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int logid;
	private int walletid;
	private int orderid;
	private float amount;
	private int logtype;
	private String remark;
	private Date createtime;
	// add by jiazhaohui
	private int quickpay;
	
	@OneToOne
	@JoinColumn(name="walletid",insertable=false,updatable=false)
	private UserWalletModel userWalletModel;
	
	@OneToOne
	@JoinColumn(name="orderid",insertable=false,updatable=false)
	private OrderinfoModel orderinfoModel;
	
	@Column(name = "logid")
	public int getLogid() {
		return logid;
	}
	@Column(name = "walletid")
	public int getWalletid() {
		return walletid;
	}
	@Column(name = "orderid")
	public int getOrderid() {
		return orderid;
	}
	@Column(name = "amount")
	public float getAmount() {
		return amount;
	}
	@Column(name = "logtype")
	public int getLogtype() {
		return logtype;
	}
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}
	// add by jiazhaohui
	@Column(name = "quickpay")
	public int getQuickpay() {
		return quickpay;
	}
	
	public void setLogid(int logid) {
		this.logid = logid;
	}
	public void setWalletid(int walletid) {
		this.walletid = walletid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public void setLogtype(int logtype) {
		this.logtype = logtype;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public UserWalletModel getUserWalletModel() {
		return userWalletModel;
	}
	public void setUserWalletModel(UserWalletModel userWalletModel) {
		this.userWalletModel = userWalletModel;
	}
	public OrderinfoModel getOrderinfoModel() {
		return orderinfoModel;
	}
	public void setOrderinfoModel(OrderinfoModel orderinfoModel) {
		this.orderinfoModel = orderinfoModel;
	}
	
	public void setQuickpay(int quickpay)
	{
		this.quickpay = quickpay;
	}
}
