package com.licence.emaillicence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.licence.emaillicence.model.LicenceLock;
import com.licence.emaillicence.model.LicenseKeyEntity;

public interface LicenceKeyRepository extends JpaRepository<LicenseKeyEntity, Long> {
	@Query("FROM LicenseKeyEntity e WHERE e.key = :key")
	LicenseKeyEntity findByKey(@Param("key")String key);

}
