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
    @Column(name="Start_date")
	private Date startdate;
    @Column(name="End_date")
	private Date enddate;
    
    @Column(name="licene_expirery")
	private String liceneExpirery;

	public EmailEntity() {
		super();
	}

	public EmailEntity(long id, String key, Date startdate, Date lastdate,String liceneExpirery) {
		super();
		this.id = id;
		this.key = key;
		this.startdate = startdate;
		this.enddate = lastdate;
		this.liceneExpirery= liceneExpirery;
		
	}
	
	public EmailEntity( String key, Date startdate, Date lastdate,String liceneExpirery) {
		 
		this.key = key;
		this.startdate = startdate;
		this.enddate = lastdate;
		this.liceneExpirery= liceneExpirery;
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
	

}
