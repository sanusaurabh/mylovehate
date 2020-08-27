package com.licence.emaillicence.controller;

import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.licence.emaillicence.dao.NumberOfAllowedRepository;
import com.licence.emaillicence.execption.LicenceLockNotFoundException;
import com.licence.emaillicence.model.LicenceLock;
import com.licence.emaillicence.model.NumberOfAllowed;

@RestController
public class NumberOfAllowedController {

	@Autowired
	private NumberOfAllowedRepository numberOfAllowedRepository;

	@GetMapping("/getAllNumberOfAllowed")
	public List<NumberOfAllowed> getAllNotes() {
		return numberOfAllowedRepository.findAll();
	}

	// Create a new Note
	@PostMapping("/createNumberOfAllowed")
	public NumberOfAllowed createNumberOfAllowed(@Valid @RequestBody NumberOfAllowed numberOfAllowed) {
		return numberOfAllowedRepository.save(numberOfAllowed);
	}

	// Get a Single Note
	@GetMapping("/getNumberOfAllowedById/{id}")
	public NumberOfAllowed getNumberOfAllowedById(@PathVariable(value = "id") Long numberOfAllowedId)
			throws LicenceLockNotFoundException {
		return numberOfAllowedRepository.findById(numberOfAllowedId)
				.orElseThrow(() -> new LicenceLockNotFoundException(numberOfAllowedId));
	}

	// Update a Note
	@PutMapping("/updateNumberOfAllowed/{id}")
	public NumberOfAllowed updateNumberOfAllowed(@PathVariable(value = "id") Long numberOfAllowedId,
			@Valid @RequestBody NumberOfAllowed numberOfAllowedDetails) throws LicenceLockNotFoundException {

		NumberOfAllowed numberOfAllowed = numberOfAllowedRepository.findById(numberOfAllowedId)
				.orElseThrow(() -> new LicenceLockNotFoundException(numberOfAllowedId));
		numberOfAllowed.setId(numberOfAllowedDetails.getId());
		numberOfAllowed.setKey(numberOfAllowedDetails.getKey());
		numberOfAllowed.setNoOfAllowed(numberOfAllowedDetails.getNoOfAllowed());
		numberOfAllowed.setToolName(numberOfAllowedDetails.getToolName());
		numberOfAllowed.setDescription(numberOfAllowedDetails.getDescription());
		NumberOfAllowed updatedBook = numberOfAllowedRepository.save(numberOfAllowed);

		return updatedBook;
	}

	// Delete a Note
	@DeleteMapping("/deleteNumberOfAllowed/{id}")
	public ResponseEntity<?> deleteLicenceLock(@PathVariable(value = "id") Long numberOfAllowedId)
			throws LicenceLockNotFoundException {
		NumberOfAllowed numberOfAllowed = numberOfAllowedRepository.findById(numberOfAllowedId)
				.orElseThrow(() -> new LicenceLockNotFoundException(numberOfAllowedId));

		numberOfAllowedRepository.delete(numberOfAllowed);

		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/getNewKey")
	public String getNewKey() 
	{
		 //http://localhost:9090/licence/helo?key=
		String key ="";
		 int i,k,j=0,sub;
		 Random random = new Random();
		 char[] charachters= {'A','B','C','D','E','F','G','H','I','J','K','L','N','O','P','Q','R','S','T','U','V','W','X','Z','0','1','2','3','4','5','6'};
		 //System.out.println("How many key want to print");
		 int keys = 1;
		 do {
			 key="";
			 i=0;
			 do {
				 k=0;
				 do {
					 sub= random.nextInt(charachters.length);
					 k++;
					 key+=charachters[sub];
				 } while(k < 8); 
					if(i < 3) key +="-"; 
				i++;
			 }while(i < 4); 
			// System.out.println(key);
			 j++;
		 }while(j < keys); 
		
		return key;
	}
	
	
 
}
