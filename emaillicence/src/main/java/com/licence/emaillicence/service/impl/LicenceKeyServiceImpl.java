package com.licence.emaillicence.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.licence.emaillicence.dao.LicenceKeyRepository;
import com.licence.emaillicence.model.LicenseKeyEntity;
import com.licence.emaillicence.model.bean.LicenseKeyBean;
import com.licence.emaillicences.service.LicenceKeyService;
@Service
public class LicenceKeyServiceImpl implements LicenceKeyService {
	@Autowired
	private LicenceKeyRepository licenceKeyRepository;

	@Override
	public List<LicenseKeyBean> getAllLicenceKey() {
		List<LicenseKeyEntity> LicenseKeyEntityList = licenceKeyRepository.findAll();
		List<LicenseKeyBean>  licenseKeyBeanList = new ArrayList<LicenseKeyBean>();
		for(LicenseKeyEntity licenseKeyEntity : LicenseKeyEntityList)
		{
			licenseKeyBeanList.add(new LicenseKeyBean(licenseKeyEntity.getId(), licenseKeyEntity.getKey(), licenseKeyEntity.getToolName(), licenseKeyEntity.getValidForNoOfDay(), licenseKeyEntity.getIsUsed()));
		}
		return  licenseKeyBeanList;
	}

	@Override
	public List<LicenseKeyBean> getThirtyDayLicencekey(String productNmae) {
		List<LicenseKeyEntity> LicenseKeyEntityList = licenceKeyRepository.findAll();
		List<LicenseKeyBean>  licenseKeyBeanList = new ArrayList<LicenseKeyBean>();
		for(LicenseKeyEntity licenseKeyEntity : LicenseKeyEntityList)
		{
			if(licenseKeyEntity.getToolName().contentEquals(productNmae)&& licenseKeyEntity.getValidForNoOfDay().equalsIgnoreCase("30"))
			licenseKeyBeanList.add(new LicenseKeyBean(licenseKeyEntity.getId(), licenseKeyEntity.getKey(), licenseKeyEntity.getToolName(), licenseKeyEntity.getValidForNoOfDay(), licenseKeyEntity.getIsUsed()));
		}
		return licenseKeyBeanList;
	}

	@Override
	public List<LicenseKeyBean> getOneYearLicencekey(String productNmae) {
		List<LicenseKeyEntity> LicenseKeyEntityList = licenceKeyRepository.findAll();
		List<LicenseKeyBean>  licenseKeyBeanList = new ArrayList<LicenseKeyBean>();
		for(LicenseKeyEntity licenseKeyEntity : LicenseKeyEntityList)
		{
			if(licenseKeyEntity.getToolName().contentEquals(productNmae)&& licenseKeyEntity.getValidForNoOfDay().equalsIgnoreCase("365"))
			licenseKeyBeanList.add(new LicenseKeyBean(licenseKeyEntity.getId(), licenseKeyEntity.getKey(), licenseKeyEntity.getToolName(), licenseKeyEntity.getValidForNoOfDay(), licenseKeyEntity.getIsUsed()));
		}
		return licenseKeyBeanList;
	}

	@Override
	public LicenseKeyBean createLicencekey(LicenseKeyBean licenseKeyBean) {
		LicenseKeyEntity licenseKeyEntity =licenceKeyRepository.save(new LicenseKeyEntity( licenseKeyBean.getKey(), licenseKeyBean.getToolName(), licenseKeyBean.getValidForNoOfDay(), licenseKeyBean.getIsUsed()));
		licenseKeyBean.setId(licenseKeyEntity.getId());
		return licenseKeyBean;
	}

	@Override
	public List<String> getOneYearLicencekeys(String productNmae) {
		List<LicenseKeyEntity> LicenseKeyEntityList = licenceKeyRepository.findAll();
		List<String>  licenseKeyBeanList = new ArrayList<String>();
		for(LicenseKeyEntity licenseKeyEntity : LicenseKeyEntityList)
		{
			if(licenseKeyEntity.getToolName().contentEquals(productNmae)&& licenseKeyEntity.getValidForNoOfDay().equalsIgnoreCase("365"))
			licenseKeyBeanList.add(licenseKeyEntity.getKey());
		}
		return licenseKeyBeanList;
	}

	@Override
	public List<String> getThirtyDayLicencekeys(String productNmae) {
		List<LicenseKeyEntity> LicenseKeyEntityList = licenceKeyRepository.findAll();
		List<String>  licenseKeyBeanList = new ArrayList<String>();
		for(LicenseKeyEntity licenseKeyEntity : LicenseKeyEntityList)
		{
			if(licenseKeyEntity.getToolName().contentEquals(productNmae)&& licenseKeyEntity.getValidForNoOfDay().equalsIgnoreCase("30"))
			licenseKeyBeanList.add(licenseKeyEntity.getKey());
		}
		return licenseKeyBeanList;
	}

}
