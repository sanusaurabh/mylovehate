package com.licence.emaillicence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "licencelock")
public class LicenceLock {
	
	@Id
    @GeneratedValue
	private long id;
    @Column(name="product_key")
	private String key;
    @Column(name="lockvalue")
   	private String lockvalue;
    
    public LicenceLock() {
		 
		 
	}
    
    
	public LicenceLock(long id, String key, String lockvalue) {
		 
		this.id = id;
		this.key = key;
		this.lockvalue = lockvalue;
	}
	
	public LicenceLock(String key, String lockvalue) {
		this.key = key;
		this.lockvalue = lockvalue;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getLockvalue() {
		return lockvalue;
	}

	public void setLockvalue(String lockvalue) {
		this.lockvalue = lockvalue;
	}
    
    

}
