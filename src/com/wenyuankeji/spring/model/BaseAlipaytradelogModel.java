package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "base_alipaytradelog")
public class BaseAlipaytradelogModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int logid;
	private String orderid;
	private String content;
	private Date createtime;
	// add by jiazhaohui
	private int quickpay;	// 订单支付类型：0表示普通订单，1表示闪惠订单

	public int getLogid() {
		return logid;
	}

	public void setLogid(int logid) {
		this.logid = logid;
	}

	@Column(name = "orderid")
	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	@Column(name = "content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
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
