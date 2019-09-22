package com.licence.emaillicence.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.licence.emaillicence.dao.EmailLicenceRepository;
import com.licence.emaillicence.model.EmailEntity;
import com.licence.emaillicence.model.bean.EmailBean;
import com.licence.emaillicence.util.AES;
import com.licence.emaillicences.service.EmailLicenceService;

@Service
public class EmailLicenceServiceImpl implements EmailLicenceService {

	@Autowired
	private EmailLicenceRepository emailLicenceDao;
	
	
	 @Autowired
	 private Environment env;

	public EmailLicenceRepository getEmailLicenceDao() {
		return emailLicenceDao;
	}

	public void setEmailLicenceDao(EmailLicenceRepository emailLicenceDao) {
		this.emailLicenceDao = emailLicenceDao;
	}

	@Override
	public void activation(String key) {

		// int twelve = LocalDate.parse("System.currentTimeMillis()").getDayOfMonth();
		//
		// Date startdate = new Date(System.currentTimeMillis() );
		//
		// //Date lastdate = new Date(twelve);
		// LocalDate lastdate = LocalDate.now().plusDays(1);
		//
		//
		//
		//
		LocalDate localDatestartdate = LocalDate.now();
		Date startdate = Date.valueOf(localDatestartdate);
		LocalDate localDatelastdate = localDatestartdate.plus(Period.ofDays(30));
		Date lastdate = Date.valueOf(localDatelastdate);
		
		 String secret = env.getProperty("aes.secretKey");
		 String decpkey = AES.encrypt(key, secret);

		EmailEntity emailEntity = new EmailEntity(key, startdate, lastdate,decpkey);
		emailLicenceDao.save(emailEntity);

	}

	@Override
	public List<EmailBean> getAllActivation() {
		List<EmailBean> emList = new ArrayList<>();
		Iterable<EmailEntity>  emailEntityList = emailLicenceDao.findAll();
		
		for(EmailEntity emailEntity :emailEntityList)
		{
			emList.add(new EmailBean(emailEntity.getId(), emailEntity.getKey(), emailEntity.getStartdate(), emailEntity.getLastdate()));
		}
		
		return emList;
	}

	@Override
	public void encription(String key) {
		// TODO Auto-generated method stub
		
	}

}
