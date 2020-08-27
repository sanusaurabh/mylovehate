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

import com.licence.emaillicence.dao.LicenceLockRepository;
import com.licence.emaillicence.execption.LicenceLockNotFoundException;
import com.licence.emaillicence.model.LicenceLock;

@RestController
public class LicenceLockController {

	@Autowired
	private LicenceLockRepository licenceLockRepository;

	@GetMapping("/getAllLicenceLock")
	public List<LicenceLock> getAllNotes() {
		return licenceLockRepository.findAll();
	}

	// Create a new Note
	@PostMapping("/createLicenceLock")
	public LicenceLock createLicenceLock(@Valid @RequestBody LicenceLock LicenceLock) {
		return licenceLockRepository.save(LicenceLock);
	}

	// Get a Single Note
	@GetMapping("/getLicenceLockById/{id}")
	public LicenceLock getLicenceLockById(@PathVariable(value = "id") Long LicenceLockId)
			throws LicenceLockNotFoundException {
		return licenceLockRepository.findById(LicenceLockId)
				.orElseThrow(() -> new LicenceLockNotFoundException(LicenceLockId));
	}

	// Update a Note
	@PutMapping("/updateLicenceLock/{id}")
	public LicenceLock updateLicenceLock(@PathVariable(value = "id") Long LicenceLockId,
			@Valid @RequestBody LicenceLock licenceLockDetails) throws LicenceLockNotFoundException {

		LicenceLock licenceLock = licenceLockRepository.findById(LicenceLockId)
				.orElseThrow(() -> new LicenceLockNotFoundException(LicenceLockId));
		licenceLock.setId(licenceLockDetails.getId());
		licenceLock.setKey(licenceLockDetails.getKey());
		 
		licenceLock.setLockvalue(licenceLockDetails.getLockvalue());

		LicenceLock updatedBook = licenceLockRepository.save(licenceLock);

		return updatedBook;
	}

	// Delete a Note
	@DeleteMapping("/deleteLicenceLock/{id}")
	public ResponseEntity<?> deleteLicenceLock(@PathVariable(value = "id") Long LicenceLockId)
			throws LicenceLockNotFoundException {
		LicenceLock LicenceLock = licenceLockRepository.findById(LicenceLockId)
				.orElseThrow(() -> new LicenceLockNotFoundException(LicenceLockId));

		licenceLockRepository.delete(LicenceLock);

		return ResponseEntity.ok().build();
	}
	
 
}
