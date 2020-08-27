package com.licence.emaillicence.model;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class NumberOfAllowed {
	@Id
    @GeneratedValue
	private Long id;
    @Column(name="product_key")
	private String key;
    @Column(name="product_name")
	private String toolName;
    @Column(name="numnerofallowed")
	private int noOfAllowed;
    @Column(name="description")
   	private String description;
    
	public NumberOfAllowed() {
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
	public int getNoOfAllowed() {
		return noOfAllowed;
	}
	public void setNoOfAllowed(int noOfAllowed) {
		this.noOfAllowed = noOfAllowed;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	

}
