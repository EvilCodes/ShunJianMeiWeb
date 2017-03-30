package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_cashrecord")
public class UserCashrecordModel {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int recordid;//记录
	private int userid;//用户
	private String username;//名字
	private String bank;//开户行
	private String cardnumber;//卡号
	private double amount;//金额
	private int state;//状态
	private String remark;//备注
	private Date createtime;//时间
	
	public int getRecordid() {
		return recordid;
	}
	@Column(name = "userid")
	public int getUserid() {
		return userid;
	}
	@Column(name = "username")
	public String getUsername() {
		return username;
	}
	@Column(name = "bank")
	public String getBank() {
		return bank;
	}
	@Column(name = "cardnumber")
	public String getCardnumber() {
		return cardnumber;
	}
	@Column(name = "amount")
	public double getAmount() {
		return amount;
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
	
	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}
	public void setAmount(double amount) {
		this.amount = amount;
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
	
	
	
}
