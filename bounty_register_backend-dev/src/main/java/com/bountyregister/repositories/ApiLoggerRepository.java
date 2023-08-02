package com.bountyregister.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bountyregister.entities.ApiLoggerEntity;

@Repository
public interface ApiLoggerRepository extends JpaRepository<ApiLoggerEntity, Long> {

}
