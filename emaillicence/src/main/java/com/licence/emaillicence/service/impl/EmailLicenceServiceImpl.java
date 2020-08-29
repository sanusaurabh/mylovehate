package com.licence.emaillicence.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.licence.emaillicence.dao.EmailLicenceRepository;
import com.licence.emaillicence.dao.LicenceKeyRepository;
import com.licence.emaillicence.dao.LicenceLockRepository;
import com.licence.emaillicence.dao.NumberOfAllowedRepository;
import com.licence.emaillicence.model.EmailEntity;
import com.licence.emaillicence.model.LicenceLock;
import com.licence.emaillicence.model.LicenseKeyEntity;
import com.licence.emaillicence.model.NumberOfAllowed;
import com.licence.emaillicence.model.bean.EmailBean;
import com.licence.emaillicence.util.AES;
import com.licence.emaillicences.service.EmailLicenceService;
import com.licence.emaillicences.service.LicenceKeyService;

@Service
public class EmailLicenceServiceImpl implements EmailLicenceService {

	@Autowired
	private EmailLicenceRepository emailLicenceDao;
	@Autowired
	private LicenceLockRepository licenceLockRepository;
	
	@Autowired
	private LicenceKeyService licenceKeyService;
	
	@Autowired
	private LicenceKeyRepository licenceKeyRepository;
	
	@Autowired
	private NumberOfAllowedRepository numberOfAllowedRepository;
	
	

	@Autowired
	private Environment env;

	public EmailLicenceRepository getEmailLicenceDao() {
		return emailLicenceDao;
	}

	public void setEmailLicenceDao(EmailLicenceRepository emailLicenceDao) {
		this.emailLicenceDao = emailLicenceDao;
	}

	public LicenceLockRepository getLicenceLockRepository() {
		return licenceLockRepository;
	}

	public void setLicenceLockRepository(LicenceLockRepository licenceLockRepository) {
		this.licenceLockRepository = licenceLockRepository;
	}

	@Override
	public String emailExtractoractivation(String key, String emailExtractortoolName) {
		LocalDate localDatestartdate = LocalDate.now();
		Date startdate = Date.valueOf(localDatestartdate);
		LocalDate localDatelastdate = null;
		String[] keyarray = key.split("#");
		key = keyarray[0];
		String macid = keyarray[1];
		String secret = env.getProperty("aes.secretKey");
		String enkey_365_7 = env.getProperty("licence.product.key_365_7");
		List<String> decpkey_365_7 = licenceKeyService.getOneYearLicencekeys(emailExtractortoolName);//AES.decrypt(enkey_365_7, secret);
		String enkey_30_6 = env.getProperty("licence.product.key_30_6");
		//String decpkey_30_6 = AES.decrypt(enkey_30_6, secret);
		List<String> decpkey_30_6 = licenceKeyService.getThirtyDayLicencekeys(emailExtractortoolName);
		String keyStartDateEndDate ="";
		////#J20G4KL-B43XXAD-N2RSRE0-DSWAWXU
		if (decpkey_365_7.contains(key)) {
			localDatelastdate =localDatestartdate.plus(Period.ofDays(365));
			Date lastdate = Date.valueOf(localDatelastdate);
			keyStartDateEndDate = saveEmailExtractorActivation(key, emailExtractortoolName, localDatestartdate,
					startdate, localDatelastdate, lastdate, secret,macid);
		}
		
		else if (decpkey_30_6.contains(key))
		{
			localDatelastdate =localDatestartdate.plus(Period.ofDays(30));
			Date lastdate = Date.valueOf(localDatelastdate);
			keyStartDateEndDate = saveEmailExtractorActivation(key, emailExtractortoolName, localDatestartdate,
					startdate, localDatelastdate, lastdate, secret,macid);
		}
		else
		{
			return "Invalid Key " + key;
		}
		 
		return keyStartDateEndDate;
	}

	private String saveEmailExtractorActivation(String key, String emailExtractortoolName, LocalDate localDatestartdate,
			Date startdate, LocalDate localDatelastdate, Date lastdate, String secret,String macid) {
		EmailEntity emailEntityvlidation = emailLicenceDao.findByKey(key);
		if (emailEntityvlidation != null && emailEntityvlidation.getKey().equalsIgnoreCase(key)) {
			if(!emailEntityvlidation.getMacId().equalsIgnoreCase(macid)) {
				emailEntityvlidation.setMacId(macid);
				emailLicenceDao.saveAndFlush(emailEntityvlidation);
				String decpkey = AES.decrypt(emailEntityvlidation.getLiceneExpirery(), secret);
				return decpkey+"_"+macid;
			}
			else if(emailEntityvlidation.getMacId().equalsIgnoreCase(macid))
			{
				String decpkey = AES.decrypt(emailEntityvlidation.getLiceneExpirery(), secret);
				return decpkey+"_"+macid;
			}
			else {
				return "Invalid Key " + key;
			}
			
		}
		String keyStartDateEndDate = key + "_" + localDatestartdate.toString() + "_" + localDatelastdate.toString();
		
		String decpkey = AES.encrypt(keyStartDateEndDate, secret);

		EmailEntity emailEntity = new EmailEntity(key, emailExtractortoolName, startdate, lastdate, decpkey,macid);

		emailLicenceDao.save(emailEntity);
		LicenseKeyEntity licenseKeyEntity = licenceKeyRepository.findByKey(key);
		licenseKeyEntity.setIsUsed("yes");
		licenceKeyRepository.saveAndFlush(licenseKeyEntity);
		return keyStartDateEndDate+"_"+macid;
	}
	
	
	@Override
	public String emailextractorExportStatusCheck(String key, String string) {
		
		String[] keys = key.split("_");
		EmailEntity emailEntity =emailLicenceDao.findByKey(keys[0]);
		if(keys[0].equalsIgnoreCase(emailEntity.getKey()) && keys[3].equalsIgnoreCase(emailEntity.getMacId()))
		{
			if(!vlidateExperyDate(emailEntity)) 
			{
				return "Key Expired";
			}
			return "success";
		}
		
		return "fail";
	}

	private boolean vlidateExperyDate(EmailEntity emailEntity) {
		LocalDate startDate = emailEntity.getStartdate().toLocalDate();
		LocalDate endDate = emailEntity.getLastdate().toLocalDate();
		long fixeddays = ChronoUnit.DAYS.between(startDate, endDate);
		
		long usedddays = ChronoUnit.DAYS.between(startDate, LocalDate.now());
		 
		if(usedddays>fixeddays) 
		{
			return false;
		}
		return true;  
		
	}

	@Override
	public List<EmailBean> getAllActivation() {
		List<EmailBean> emList = new ArrayList<>();
		Iterable<EmailEntity> emailEntityList = emailLicenceDao.findAll();

		for (EmailEntity emailEntity : emailEntityList) {
			emList.add(new EmailBean(emailEntity.getId(), emailEntity.getKey(), emailEntity.getStartdate(),
					emailEntity.getLastdate()));
		}

		return emList;
	}

	@Override
	public String emailvalidatorLicenceaAccount(String key, String emailValidationtoolName) {
		LocalDate localDatestartdate = LocalDate.now();
		Date startdate = Date.valueOf(localDatestartdate);
		Date lastdate=null;
		String[] keyarray = key.split("#");
		key = keyarray[0];
		String macid = keyarray[1];
		String keyStartDateEndDate = "";//key + "_" + localDatestartdate.toString() + "_" + localDatelastdate.toString();
		List<String> decpkey_365 = licenceKeyService.getOneYearLicencekeys(emailValidationtoolName);//AES.decrypt(enkey_365_7, secret);
		List<String> decpkey_30 = licenceKeyService.getThirtyDayLicencekeys(emailValidationtoolName);
		List<String> decpTrailkey_7days_6 = licenceKeyService.getSevenDayTrailLicencekeys(emailValidationtoolName);
		if(decpkey_365.contains(key)) 
		{
			LocalDate localDatelastdate = localDatestartdate.plus(Period.ofDays(365));
			  lastdate = Date.valueOf(localDatelastdate);
			keyStartDateEndDate = key + "_" + localDatestartdate.toString() + "_" + localDatelastdate.toString();
		}
		else if(decpkey_30.contains(key))
		{
			LocalDate localDatelastdate = localDatestartdate.plus(Period.ofDays(30));
			  lastdate = Date.valueOf(localDatelastdate);
			keyStartDateEndDate = key + "_" + localDatestartdate.toString() + "_" + localDatelastdate.toString();
		}
		
		else if(decpTrailkey_7days_6.contains(key))
		{
			LocalDate localDatelastdate = localDatestartdate.plus(Period.ofDays(7));
			  lastdate = Date.valueOf(localDatelastdate);
			keyStartDateEndDate = key + "_" + localDatestartdate.toString() + "_" + localDatelastdate.toString();
		}
		
		//String keys = env.getProperty("licence.product.key");
		//if (!keys.contains(key)) {
		else {
			return "Invalid Key " + key;
		}
		EmailEntity emailEntityvlidation = emailLicenceDao.findByKey(key);
		if (emailEntityvlidation != null && emailEntityvlidation.getKey().equalsIgnoreCase(key)) {
			return keyStartDateEndDate+"_"+macid;
		}
		String secret = env.getProperty("aes.secretKey");
		String decpkey = AES.encrypt(keyStartDateEndDate, secret);

		EmailEntity emailEntity = new EmailEntity(key, emailValidationtoolName, startdate, lastdate, decpkey,macid);

		emailLicenceDao.save(emailEntity);
		LicenseKeyEntity licenseKeyEntity = licenceKeyRepository.findByKey(key);
		licenseKeyEntity.setIsUsed("yes");
		licenceKeyRepository.saveAndFlush(licenseKeyEntity);
		return keyStartDateEndDate+"_"+macid;
	}

	@Override
	public String resetlicenceactivateAccount(String key) {

		EmailEntity emailEntityvlidation = emailLicenceDao.findByKey(key);
		if (emailEntityvlidation != null && emailEntityvlidation.getKey().equalsIgnoreCase(key)) {
			String secret = env.getProperty("aes.secretKey");
			String decpkey = AES.decrypt(emailEntityvlidation.getLiceneExpirery(), secret);
			return decpkey;
		}

		return "";
	}

	@Override
	public String getresetlock(String key) {
		LicenceLock licenceLock = licenceLockRepository.findByKey(key);
		if (licenceLock != null) {
			licenceLockRepository.delete(licenceLock);
		}
		return "success";
	}

	@Override
	public String getsetlock(String key) {
		LicenceLock licenceLock = new LicenceLock(key, "1");
		licenceLockRepository.save(licenceLock);
		return licenceLock.getLockvalue();
	}

	@Override
	public String emailvalidatorStatusCheck(String key) {
		LicenceLock licenceLock = licenceLockRepository.findByKey(key.split("_")[0]);
		if (licenceLock != null) {
			if (licenceLock.getLockvalue().equalsIgnoreCase("1")) {
				return "Please Do Reset";
			}
		}
		EmailEntity emailEntityvlidation = emailLicenceDao.findByKey(key.split("_")[0]);
		String lockValue = "0";

		if (emailEntityvlidation != null && emailEntityvlidation.getKey().equalsIgnoreCase(key.split("_")[0])) {
			if(!vlidateExperyDate(emailEntityvlidation)) 
			{
				return "Key Expired";
			}
			String secret = env.getProperty("aes.secretKey");
			String decpkey = AES.decrypt(emailEntityvlidation.getLiceneExpirery(), secret);
			if (!decpkey.equalsIgnoreCase(key)) {
				return "Please Check Licence Key.";
			}
			lockValue = getsetlock(key.split("_")[0]);

		}
		return lockValue;
	}

	@Override
	public String getlockstatus(String key) {
		LicenceLock licenceLock = licenceLockRepository.findByKey(key.split("_")[0]);
		if (licenceLock != null) {
			if (licenceLock.getLockvalue().equalsIgnoreCase("1")) {
				return "1";
			}
		}
		return "";
	}

	@Override
	public String emailSenderActivation(String key, String emailSendertoolName) {

		LocalDate localDatestartdate = LocalDate.now();
		Date startdate = Date.valueOf(localDatestartdate);
		LocalDate localDatelastdate = null;
		String[] keyarray = key.split("#");
		key = keyarray[0];
		String macid = keyarray[1];
		String secret = env.getProperty("aes.secretKey");
		String enkey_365_7 = env.getProperty("licence.product.key_365_7");
		List<String> decpkey_365_7 = licenceKeyService.getOneYearLicencekeys(emailSendertoolName);//AES.decrypt(enkey_365_7, secret);
		String enkey_30_6 = env.getProperty("licence.product.key_30_6");
		List<String> decpkey_30_6 = licenceKeyService.getThirtyDayLicencekeys(emailSendertoolName);
		List<String> decpTrailkey_7days_6 = licenceKeyService.getSevenDayTrailLicencekeys(emailSendertoolName);
		String keyStartDateEndDate ="";
		////#J20G4KL-B43XXAD-N2RSRE0-DSWAWXU
		if (decpkey_365_7.contains(key)) {
			localDatelastdate =localDatestartdate.plus(Period.ofDays(365));
			Date lastdate = Date.valueOf(localDatelastdate);
			keyStartDateEndDate = saveEmailExtractorActivation(key, emailSendertoolName, localDatestartdate,
					startdate, localDatelastdate, lastdate, secret,macid);
		}
		
		else if (decpkey_30_6.contains(key))
		{
			localDatelastdate =localDatestartdate.plus(Period.ofDays(30));
			Date lastdate = Date.valueOf(localDatelastdate);
			keyStartDateEndDate = saveEmailExtractorActivation(key, emailSendertoolName, localDatestartdate,
					startdate, localDatelastdate, lastdate, secret,macid);
		}
		
		else if (decpTrailkey_7days_6.contains(key))
		{
			localDatelastdate =localDatestartdate.plus(Period.ofDays(7));
			Date lastdate = Date.valueOf(localDatelastdate);
			keyStartDateEndDate = saveEmailExtractorActivation(key, emailSendertoolName, localDatestartdate,
					startdate, localDatelastdate, lastdate, secret,macid);
		}
		
		else
		{
			return "Invalid Key " + key;
		}
		 
		return keyStartDateEndDate;
	
	}

	@Override
	public String emailsenderStatusCheck(String key, String string) {
		String[] keys = key.split("_");
		EmailEntity emailEntity =emailLicenceDao.findByKey(keys[0]);
		if(keys[0].equalsIgnoreCase(emailEntity.getKey()) && keys[3].equalsIgnoreCase(emailEntity.getMacId()))
		{
			if(!vlidateExperyDate(emailEntity)) 
			{
				return "Key Expired";
			}
			return "success";
		}
		
		return "fail";
	}

	@Override
	public String semrushlicenceactivate(String key, String semRushToolName) {

		LocalDate localDatestartdate = LocalDate.now();
		Date startdate = Date.valueOf(localDatestartdate);
		LocalDate localDatelastdate = null;
		String[] keyarray = key.split("#");
		key = keyarray[0];
		String macid = keyarray[1];
		String secret = env.getProperty("aes.secretKey");
		String enkey_365_7 = env.getProperty("licence.product.key_365_7");
		List<String> decpkey_365_7 = licenceKeyService.getOneYearLicencekeys(semRushToolName);//AES.decrypt(enkey_365_7, secret);
		String enkey_30_6 = env.getProperty("licence.product.key_30_6");
		List<String> decpkey_30_6 = licenceKeyService.getThirtyDayLicencekeys(semRushToolName);
		
		List<String> decpTrailkey_7days_6 = licenceKeyService.getSevenDayTrailLicencekeys(semRushToolName);
		String keyStartDateEndDate ="";
		////#J20G4KL-B43XXAD-N2RSRE0-DSWAWXU
		if (decpkey_365_7.contains(key)) {
			localDatelastdate =localDatestartdate.plus(Period.ofDays(365));
			Date lastdate = Date.valueOf(localDatelastdate);
			keyStartDateEndDate = saveEmailExtractorActivation(key, semRushToolName, localDatestartdate,
					startdate, localDatelastdate, lastdate, secret,macid);
		}
		
		else if (decpkey_30_6.contains(key))
		{
			localDatelastdate =localDatestartdate.plus(Period.ofDays(30));
			Date lastdate = Date.valueOf(localDatelastdate);
			keyStartDateEndDate = saveEmailExtractorActivation(key, semRushToolName, localDatestartdate,
					startdate, localDatelastdate, lastdate, secret,macid);
		}
		else if (decpTrailkey_7days_6.contains(key))
		{
			localDatelastdate =localDatestartdate.plus(Period.ofDays(7));
			Date lastdate = Date.valueOf(localDatelastdate);
			keyStartDateEndDate = saveEmailExtractorActivation(key, semRushToolName, localDatestartdate,
					startdate, localDatelastdate, lastdate, secret,macid);
		}
		else
		{
			return "Invalid Key " + key;
		}
		 
		return keyStartDateEndDate;
	}

	@Override
	public String semrushstatusCheck(String key, String string) {
		String[] keys = key.split("_");
		EmailEntity emailEntity =emailLicenceDao.findByKey(keys[0]);
		if(keys[0].equalsIgnoreCase(emailEntity.getKey()) && keys[3].equalsIgnoreCase(emailEntity.getMacId()))
		{
			if(!vlidateExperyDate(emailEntity)) 
			{
				return "Key Expired";
			}
			return "success";
		}
		
		return "fail";
	}

	@Override
	public String gSuitelicenceactivate(String key, String gSuiteToolName) {
		LocalDate localDatestartdate = LocalDate.now();
		Date startdate = Date.valueOf(localDatestartdate);
		LocalDate localDatelastdate = null;
		String[] keyarray = key.split("#");
		key = keyarray[0];
		String macid = keyarray[1];
		String secret = env.getProperty("aes.secretKey");
		String enkey_365_7 = env.getProperty("licence.product.key_365_7");
		List<String> decpkey_365_7 = licenceKeyService.getOneYearLicencekeys(gSuiteToolName);//AES.decrypt(enkey_365_7, secret);
		String enkey_30_6 = env.getProperty("licence.product.key_30_6");
		List<String> decpkey_30_6 = licenceKeyService.getThirtyDayLicencekeys(gSuiteToolName);
		
		List<String> decpTrailkey_7days_6 = licenceKeyService.getSevenDayTrailLicencekeys(gSuiteToolName);
		String keyStartDateEndDate ="";
		////#J20G4KL-B43XXAD-N2RSRE0-DSWAWXU
		if (decpkey_365_7.contains(key)) {
			localDatelastdate =localDatestartdate.plus(Period.ofDays(365));
			Date lastdate = Date.valueOf(localDatelastdate);
			keyStartDateEndDate = saveEmailExtractorActivation(key, gSuiteToolName, localDatestartdate,
					startdate, localDatelastdate, lastdate, secret,macid);
		}
		
		else if (decpkey_30_6.contains(key))
		{
			localDatelastdate =localDatestartdate.plus(Period.ofDays(30));
			Date lastdate = Date.valueOf(localDatelastdate);
			keyStartDateEndDate = saveEmailExtractorActivation(key, gSuiteToolName, localDatestartdate,
					startdate, localDatelastdate, lastdate, secret,macid);
		}
		else if (decpTrailkey_7days_6.contains(key))
		{
			localDatelastdate =localDatestartdate.plus(Period.ofDays(7));
			Date lastdate = Date.valueOf(localDatelastdate);
			keyStartDateEndDate = saveEmailExtractorActivation(key, gSuiteToolName, localDatestartdate,
					startdate, localDatelastdate, lastdate, secret,macid);
		}
		else
		{
			return "Invalid Key " + key;
		}
		 
		return keyStartDateEndDate;
	}

	@Override
	public String gSuitetatusCheck(String key, String string) {
		String[] keys = key.split("_");
		EmailEntity emailEntity =emailLicenceDao.findByKey(keys[0]);
		if(keys[0].equalsIgnoreCase(emailEntity.getKey()) && keys[3].equalsIgnoreCase(emailEntity.getMacId()))
		{
			if(!vlidateExperyDate(emailEntity)) 
			{
				return "Key Expired";
			}
			return "success";
		}
		
		return "fail";
	}

	 

	@Override
	public String getnoofallowedCheck(String key) {
		String[] keys = key.split("_");
		NumberOfAllowed numberOfAllowed =numberOfAllowedRepository.findByKey(keys[0]);
		return ""+numberOfAllowed.getNoOfAllowed();
	}

	

}
