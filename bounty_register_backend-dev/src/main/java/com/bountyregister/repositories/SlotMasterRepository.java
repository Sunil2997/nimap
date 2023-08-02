package com.bountyregister.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bountyregister.entities.CustomerMachineSlot;

public interface SlotMasterRepository extends JpaRepository<CustomerMachineSlot, Long>{

}
