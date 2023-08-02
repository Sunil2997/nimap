package com.bountyregister.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bountyregister.entities.CustomerMaster;
@Repository
	public interface CustomerMasterRepository extends JpaRepository<CustomerMaster, Long> {

	    @Transactional
	    @Modifying
	    @Query(value = "update CustomerMaster c set c.isActive = true where c.id = :id and c.isActive = false")
	    void activateIsActive(@Param("id") Long id);
	}