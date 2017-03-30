package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassNameUserMyhairPacked
 * @Description
 * @date 2016-01-11 13:19:15
 * @author lnn
 */
@Entity
@Table(name = "user_myhair_packed")
public class UserMyhairPackedModel {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private Integer id; // 美发师套餐id
	private Integer userid; // 美发师id
	private String nickname; //美发师昵称    
	private Integer hairpackedid; // 发型套餐id
	private Integer usednum; // 使用人数
	private Integer state; // 状态 0不可用 1可用
	private Date createtime; // 创建时间
	private String remark; // 备注

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "userid")
	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	@Column(name = "nickname")
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@Column(name = "hairpackedid")
	public Integer getHairpackedid() {
		return hairpackedid;
	}

	public void setHairpackedid(Integer hairpackedid) {
		this.hairpackedid = hairpackedid;
	}
	@Column(name = "usednum")
	public Integer getUsednum() {
		return usednum;
	}

	public void setUsednum(Integer usednum) {
		this.usednum = usednum;
	}
	@Column(name = "state")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	@Column(name = "hairpackedid")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
