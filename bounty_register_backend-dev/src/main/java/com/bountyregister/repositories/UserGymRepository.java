package com.bountyregister.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bountyregister.entities.CustomerGym;

public interface UserGymRepository extends JpaRepository<CustomerGym, Long>{

}
