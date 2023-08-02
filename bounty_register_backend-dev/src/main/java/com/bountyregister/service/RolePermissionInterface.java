package com.bountyregister.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.bountyregister.dto.AssignPermission;
import com.bountyregister.iListDto.IListRolePermissions;
import com.bountyregister.iListDto.IUserPermissionDto;

public interface RolePermissionInterface {

	void add(AssignPermission assignPermission);

	public Page<IListRolePermissions> getAllRolePermissions(String search, String pageNo, String pageSize);

	ArrayList<String> getPermissionByUserId(Long id);
	
	List<IUserPermissionDto> getPermissionsByUserId(long id);
}
