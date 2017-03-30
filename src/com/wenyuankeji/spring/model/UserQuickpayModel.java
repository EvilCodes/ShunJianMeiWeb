package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_quickpay")
public class UserQuickpayModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int paymentid;
	private String ordercode;
	private int storeid;
	private int userid;
	private float payamount;
	private float customreduce;
	private float storereduce;
	private int paytype;
	private Date createtime;
	private int paystate;
	private String mastername;
	


	public int getOrderid() {
		return paymentid;
	}
	
	@Column(name = "ordercode")
	public String getOrdercode()
	{
		return ordercode; 
	}
	
	public void setOrdercode(String ordercode)
	{
		this.ordercode = ordercode;
	}
	
	@Column(name = "storeid")
	public int getStoreid()
	{
		return storeid;
	}
	
	public void setStoreid(int storeid)
	{
		this.storeid = storeid;
	}
	
	@Column(name = "payamount")
	public float getPayamount()
	{
		return payamount;
	}
	
	public void setPayamount(float payamount)
	{
		this.payamount = payamount;
	}
	
	@Column(name = "customreduce")
	public float getCustomreduce()
	{
		return customreduce;
	}
	
	public void setCustomreduce(float customreduce)
	{
		this.customreduce = customreduce;
	}
	
	@Column(name = "storereduce")
	public float getStorereduce()
	{
		return storereduce;
	}
	
	public void setStorereduce(float storereduce)
	{
		this.storereduce = storereduce;
	}
	
	@Column(name = "paytype")
	public float getPaytype()
	{
		return paytype;
	}
	
	public void setPaytype(int paytype)
	{
		this.paytype = paytype;
	}
	
	@Column(name = "createtime")
	public Date getCreatetime() 
	{
		return createtime;
	}
	
	public void setCreatetime(Date createtime) 
	{
		this.createtime = createtime;
	}
	
	@Column(name = "paystate")
	public int getPaystate()
	{
		return paystate;
	}
	
	public void setPaystate(int paystate)
	{
		this.paystate = paystate;
	}
	@Column(name = "userid")
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
	@Column(name = "mastername")
	public String getMastername() {
		return mastername;
	}

	public void setMastername(String mastername) {
		this.mastername = mastername;
	}
	
}
