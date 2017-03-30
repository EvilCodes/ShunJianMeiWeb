package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_workplace")
public class UserWorkplaceModel {
	@Id
	private int id;
	private int wdid;
	private int storeid;
	private Date createtime;
	@OneToOne
	@JoinColumn(name = "wdid", insertable = false, updatable = false)
	private UserWorkdateModel userWorkdate;

	@ManyToOne
	@JoinColumn(name = "storeid", insertable = false, updatable = false)
	private StoreinfoModel storeinfo;
	
	
	@Column(name = "id")
	public int getId() {
		return id;
	}

	@Column(name = "wdid")
	public int getWdid() {
		return wdid;
	}

	@Column(name = "storeid")
	public int getStoreid() {
		return storeid;
	}

	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setWdid(int wdid) {
		this.wdid = wdid;
	}

	public void setStoreid(int storeid) {
		this.storeid = storeid;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public UserWorkdateModel getUserWorkdate() {
		return userWorkdate;
	}

	public void setUserWorkdate(UserWorkdateModel userWorkdate) {
		this.userWorkdate = userWorkdate;
	}

	public StoreinfoModel getStoreinfo() {
		return storeinfo;
	}

	public void setStoreinfo(StoreinfoModel storeinfo) {
		this.storeinfo = storeinfo;
	}

}
