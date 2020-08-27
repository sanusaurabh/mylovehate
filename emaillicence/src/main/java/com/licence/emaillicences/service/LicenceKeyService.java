package com.licence.emaillicences.service;

import java.util.List;

import com.licence.emaillicence.model.bean.EmailBean;
import com.licence.emaillicence.model.bean.LicenseKeyBean;

public interface LicenceKeyService {

	List<LicenseKeyBean> getAllLicenceKey();

	List<LicenseKeyBean> getThirtyDayLicencekey(String productNmae);

	List<LicenseKeyBean> getOneYearLicencekey(String productNmae);
	List<LicenseKeyBean> getSevenDaysTrailLicencekey(String productNmae);

	LicenseKeyBean createLicencekey(LicenseKeyBean licenseKeyBean);
	
	List<String> getOneYearLicencekeys(String productNmae);
	List<String> getThirtyDayLicencekeys(String productNmae);
	
	List<String> getSevenDayTrailLicencekeys(String productNmae);
	
	 

}
