package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "material_deliveryrecord_detail")
public class MaterialDeliveryrecordDetailModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int recordid;
	private int materialid;
	private int quantity;
	private Date createtime;
	
	public int getId() {
		return id;
	}
	@Column(name = "recordid")
	public int getRecordid() {
		return recordid;
	}
	@Column(name = "materialid")
	public int getMaterialid() {
		return materialid;
	}
	@Column(name = "quantity")
	public int getQuantity() {
		return quantity;
	}
	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}
	public void setMaterialid(int materialid) {
		this.materialid = materialid;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	
}
