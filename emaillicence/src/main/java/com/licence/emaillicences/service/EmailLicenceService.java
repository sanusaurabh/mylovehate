package com.licence.emaillicences.service;

import java.util.List;

import com.licence.emaillicence.model.bean.EmailBean;

public interface EmailLicenceService {
	
	public abstract void activation(String key);
	
	public abstract List<EmailBean>  getAllActivation();
	
	public  abstract void encription(String key);
	

}
