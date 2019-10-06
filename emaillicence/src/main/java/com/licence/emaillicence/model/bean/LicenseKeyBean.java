package com.licence.emaillicence.model.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


public class LicenseKeyBean {
	
	private Long id;
	private String key;
	private String toolName;
	private String validForNoOfDay;
	private String isUsed;
	public LicenseKeyBean() {
		 
	}
	
	
	public LicenseKeyBean(Long id, String key, String toolName, String validForNoOfDay, String isUsed) {
		super();
		this.id = id;
		this.key = key;
		this.toolName = toolName;
		this.validForNoOfDay = validForNoOfDay;
		this.isUsed = isUsed;
	}
	
	public LicenseKeyBean(String key, String toolName, String validForNoOfDay, String isUsed) {
		this.key = key;
		this.toolName = toolName;
		this.validForNoOfDay = validForNoOfDay;
		this.isUsed = isUsed;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getToolName() {
		return toolName;
	}
	public void setToolName(String toolName) {
		this.toolName = toolName;
	}
	public String getValidForNoOfDay() {
		return validForNoOfDay;
	}
	public void setValidForNoOfDay(String validForNoOfDay) {
		this.validForNoOfDay = validForNoOfDay;
	}
	public String getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}
	
	
	
	
	

}
