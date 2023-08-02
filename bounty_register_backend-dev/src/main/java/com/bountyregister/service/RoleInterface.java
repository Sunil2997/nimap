package com.bountyregister.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.bountyregister.dto.RoleDto;
import com.bountyregister.iListDto.IListRole;
import com.bountyregister.iListDto.IListRolePermissions;

public interface RoleInterface {

	Page<IListRole> getAllRoles(String search, String pageNo, String pageSize) throws Exception;

//	RoleDto updateRoles(RoleDto roleDto, Long id, Long userId);

//	RoleDto addRole(RoleDto roleDto, Long userId);

//	public void deleteRoleById(Long roleId, Long userId);

	List<IListRole> getAllRole();

	List<IListRolePermissions> getPermissionsByRoleId(Long roleId);
}
