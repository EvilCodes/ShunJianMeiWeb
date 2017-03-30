package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "base_wechattradelog")
public class BaseWechatTradelogModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int logid;
	private String orderid;
	private String additionalcode;
	private String payorderid;
	private Date paytime;
	private int state;
	private String remark;
	private Date createtime;
	// add by jiazhaohui
	private int quickpay;	// 订单支付类型：0表示普通订单，1表示闪惠订单
	
	@Column(name = "logid")
	public int getLogid() {
		return logid;
	}
	@Column(name = "orderid")
	public String getOrderid() {
		return orderid;
	}
	@Column(name = "additionalcode")
	public String getAdditionalcode() {
		return additionalcode;
	}
	@Column(name = "payorderid")
	public String getPayorderid() {
		return payorderid;
	}
	@Column(name = "paytime")
	public Date getPaytime() {
		return paytime;
	}
	@Column(name = "state")
	public int getState() {
		return state;
	}
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}
	public void setLogid(int logid) {
		this.logid = logid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public void setAdditionalcode(String additionalcode) {
		this.additionalcode = additionalcode;
	}
	public void setPayorderid(String payorderid) {
		this.payorderid = payorderid;
	}
	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}
	public void setState(int state) {
		this.state = state;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@Column(name = "quickpay")
	public int getQuickpay() {
		return quickpay;
	}

	public void setQuickpay(int quickpay) {
		this.quickpay = quickpay;
	}
}
