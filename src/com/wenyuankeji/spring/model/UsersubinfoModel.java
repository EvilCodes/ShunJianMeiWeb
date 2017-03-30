package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "usersubinfo")
public class UsersubinfoModel {
    
	@Id
	private int userid;
	private int levelid;
	private String intro;
	private int checkstate;
	private Date checktime;
	private float score;
	private int ordernum;
	private String hairstyle;
	private String hobbies;
	private int starid;
	private String email;
	private String household;
	private String truename;
	private String idcard;
	private String address;
	private String contact;
	private String contactmobile;
	private String relationship;
	private Date createtime;
	private int workinglife;
	private String allocation;
	private String workhistory;
	// add by jiazhaohui
	// 增加了美发师快捷设置的信息
	private String workday;	// 美发师每周工作的时间
	private String dayworkhour;	// 美发师每天工作的小时数
	private String storeid;	// 美发师常驻的工作店
	private String storename;	// 美发师常驻的工作店店名
	private String storeaddress;	// 美发店的地址
	private String nationality;	// 美发师国籍
	private String language;	// 美发师使用的语言
	private int sort;//排序
	private String flagpng;		// 国家旗帜
	@Column
	private int istype;
	
	@Transient
	private String nickname;
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public int getIstype() {

		return istype;
	}
	public void setIstype(int istype) {
		this.istype = istype;
	}
	
	
	@OneToOne
	@JoinColumn(name="levelid",insertable=false,updatable=false)
	private UserProfessionLevelModel userProfessionLevel;
	
	@OneToOne
	@JoinColumn(name="starid",insertable=false,updatable=false)
	private BaseStarinfoModel baseStarinfoModel;
	
	@OneToOne
	@JoinColumn(name="userid",insertable=false,updatable=false)
	private UserinfoModel userinfoModel;
	
	
	
	public int getUserid() {
		return userid;
	}
	@Column(name = "sort")
	public int getSort() {
		return sort;
	}
	@Column(name = "levelid")
	public int getLevelid() {
		return levelid;
	}
	@Column(name = "intro")
	public String getIntro() {
		return intro;
	}
	@Column(name = "checktime")
	public Date getChecktime() {
		return checktime;
	}
	@Column(name = "score")
	public float getScore() {
		return score;
	}
	@Column(name = "ordernum")
	public int getOrdernum() {
		return ordernum;
	}
	@Column(name = "hairstyle")
	public String getHairstyle() {
		return hairstyle;
	}
	@Column(name = "hobbies")
	public String getHobbies() {
		return hobbies;
	}
	@Column(name = "starid")
	public int getStarid() {
		return starid;
	}
	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	@Column(name = "household")
	public String getHousehold() {
		return household;
	}
	@Column(name = "truename")
	public String getTruename() {
		return truename;
	}
	@Column(name = "idcard")
	public String getIdcard() {
		return idcard;
	}
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	@Column(name = "contact")
	public String getContact() {
		return contact;
	}
	@Column(name = "contactmobile")
	public String getContactmobile() {
		return contactmobile;
	}
	@Column(name = "relationship")
	public String getRelationship() {
		return relationship;
	}
	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}
	@Column(name = "flagpng")
	public String getFlagpng() {
		return flagpng;
	}
	
	public void setFlagpng(String flagpng)
	{
		this.flagpng = flagpng;
	}
	
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public void setLevelid(int levelid) {
		this.levelid = levelid;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public void setChecktime(Date checktime) {
		this.checktime = checktime;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}
	public void setHairstyle(String hairstyle) {
		this.hairstyle = hairstyle;
	}
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}
	public void setStarid(int starid) {
		this.starid = starid;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setHousehold(String household) {
		this.household = household;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public void setContactmobile(String contactmobile) {
		this.contactmobile = contactmobile;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public UserProfessionLevelModel getUserProfessionLevel() {
		return userProfessionLevel;
	}
	public void setUserProfessionLevel(UserProfessionLevelModel userProfessionLevel) {
		this.userProfessionLevel = userProfessionLevel;
	}
	public BaseStarinfoModel getBaseStarinfoModel() {
		return baseStarinfoModel;
	}
	public void setBaseStarinfoModel(BaseStarinfoModel baseStarinfoModel) {
		this.baseStarinfoModel = baseStarinfoModel;
	}
	public UserinfoModel getUserinfoModel() {
		return userinfoModel;
	}
	public void setUserinfoModel(UserinfoModel userinfoModel) {
		this.userinfoModel = userinfoModel;
	}
	@Column(name = "checkstate")
	public int getCheckstate() {
		return checkstate;
	}
	public void setCheckstate(int checkstate) {
		this.checkstate = checkstate;
	}
	
	@Column(name = "workinglife")
	public int getWorkinglife() {
		return workinglife;
	}
	public void setWorkinglife(int workinglife) {
		this.workinglife = workinglife;
	}
	
	@Column(name = "allocation")
	public String getAllocation() {
		return allocation;
	}
	public void setAllocation(String allocation) {
		this.allocation = allocation;
	}
	
	@Column(name = "workhistory")
	public String getWorkhistory() {
		return workhistory;
	}
	public void setWorkhistory(String workhistory) {
		this.workhistory = workhistory;
	}
	
	// add by jiazhaohui
	@Column(name = "workday")
	public String getWorkday() {
		return workday;
	}
	public void setWorkday(String workday) {
		this.workday = workday;
	}
	
	@Column(name = "dayworkhour")
	public String getDayworkhour() {
		return dayworkhour;
	}
	public void setDayworkhour(String dayworkhour) {
		this.dayworkhour = dayworkhour;
	}

	@Column(name = "storeid")
	public String getStoreid() {
		return storeid;
	}
	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}
	
	@Column(name = "storename")
	public String getStorename() {
		return storename;
	}
	public void setStorename(String storename) {
		this.storename = storename;
	}
	
	@Column(name = "storeaddress")
	public String getStoreaddress() {
		return storeaddress;
	}
	public void setStoreaddress(String storeaddress) {
		this.storeaddress = storeaddress;
	}
	
	@Column(name = "nationality")
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	@Column(name = "language")
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
}
