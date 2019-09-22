package com.licence.emaillicence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.licence.emaillicence.model.EmailEntity;

public interface EmailLicenceRepository extends CrudRepository<EmailEntity, Long> {

	

}
