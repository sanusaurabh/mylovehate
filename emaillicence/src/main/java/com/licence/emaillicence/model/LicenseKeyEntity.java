package com.licence.emaillicence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "licencekey")
public class LicenseKeyEntity {
	@Id
    @GeneratedValue
	private Long id;
	 @Column(name="product_key")
	private String key;
	@Column(name="product_name")
	private String toolName;
	@Column(name="valid_for_day")
	private String validForNoOfDay;
	@Column(name="key_isUesd")
	private String isUsed;
	
	public LicenseKeyEntity() {
		 
	}
	
	
	public LicenseKeyEntity(Long id, String key, String toolName, String validForNoOfDay, String isUsed) {
		super();
		this.id = id;
		this.key = key;
		this.toolName = toolName;
		this.validForNoOfDay = validForNoOfDay;
		this.isUsed = isUsed;
	}
	
	public LicenseKeyEntity(String key, String toolName, String validForNoOfDay, String isUsed) {
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
