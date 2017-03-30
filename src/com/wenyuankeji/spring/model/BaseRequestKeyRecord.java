package com.wenyuankeji.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "base_requestkey_record")
public class BaseRequestKeyRecord {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String keystring;
	
	@Column(name = "id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "keystring")
	public String getKeystring() {
		return keystring;
	}
	public void setKeystring(String keystring) {
		this.keystring = keystring;
	}
	
}
