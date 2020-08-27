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

import com.licence.emaillicence.dao.EmailLicenceRepository;
import com.licence.emaillicence.execption.EmailEntityNotFoundException;
import com.licence.emaillicence.model.EmailEntity;

@RestController
public class EmailEntityController {

	@Autowired
	private EmailLicenceRepository emailLicenceRepository;

	@GetMapping("/getAllEmailEntity")
	public List<EmailEntity> getAllNotes() {
		return emailLicenceRepository.findAll();
	}

	// Create a new Note
	@PostMapping("/createEmailEntity")
	public EmailEntity createEmailEntity(@Valid @RequestBody EmailEntity emailEntity) {
		return emailLicenceRepository.save(emailEntity);
	}

	// Get a Single Note
	@GetMapping("/getEmailEntityById/{id}")
	public EmailEntity getEmailEntityById(@PathVariable(value = "id") Long emailEntityId)
			throws EmailEntityNotFoundException {
		return emailLicenceRepository.findById(emailEntityId)
				.orElseThrow(() -> new EmailEntityNotFoundException(emailEntityId));
	}

	// Update a Note
	@PutMapping("/updateEmailEntity/{id}")
	public EmailEntity updateEmailEntity(@PathVariable(value = "id") Long emailEntityId,
			@Valid @RequestBody EmailEntity emailEntityDetails) throws EmailEntityNotFoundException {

		EmailEntity emailEntity = emailLicenceRepository.findById(emailEntityId)
				.orElseThrow(() -> new EmailEntityNotFoundException(emailEntityId));
		emailEntity.setId(emailEntityId);
		emailEntity.setKey(emailEntityDetails.getKey());
		emailEntity.setLastdate(emailEntityDetails.getLastdate());
		emailEntity.setLiceneExpirery(emailEntityDetails.getLiceneExpirery());
		emailEntity.setMacId(emailEntityDetails.getMacId());
		emailEntity.setStartdate(emailEntityDetails.getStartdate());
		emailLicenceRepository.saveAndFlush(emailEntityDetails);

		EmailEntity updatedBook = emailLicenceRepository.saveAndFlush(emailEntityDetails);

		return updatedBook;
	}

	// Delete a Note
	@DeleteMapping("/deleteEmailEntity/{id}")
	public ResponseEntity<?> deleteEmailEntity(@PathVariable(value = "id") Long emailEntityId)
			throws EmailEntityNotFoundException {
		EmailEntity emailEntity = emailLicenceRepository.findById(emailEntityId)
				.orElseThrow(() -> new EmailEntityNotFoundException(emailEntityId));

		emailLicenceRepository.delete(emailEntity);

		return ResponseEntity.ok().build();
	}
	
	
	// Update a Note
		@PutMapping("/findbykeyEmailEntity/{key}")
		public EmailEntity findbykeyEmailEntity(@PathVariable(value = "key") String key) throws EmailEntityNotFoundException {

			EmailEntity emailEntity = emailLicenceRepository.findByKey(key);
			return emailEntity;
		}
}
