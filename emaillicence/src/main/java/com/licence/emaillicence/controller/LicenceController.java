package com.licence.emaillicence.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.licence.emaillicence.model.bean.EmailBean;
import com.licence.emaillicence.util.AES;
import com.licence.emaillicences.service.EmailLicenceService;
import com.licence.emaillicences.service.LicenceKeyService;

@RestController
@RequestMapping("/licence")
public class LicenceController {
	
	@Autowired
	private EmailLicenceService emailLicenceService;
	
	
	
	
	
	private Map< String, Integer> listofCurrentuser = new HashMap<>();
	
	 @Autowired
	 private Environment env;
	
	@RequestMapping(value="/activate",method= RequestMethod.POST)
	public int getActiveAccount(HttpServletRequest handlerServlet) 
	{
		 String key =handlerServlet.getParameter("key");
		 String keyValue = env.getProperty("licence.product.key");
		 String secret = env.getProperty("aes.secretKey");
		 String decpkey = AES.decrypt(key, secret);
		 if(keyValue.contains(decpkey)) {
			
			 listofCurrentuser.put(decpkey, 1);
			 return 1;
		 }
		return 0;
		
	}
	
	public EmailLicenceService getEmailLicenceService() {
		return emailLicenceService;
	}

	public void setEmailLicenceService(EmailLicenceService emailLicenceService) {
		this.emailLicenceService = emailLicenceService;
	}

	@RequestMapping(value="/deactivate",method= RequestMethod.POST)
	public int getdeactivate(HttpServletRequest handlerServlet) throws IOException 
	{//http://localhost:9090/licence/deactivate?key=DEGL-NNNW-V0BN-TEBT
		
		 String key =handlerServlet.getParameter("key");
		 String keyValue = env.getProperty("licence.product.key");
		 String secret = env.getProperty("aes.secretKey");
		 String decpkey = AES.decrypt(key, secret);
		 if(keyValue.contains(decpkey)) {
			 listofCurrentuser.remove(decpkey);
		 }
		 System.out.println(listofCurrentuser.get(decpkey));
		return 0;
		
	}
	
	
	
	@RequestMapping(value="/emailextractoractivateion",method= RequestMethod.POST)
	public String getEmailextractorActivateionAccount(HttpServletRequest handlerServlet) 
	{
		 String key =handlerServlet.getParameter("key");
		 String licenceKey =emailLicenceService.emailExtractoractivation(key,"Email Extractor Tool");
		 return licenceKey;
		
	}
	
	
	@RequestMapping(value="/emailextractorexportstatuscheck",method= RequestMethod.POST)
	public String getEmailextractorExportStatusCheck(HttpServletRequest handlerServlet) 
	{
		 String key =handlerServlet.getParameter("key");
		 String licenceKey =emailLicenceService.emailextractorExportStatusCheck(key,"Email Extractor Tool");
		 return licenceKey;
		
	}
	
	
	@RequestMapping("/helo")
	public String hello(@RequestParam("key") String key) 
	{
		 //http://localhost:9090/licence/helo?key=
		return "hello";
	}
	
	
	@RequestMapping(value = "/allactivationkey",method = RequestMethod.GET,produces = "application/json")
	public  List<EmailBean> getAllActivation() {
		List<EmailBean> list =emailLicenceService.getAllActivation();
		return list;
	
	
	}
	
	@RequestMapping(value="/emailvalidationlicenceactivate",method= RequestMethod.POST)
	public String getActiveEmailvalidatorLicenceaAccount(HttpServletRequest handlerServlet) 
	{
		//0VRC-RJRB-GEXT-ARNQ
		 String key =handlerServlet.getParameter("key");
		 String value = emailLicenceService.emailvalidatorLicenceaAccount(key,"Email Validation Tool");
		 return value;
		
	}
	
	@RequestMapping(value="/emailvalidationtstatuscheck",method= RequestMethod.POST)
	public String getActiveEmailvalidatorStatusCheck(HttpServletRequest handlerServlet) 
	{
		//0VRC-RJRB-GEXT-ARNQ
		 String key =handlerServlet.getParameter("key");
		 String value = emailLicenceService.emailvalidatorStatusCheck(key);
		 return value;
		
	}
	
	@RequestMapping(value="/resetlicenceactivate",method= RequestMethod.POST)
	public String getresetlicenceactivateAccount(HttpServletRequest handlerServlet) 
	{
		//0VRC-RJRB-GEXT-ARNQ
		 String key =handlerServlet.getParameter("key");
		 String value = emailLicenceService.resetlicenceactivateAccount(key);
		 return value;
		
	}
	
	@RequestMapping(value="/resetlock",method= RequestMethod.POST)
	public String getresetlock(HttpServletRequest handlerServlet) 
	{
		//0VRC-RJRB-GEXT-ARNQ
		 String key =handlerServlet.getParameter("key");
		 String value = emailLicenceService.getresetlock(key);
		 return value;
		
	}
	
	@RequestMapping(value="/setlock",method= RequestMethod.POST)
	public String getsetlock(HttpServletRequest handlerServlet) 
	{
		//0VRC-RJRB-GEXT-ARNQ
		 String key =handlerServlet.getParameter("key");
		 String value = emailLicenceService.getsetlock(key);
		 return value;
		
	}//lockstatus
	
	@RequestMapping(value="/lockstatus",method= RequestMethod.POST)
	public String getlockstatus(HttpServletRequest handlerServlet) 
	{
		//0VRC-RJRB-GEXT-ARNQ
		 String key =handlerServlet.getParameter("key");
		 String value = emailLicenceService.getlockstatus(key);
		 return value;
		
	}
	
	
	@RequestMapping(value="/emailsenderactivation",method= RequestMethod.POST)
	public String getEmailsenderactivationAccount(HttpServletRequest handlerServlet) 
	{
		 String key =handlerServlet.getParameter("key");
		 String licenceKey =emailLicenceService.emailSenderActivation(key,"Email Sender Tool");
		 return licenceKey;
		
	}
	
	
	@RequestMapping(value="/emailsenderstatuscheck",method= RequestMethod.POST)
	public String getEmailsenderStatusCheck(HttpServletRequest handlerServlet) 
	{
		 String key =handlerServlet.getParameter("key");
		 String licenceKey =emailLicenceService.emailsenderStatusCheck(key,"Email Sender Tool");
		 return licenceKey;
		
	}
	
	@RequestMapping(value="/semrushlicenceactivate",method= RequestMethod.POST)
	public String getsemrushlicenceactivateAccount(HttpServletRequest handlerServlet) 
	{
		 String key =handlerServlet.getParameter("key");
		 String licenceKey =emailLicenceService.semrushlicenceactivate(key,"SemRush Tool");
		 return licenceKey;
		
	}
	
	
	@RequestMapping(value="/semrushstatuscheck",method= RequestMethod.POST)
	public String getsemrushstatusCheck(HttpServletRequest handlerServlet) 
	{
		 String key =handlerServlet.getParameter("key");
		 String licenceKey =emailLicenceService.semrushstatusCheck(key,"SemRush Tool");
		 return licenceKey;
		
	}
	
	@RequestMapping(value="/gsuitelicenceactivate",method= RequestMethod.POST)
	public String getgsuitelicenceactivateAccount(HttpServletRequest handlerServlet) 
	{
		 String key =handlerServlet.getParameter("key");
		 String licenceKey =emailLicenceService.gSuitelicenceactivate(key,"GSuite Tool");
		 return licenceKey;
		
	}
	
	
	@RequestMapping(value="/gsuitestatuscheck",method= RequestMethod.POST)
	public String getGSuitetatusCheck(HttpServletRequest handlerServlet) 
	{
		 String key =handlerServlet.getParameter("key");
		 String licenceKey =emailLicenceService.gSuitetatusCheck(key,"GSuite Tool");
		 return licenceKey;
		
	}
	
	@RequestMapping(value="/noofallowed",method= RequestMethod.POST)
	public String getnoofallowedCheck(HttpServletRequest handlerServlet) 
	{
		 String key =handlerServlet.getParameter("key");
		 String noofallowed =emailLicenceService.getnoofallowedCheck(key);
		 return noofallowed;
		
	}
	
	@RequestMapping(value="/pinterestlicenceactivate",method= RequestMethod.POST)
	public String getpinterestlicenceactivateAccount(HttpServletRequest handlerServlet) 
	{
		 String key =handlerServlet.getParameter("key");
		 String licenceKey =emailLicenceService.getpinterestlicenceactivateAccount(key,"Pinterest Tool");
		 return licenceKey;
		
	}
	
	
	@RequestMapping(value="/pintereststatuscheck",method= RequestMethod.POST)
	public String getpintereststatusCheck(HttpServletRequest handlerServlet) 
	{
		 String key =handlerServlet.getParameter("key");
		 String licenceKey =emailLicenceService.getpintereststatusCheck(key,"Pinterest Tool");
		 return licenceKey;
		
	}
	
	
	
	 
	
}
