package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_editwork")
public class UserEditWorkModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int orderid;
	private int starthour;
	private int lasthour;
	private int endhour;
	private String name;
	private String mobile;
	private String content;
	private Date createtime;
	
	public int getOrderid() {
		return orderid;
	}
	
	@Column(name = "starthour")
	public int getStarthour()
	{
		return starthour; 
	}
	
	public void setStarthour(int starthour)
	{
		this.starthour = starthour;
	}
	
	@Column(name = "lasthour")
	public int getLasthour()
	{
		return lasthour; 
	}
	
	public void setLasthour(int lasthour)
	{
		this.lasthour = lasthour;
	}
	
	@Column(name = "endhour")
	public int getEndhour()
	{
		return endhour; 
	}
	
	public void setEndhour(int endhour)
	{
		this.endhour = endhour;
	}
	
	@Column(name = "name")
	public String getName()
	{
		return name; 
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	@Column(name = "mobile")
	public String getMobile()
	{
		return mobile; 
	}
	
	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}
	
	@Column(name = "content")
	public String getContent()
	{
		return content; 
	}
	
	public void setContent(String content)
	{
		this.content = content;
	}
	
	@Column(name = "createtime")
	public Date getCreatetime() 
	{
		return createtime;
	}
	
	public void setCreatetime(Date createtime) 
	{
		this.createtime = createtime;
	}
}
