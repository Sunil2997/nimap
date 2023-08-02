package com.bountyregister.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bountyregister.entities.UserEntity;

public interface AuthRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByEmailIgnoreCaseAndIsActiveTrue(String email);

	UserEntity findByEmailContainingIgnoreCase(String email);

	UserEntity findByEmailIgnoreCase(String email);

	UserEntity findByEmail(String email);

}
