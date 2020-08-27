package com.licence.emaillicence.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.licence.emaillicence.dao.LicenceKeyRepository;
import com.licence.emaillicence.execption.LicenseKeyEntityNotFoundException;
import com.licence.emaillicence.model.LicenseKeyEntity;

@RestController
public class LicenceKeyEntityController {

	@Autowired
	private LicenceKeyRepository eicenceKeyRepository;

	@GetMapping("/getAllLicenseKeyEntity")
	public List<LicenseKeyEntity> getAllNotes() {
		return eicenceKeyRepository.findAll();
	}

	// Create a new Note
	@PostMapping("/createLicenseKeyEntity")
	public LicenseKeyEntity createLicenseKeyEntity(@Valid @RequestBody LicenseKeyEntity licenseKeyEntity) {
		return eicenceKeyRepository.save(licenseKeyEntity);
	}

	// Get a Single Note
	@GetMapping("/getLicenseKeyEntityById/{id}")
	public LicenseKeyEntity getLicenseKeyEntityById(@PathVariable(value = "id") Long licenseKeyEntityId)
			throws LicenseKeyEntityNotFoundException {
		return eicenceKeyRepository.findById(licenseKeyEntityId)
				.orElseThrow(() -> new LicenseKeyEntityNotFoundException(licenseKeyEntityId));
	}

	// Update a Note
	@PutMapping("/updateLicenseKeyEntity/{id}")
	public LicenseKeyEntity updateLicenseKeyEntity(@PathVariable(value = "id") Long licenseKeyEntityId,
			@Valid @RequestBody LicenseKeyEntity licenseKeyEntityDetails) throws LicenseKeyEntityNotFoundException {

		LicenseKeyEntity licenseKeyEntity = eicenceKeyRepository.findById(licenseKeyEntityId)
				.orElseThrow(() -> new LicenseKeyEntityNotFoundException(licenseKeyEntityId));
		licenseKeyEntity.setId(licenseKeyEntity.getId());
		licenseKeyEntity.setKey(licenseKeyEntityDetails.getKey());
		licenseKeyEntity.setIsUsed(licenseKeyEntityDetails.getIsUsed());
		licenseKeyEntity.setValidForNoOfDay(licenseKeyEntityDetails.getValidForNoOfDay());
		licenseKeyEntity.setToolName(licenseKeyEntityDetails.getToolName());
		

		LicenseKeyEntity updatedBook = eicenceKeyRepository.saveAndFlush(licenseKeyEntity);

		return updatedBook;
	}

	// Delete a Note
	@DeleteMapping("/deleteLicenseKeyEntity/{id}")
	public ResponseEntity<?> deleteLicenseKeyEntity(@PathVariable(value = "id") Long LicenseKeyEntityId)
			throws LicenseKeyEntityNotFoundException {
		LicenseKeyEntity LicenseKeyEntity = eicenceKeyRepository.findById(LicenseKeyEntityId)
				.orElseThrow(() -> new LicenseKeyEntityNotFoundException(LicenseKeyEntityId));

		eicenceKeyRepository.delete(LicenseKeyEntity);

		return ResponseEntity.ok().build();
	}
	
	
	// Update a Note
		@PutMapping("/findbykeyLicenseKeyEntity/{key}")
		public LicenseKeyEntity findbykeyLicenseKeyEntity(@PathVariable(value = "key") String key) throws LicenseKeyEntityNotFoundException {

			LicenseKeyEntity LicenseKeyEntity = eicenceKeyRepository.findByKey(key);
			return LicenseKeyEntity;
		}
}
