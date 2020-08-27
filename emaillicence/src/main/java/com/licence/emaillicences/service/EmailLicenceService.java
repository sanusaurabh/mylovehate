package com.licence.emaillicences.service;

import java.util.List;

import com.licence.emaillicence.model.bean.EmailBean;

public interface EmailLicenceService {
	
	public abstract String emailExtractoractivation(String key, String toolName);
	
	public abstract List<EmailBean>  getAllActivation();
	
	public abstract String emailvalidatorLicenceaAccount(String key, String toolName);

	public abstract String resetlicenceactivateAccount(String key);

	public abstract String getresetlock(String key);

	public abstract String getsetlock(String key);

	public abstract String emailvalidatorStatusCheck(String key);

	public abstract String getlockstatus(String key);

	public abstract String emailextractorExportStatusCheck(String key, String string);

	public abstract String emailSenderActivation(String key, String string);

	public abstract String emailsenderStatusCheck(String key, String toolName);

	public abstract String semrushlicenceactivate(String key, String string);

	public abstract String semrushstatusCheck(String key, String string);
	
	public abstract String gSuitelicenceactivate(String key, String string);

	public abstract String gSuitetatusCheck(String key, String string);

	public abstract String getnoofallowedCheck(String key);
	

}
