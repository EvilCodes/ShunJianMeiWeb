package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "storeinfo")
public class StoreinfoModel {

	@Id
	private int storeid;
	private String name;
	private int provinceid;
	private int cityid;
	private int districtid;
	private String address;
	private String tel;
	private int empiricalmode;
	private String businesshours;
	private int areaid;
	private String intro;
	private int orderquantity;
	private int carnumber;
	private String storemanagername;
	private String storemanagermobile;
	private String bossname;
	private String bossmobile;
	private String longitude;
	private String latitude;
	private float score;
	private int state;
	private Date createtime;
	private String allocation;
	private String username;
	private String password;
	private String ownername;
	private String bank;
	private String cardnumber;
	private int sort;//排序
	//新追加，不做数据库关联，按照距离排序视同
	@Transient
	private long distance;
	
	// add by jiazhaohui
	private int quickpay;	// 是否支持闪惠的标记为
	private String masterlist;	// 美发师列表：用于闪惠
	
	
	private Short istype;

	public Short getIstype() {
		return istype;
	}
	public void setIstype(Short istype) {
		this.istype = istype;
	}
	public int getStoreid() {
		return storeid;
	}
	@Column(name = "sort")
	public int getSort() {
		return sort;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}

	@Column(name = "provinceid")
	public int getProvinceid() {
		return provinceid;
	}

	@Column(name = "cityid")
	public int getCityid() {
		return cityid;
	}

	@Column(name = "districtid")
	public int getDistrictid() {
		return districtid;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	@Column(name = "tel")
	public String getTel() {
		return tel;
	}

	@Column(name = "empiricalmode")
	public int getEmpiricalmode() {
		return empiricalmode;
	}

	@Column(name = "businesshours")
	public String getBusinesshours() {
		return businesshours;
	}

	@Column(name = "areaid")
	public int getAreaid() {
		return areaid;
	}

	@Column(name = "intro")
	public String getIntro() {
		return intro;
	}

	@Column(name = "orderquantity")
	public int getOrderquantity() {
		return orderquantity;
	}

	@Column(name = "carnumber")
	public int getCarnumber() {
		return carnumber;
	}

	@Column(name = "storemanagername")
	public String getStoremanagername() {
		return storemanagername;
	}

	@Column(name = "storemanagermobile")
	public String getStoremanagermobile() {
		return storemanagermobile;
	}

	@Column(name = "bossname")
	public String getBossname() {
		return bossname;
	}

	@Column(name = "bossmobile")
	public String getBossmobile() {
		return bossmobile;
	}

	@Column(name = "longitude")
	public String getLongitude() {
		return longitude;
	}

	@Column(name = "latitude")
	public String getLatitude() {
		return latitude;
	}

	@Column(name = "score")
	public float getScore() {
		return score;
	}

	@Column(name = "state")
	public int getState() {
		return state;
	}

	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}

	@Column(name = "allocation")
	public String getAllocation() {
		return allocation;
	}

	@Column(name = "username")
	public String getUsername() {
		return username;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	@Column(name = "ownername")
	public String getOwnername() {
		return ownername;
	}

	@Column(name = "bank")
	public String getBank() {
		return bank;
	}

	@Column(name = "cardnumber")
	public String getCardnumber() {
		return cardnumber;
	}
	
	@Column(name = "quickpay")
	public int getQuickpay() {
		return quickpay;
	}

	@Column(name = "masterlist")
	public String getMasterlist() {
		return masterlist;
	}

	public void setStoreid(int storeid) {
		this.storeid = storeid;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void setProvinceid(int provinceid) {
		this.provinceid = provinceid;
	}

	public void setCityid(int cityid) {
		this.cityid = cityid;
	}

	public void setDistrictid(int districtid) {
		this.districtid = districtid;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setEmpiricalmode(int empiricalmode) {
		this.empiricalmode = empiricalmode;
	}

	public void setBusinesshours(String businesshours) {
		this.businesshours = businesshours;
	}

	public void setAreaid(int areaid) {
		this.areaid = areaid;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public void setOrderquantity(int orderquantity) {
		this.orderquantity = orderquantity;
	}

	public void setCarnumber(int carnumber) {
		this.carnumber = carnumber;
	}

	public void setStoremanagername(String storemanagername) {
		this.storemanagername = storemanagername;
	}

	public void setStoremanagermobile(String storemanagermobile) {
		this.storemanagermobile = storemanagermobile;
	}

	public void setBossname(String bossname) {
		this.bossname = bossname;
	}

	public void setBossmobile(String bossmobile) {
		this.bossmobile = bossmobile;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public void setAllocation(String allocation) {
		this.allocation = allocation;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}

	public long getDistance() {
		return distance;
	}

	public void setDistance(long distance) {
		this.distance = distance;
	}
	
	public void setQuickpay(int quickpay)
	{
		this.quickpay = quickpay;
	}
	
	public void setMasterlist(String masterlist)
	{
		this.masterlist = masterlist;
	}

}
