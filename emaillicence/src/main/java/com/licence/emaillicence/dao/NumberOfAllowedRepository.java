package com.licence.emaillicence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.licence.emaillicence.model.NumberOfAllowed;

public interface NumberOfAllowedRepository extends JpaRepository<NumberOfAllowed, Long> {
	@Query("FROM NumberOfAllowed e WHERE e.key = :key")
	NumberOfAllowed findByKey(@Param("key")String key);
	
	 

}
