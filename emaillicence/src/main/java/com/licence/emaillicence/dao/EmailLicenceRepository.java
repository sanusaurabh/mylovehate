package com.licence.emaillicence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.licence.emaillicence.model.EmailEntity;

public interface EmailLicenceRepository extends JpaRepository<EmailEntity, Long> {
	@Query("FROM EmailEntity e WHERE e.key = :key")
	EmailEntity findByKey(@Param("key")String key);
	
	 

}
