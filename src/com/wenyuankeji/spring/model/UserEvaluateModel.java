package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_evaluate")
public class UserEvaluateModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int evaid;
	private String item;
	private int userid;
	private int type;
	private float score;
	private String content;
	private int orderid;
	private boolean deleted;
	private Date createtime;
	
	public int getEvaid() {
		return evaid;
	}
	@Column(name = "item")
	public String getItem() {
		return item;
	}
	@Column(name = "userid")
	public int getUserid() {
		return userid;
	}
	@Column(name = "type")
	public int getType() {
		return type;
	}
	@Column(name = "score")
	public float getScore() {
		return score;
	}
	@Column(name = "content")
	public String getContent() {
		return content;
	}
	@Column(name = "orderid")
	public int getOrderid() {
		return orderid;
	}
	@Column(name = "deleted")
	public boolean isDeleted() {
		return deleted;
	}
	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}
	public void setEvaid(int evaid) {
		this.evaid = evaid;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	
}
