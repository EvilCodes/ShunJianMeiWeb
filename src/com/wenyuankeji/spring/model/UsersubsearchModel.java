package com.wenyuankeji.spring.model;


public class UsersubsearchModel {
	
	private int userid;
	private String bindphone;
	private String nickname;
	private int score;
	private int levelid;
	private String levelname;
	private int checkstate;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getBindphone() {
		return bindphone;
	}
	public void setBindphone(String bindphone) {
		this.bindphone = bindphone;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getLevelid() {
		return levelid;
	}
	public void setLevelid(int levelid) {
		this.levelid = levelid;
	}
	public String getLevelname() {
		return levelname;
	}
	public void setLevelname(String levelname) {
		this.levelname = levelname;
	}
	public int getCheckstate() {
		return checkstate;
	}
	public void setCheckstate(int checkstate) {
		this.checkstate = checkstate;
	}
	
}
