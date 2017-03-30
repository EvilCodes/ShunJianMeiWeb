package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_evaluate_score")
public class UserEvaluateScoreModel {

	@Id
	private int id;
	private int evaid;
	private String evaluate;
	private int score;
	private Date createtime;
	
	public int getId() {
		return id;
	}
	@Column(name = "evaid")
	public int getEvaid() {
		return evaid;
	}
	@Column(name = "evaluate")
	public String getEvaluate() {
		return evaluate;
	}
	@Column(name = "score")
	public int getScore() {
		return score;
	}
	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setEvaid(int evaid) {
		this.evaid = evaid;
	}
	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
}
