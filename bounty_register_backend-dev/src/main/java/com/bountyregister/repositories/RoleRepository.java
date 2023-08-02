package com.bountyregister.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bountyregister.entities.RoleEntity;
import com.bountyregister.iListDto.IListRole;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

	RoleEntity findByRoleNameIgnoreCase(String roleName);

	Page<IListRole> findByOrderByIdDesc(Pageable pageable, Class<IListRole> class1);

	Page<IListRole> findByRoleNameContainingIgnoreCase(String search, Pageable pageable, Class<IListRole> class1);

	List<IListRole> findByOrderByRoleNameAsc(Class<IListRole> class1);

	RoleEntity findByRoleNameIgnoreCaseAndIsActiveTrue(String roleName);

}
