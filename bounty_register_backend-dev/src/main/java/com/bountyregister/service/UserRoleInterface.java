package com.bountyregister.service;


import java.util.List;

import org.springframework.data.domain.Page;

import com.bountyregister.dto.AssignRole;
import com.bountyregister.iListDto.IListUserRoles;

public interface UserRoleInterface {

//	void add(AssignRole assignRole);

	Page<IListUserRoles> getAllUserRole(String search, String pageNo, String pageSize);

	List<String> getRoleByUserId(Long roleId);
}
