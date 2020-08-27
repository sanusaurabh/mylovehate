package com.licence.emaillicence.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.licence.emaillicence.model.bean.LicenseKeyBean;
import com.licence.emaillicences.service.LicenceKeyService;

@RestController
@RequestMapping("/licencekey")
public class LicenceKeyController {
	
	
	@Autowired
	private LicenceKeyService licenceKeyService;
	
	
	@RequestMapping(value = "/getallLicencekey",method = RequestMethod.GET,produces = "application/json")
	public  List<LicenseKeyBean> getAllActivation() {
		List<LicenseKeyBean> list =licenceKeyService.getAllLicenceKey();
		return list;
	}
	
	@RequestMapping(value = "/getthirtydaylicencekey",method = RequestMethod.POST,produces = "application/json")
	public  List<LicenseKeyBean> getThirtyDayLicencekey(HttpServletRequest handlerServlet) {
		String  toolName = "Email Extractor Tool,Email Validation Tool,Email Sender Tool,SemRush Tool,GSuite Tool";
		 String productNmae = handlerServlet.getParameter("toolName");
		 if(!toolName.contains(productNmae)) {
			 return new ArrayList<LicenseKeyBean>();
		 }
		List<LicenseKeyBean> list =licenceKeyService.getThirtyDayLicencekey(productNmae);
		return list;
	}
	@RequestMapping(value = "/getoneyearlicencekey",method = RequestMethod.POST,produces = "application/json")
	public  List<LicenseKeyBean> getOneYearLicencekey(HttpServletRequest handlerServlet) {
		String  toolName = "Email Extractor Tool,Email Validation Tool,Email Sender Tool,SemRush Tool,GSuite Tool";
		 String productNmae = handlerServlet.getParameter("toolName");
		 if(!toolName.contains(productNmae)) {
			 return new ArrayList<LicenseKeyBean>();
		 }
		List<LicenseKeyBean> list =licenceKeyService.getOneYearLicencekey(productNmae);
		return list;
	}
	
	@RequestMapping(value = "/getsevendaystraillicencekey",method = RequestMethod.POST,produces = "application/json")
	public  List<LicenseKeyBean> getsevendaystraillicencekey(HttpServletRequest handlerServlet) {
		String  toolName = "Email Extractor Tool,Email Validation Tool,Email Sender Tool,SemRush Tool,GSuite Tool";
		 String productNmae = handlerServlet.getParameter("toolName");
		 if(!toolName.contains(productNmae)) {
			 return new ArrayList<LicenseKeyBean>();
		 }
		List<LicenseKeyBean> list =licenceKeyService.getSevenDaysTrailLicencekey(productNmae);
		return list;
	}
	
	@RequestMapping(value = "/createlicencekey",method = RequestMethod.POST,produces = "application/json")
	public  LicenseKeyBean createLicencekey(HttpServletRequest handlerServlet) {
		//http://localhost:9090/licencekey/createlicencekey?key=0S0Q6GT-IK125UH-1JJWL3H-5EQROJT&toolName=Email Extractor Tool&validForNoOfDay=365&isUsed=no
		String  toolName = "Email Extractor Tool,Email Validation Tool,Email Sender Tool,SemRush Tool,GSuite Tool";
		 String productNmae = handlerServlet.getParameter("toolName");
		 
		 if(!toolName.contains(productNmae)) 
		 {
			 return new LicenseKeyBean();
		 }
		      String key= handlerServlet.getParameter("key");
			  String validForNoOfDay = handlerServlet.getParameter("validForNoOfDay");
			  String isUsed= handlerServlet.getParameter("isUsed");
			 
		 LicenseKeyBean list =licenceKeyService.createLicencekey(new LicenseKeyBean(key,productNmae,validForNoOfDay,isUsed));
		return list;
	}

}
