package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_wallet")
public class UserWalletModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int walletid;
	private int ownerid;
	private float balance;
	private int ownertype;
	private Date createtime;
	private Date updatetime;
	@Column(name = "walletid")
	public int getWalletid() {
		return walletid;
	}
	@Column(name = "ownerid")
	public int getOwnerid() {
		return ownerid;
	}
	@Column(name = "balance")
	public float getBalance() {
		return balance;
	}
	@Column(name = "ownertype")
	public int getOwnertype() {
		return ownertype;
	}
	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}
	@Column(name = "updatetime")
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setWalletid(int walletid) {
		this.walletid = walletid;
	}
	public void setOwnerid(int ownerid) {
		this.ownerid = ownerid;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public void setOwnertype(int ownertype) {
		this.ownertype = ownertype;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	
}
