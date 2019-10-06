package com.licence.emaillicence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.licence.emaillicence.model.EmailEntity;
import com.licence.emaillicence.model.LicenceLock;

public interface LicenceLockRepository extends JpaRepository<LicenceLock, Long> {
	@Query("FROM LicenceLock e WHERE e.key = :key")
	LicenceLock findByKey(@Param("key")String key);

}
