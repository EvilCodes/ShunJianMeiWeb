package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wenyuankeji.spring.util.ShunJianMeiUtil;

@Entity
@Table(name = "orderinfo")
public class OrderinfoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderid;//订单ID
	private int customerid;//顾客ID
	private int userid;//美发师ID
	private int storeid;//店铺ID
	private int styleid;//发型项目ID
	private int hairstyleid;//发型ID
	private int parentid;//父级ID
	private int paytype;//支付方式
	private int paystate;//订单状态
	private Double total;//总金额
	private Double couponamount;//优惠券金额
	private Double amount;//实际支付金额
	private Date signtime;//签到时间
	private Date servicebegintime;//服务开始时间
	private Date paytime;//支付时间
	private Date completetime;//完成时间
	private Date createtime;//创建时间
	private String storeinfo_address;//商家地址
	private String storeinfo_name;//商家名称
	private String storeinfo_tel;//商家电话
	private String user_hairstyle_name;//我的发型名称
	private String user_profession_level_name;//我的等级
	private String usersubinfo_contactmobile;//发型师联系电话
	private String usersubinfo_name;//发型师名字
	private String additionalcode;//加单编号
	private String additionalcontent;//加单备注
	private Double additionalamount;//加单价格
	private Integer additionalstate;//加单支付状态
	private Date additionaltime;//加单支付时间
	private int additionalpaytype;//加单支付类型
	private String ordercode;//订单编号
	private Date appointmenttime;//预约时间
	private String stylename;//发型项目名称
	/*2016-01-14 lnn*/
	private int ishairpacked;//订单类型，0（默认）普通订单，1套餐订单
	public int getOrderid() {
		return orderid;
	}
	@Column(name = "customerid")
	public int getCustomerid() {
		return customerid;
	}
	@Column(name = "userid")
	public int getUserid() {
		return userid;
	}
	@Column(name = "storeid")
	public int getStoreid() {
		return storeid;
	}
	@Column(name = "styleid")
	public int getStyleid() {
		return styleid;
	}
	@Column(name = "hairstyleid")
	public int getHairstyleid() {
		return hairstyleid;
	}
	@Column(name = "parentid")
	public int getParentid() {
		return parentid;
	}
	@Column(name = "paytype")
	public int getPaytype() {
		return paytype;
	}
	@Column(name = "paystate")
	public int getPaystate() {
		return paystate;
	}
	@Column(name = "total")
	public double getTotal() {
		return total;
	}
	@Column(name = "couponamount")
	public double getCouponamount() {
		return couponamount;
	}
	@Column(name = "amount")
	public double getAmount() {
		return amount;
	}
	@Column(name = "signtime")
	public Date getSigntime() {
		return signtime;
	}
	@Column(name = "servicebegintime")
	public Date getServicebegintime() {
		return servicebegintime;
	}
	@Column(name = "paytime")
	public Date getPaytime() {
		return paytime;
	}
	@Column(name = "completetime")
	public Date getCompletetime() {
		return completetime;
	}
	@Column(name = "createtime")
	public String getCreatetime() {
		if (createtime != null) {
			return ShunJianMeiUtil.dateConvertString(createtime);
		}
		return "";
	}
	@Column(name = "storeinfo_address")
	public String getStoreinfo_address() {
		if (ShunJianMeiUtil.checkNull(storeinfo_address)) {
			return "";
		}
		return storeinfo_address;
	}
	@Column(name = "storeinfo_name")
	public String getStoreinfo_name() {
		if (ShunJianMeiUtil.checkNull(storeinfo_name)) {
			return "";
		}
		return storeinfo_name;
	}
	@Column(name = "storeinfo_tel")
	public String getStoreinfo_tel() {
		if (ShunJianMeiUtil.checkNull(storeinfo_tel)) {
			return "";
		}
		return storeinfo_tel;
	}
	@Column(name = "user_hairstyle_name")
	public String getUser_hairstyle_name() {
		return user_hairstyle_name;
	}
	@Column(name = "user_profession_level_name")
	public String getUser_profession_level_name() {
		return user_profession_level_name;
	}
	@Column(name = "usersubinfo_contactmobile")
	public String getUsersubinfo_contactmobile() {
		if (ShunJianMeiUtil.checkNull(usersubinfo_contactmobile)) {
			return "";
		}
		return usersubinfo_contactmobile;
	}
	@Column(name = "usersubinfo_name")
	public String getUsersubinfo_name() {
		return usersubinfo_name;
	}
	@Column(name = "additionalcode")
	public String getAdditionalcode() {
		return additionalcode;
	}
	@Column(name = "additionalcontent")
	public String getAdditionalcontent() {
		return additionalcontent;
	}
	@Column(name = "additionalamount")
	public Double getAdditionalamount() {
		return additionalamount;
	}
	@Column(name = "additionalstate")
	public int getAdditionalstate() {
		return additionalstate;
	}
	@Column(name = "additionaltime")
	public Date getAdditionaltime() {
		return additionaltime;
	}
	@Column(name = "ordercode")
	public String getOrdercode() {
		if (ShunJianMeiUtil.checkNull(ordercode)) {
			return "";
		}
		return ordercode;
	}
	@Column(name = "appointmenttime")
	public String getAppointmenttime() {
		if (null != appointmenttime) {
			return ShunJianMeiUtil.dateConvertString(appointmenttime);
		}
		return "";
	}
	@Column(name ="ishairpacked")
	public Integer getIshairpacked() {
		return ishairpacked;
	}

	public void setIshairpacked(Integer ishairpacked) {
		this.ishairpacked = ishairpacked;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public void setStoreid(int storeid) {
		this.storeid = storeid;
	}

	public void setStyleid(int styleid) {
		this.styleid = styleid;
	}

	public void setHairstyleid(int hairstyleid) {
		this.hairstyleid = hairstyleid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	public void setPaytype(int paytype) {
		this.paytype = paytype;
	}

	public void setPaystate(int paystate) {
		this.paystate = paystate;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void setCouponamount(double couponamount) {
		this.couponamount = couponamount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setSigntime(Date signtime) {
		this.signtime = signtime;
	}

	public void setServicebegintime(Date servicebegintime) {
		this.servicebegintime = servicebegintime;
	}

	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}

	public void setCompletetime(Date completetime) {
		this.completetime = completetime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public void setStoreinfo_address(String storeinfo_address) {
		this.storeinfo_address = storeinfo_address;
	}

	public void setStoreinfo_name(String storeinfo_name) {
		this.storeinfo_name = storeinfo_name;
	}

	public void setStoreinfo_tel(String storeinfo_tel) {
		this.storeinfo_tel = storeinfo_tel;
	}

	public void setUser_hairstyle_name(String user_hairstyle_name) {
		this.user_hairstyle_name = user_hairstyle_name;
	}

	public void setUser_profession_level_name(String user_profession_level_name) {
		this.user_profession_level_name = user_profession_level_name;
	}

	public void setUsersubinfo_contactmobile(String usersubinfo_contactmobile) {
		this.usersubinfo_contactmobile = usersubinfo_contactmobile;
	}

	public void setUsersubinfo_name(String usersubinfo_name) {
		this.usersubinfo_name = usersubinfo_name;
	}

	public void setAdditionalcode(String additionalcode) {
		this.additionalcode = additionalcode;
	}

	public void setAdditionalcontent(String additionalcontent) {
		this.additionalcontent = additionalcontent;
	}

	public void setAdditionalamount(Double additionalamount) {
		this.additionalamount = additionalamount;
	}

	public void setAdditionalstate(int additionalstate) {
		this.additionalstate = additionalstate;
	}

	public void setAdditionaltime(Date additionaltime) {
		this.additionaltime = additionaltime;
	}

	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}

	public void setAppointmenttime(Date appointmenttime) {
		this.appointmenttime = appointmenttime;
	}
	@Column(name = "stylename")
	public String getStylename() {
		if (ShunJianMeiUtil.checkNull(stylename)) {
			return "";
		}
		return stylename;
	}

	public void setStylename(String stylename) {
		this.stylename = stylename;
	}
	@Column(name = "additionalpaytype")
	public int getAdditionalpaytype() {
		return additionalpaytype;
	}

	public void setAdditionalpaytype(int additionalpaytype) {
		this.additionalpaytype = additionalpaytype;
	}
	
	

}
