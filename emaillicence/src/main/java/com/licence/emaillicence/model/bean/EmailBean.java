package com.licence.emaillicence.model.bean;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class EmailBean {

	@Id
    @GeneratedValue
	private long id;
    @Column(name="product_key")
	private String key;
    @Column(name="Start_date")
	private Date startdate;
    @Column(name="End_date")
	private Date enddate;

	public EmailBean() {
		super();
	}

	public EmailBean(long id, String key, Date startdate, Date lastdate) {
		super();
		this.id = id;
		this.key = key;
		this.startdate = startdate;
		this.enddate = lastdate;
	}
	
	public EmailBean( String key, Date startdate, Date lastdate) {
		 
		this.key = key;
		this.startdate = startdate;
		this.enddate = lastdate;
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



}
