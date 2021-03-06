package com.licence.emaillicence.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "licence")
public class EmailEntity {

	@Id
    @GeneratedValue
	private long id;
    @Column(name="product_key")
	private String key;
    @Column(name="product_name")
   	private String productName;
    @Column(name="Start_date")
	private Date startdate;
    @Column(name="End_date")
	private Date enddate;
    @Column(name="licene_expirery")
	private String liceneExpirery;
    @Column(name="mac_id")
	private String macId;

	public EmailEntity() {
		super();
	}

	public EmailEntity(long id, String key, String productName,Date startdate, Date lastdate,String liceneExpirery,String macId) {
		super();
		this.id = id;
		this.key = key;
		this.productName =productName;
		this.startdate = startdate;
		this.enddate = lastdate;
		this.liceneExpirery= liceneExpirery;
		this.macId= macId;
		
	}
	
	public EmailEntity( String key, String productName,Date startdate, Date lastdate,String liceneExpirery,String macId) {
		 
		this.key = key;
		this.productName =productName;
		this.startdate = startdate;
		this.enddate = lastdate;
		this.liceneExpirery= liceneExpirery;
		this.macId= macId;
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

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getLastdate() {
		return enddate;
	}

	public void setLastdate(Date lastdate) {
		this.enddate = lastdate;
	}
	public String getLiceneExpirery() {
		return liceneExpirery;
	}
	
	public void setLiceneExpirery(String liceneExpirery){
		this.liceneExpirery=liceneExpirery;
	}

	public String getMacId() {
		return macId;
	}

	public void setMacId(String macId) {
		this.macId = macId;
	}
	

}
