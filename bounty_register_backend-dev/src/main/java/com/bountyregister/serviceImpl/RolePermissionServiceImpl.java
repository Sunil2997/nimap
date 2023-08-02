package com.bountyregister.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bountyregister.dto.AssignPermission;
import com.bountyregister.entities.PermissionEntity;
import com.bountyregister.entities.RoleEntity;
import com.bountyregister.entities.RolePermissionEntity;
import com.bountyregister.entities.UserRoleEntity;
import com.bountyregister.exceptionHandling.ResourceNotFoundException;
import com.bountyregister.iListDto.IListRolePermissions;
import com.bountyregister.iListDto.IUserPermissionDto;
import com.bountyregister.repositories.PermissionRepository;
import com.bountyregister.repositories.RolePermissionRepository;
import com.bountyregister.repositories.RoleRepository;
import com.bountyregister.repositories.UserRoleRepository;
import com.bountyregister.service.RolePermissionInterface;
import com.bountyregister.utils.ErrorMessageCode;
import com.bountyregister.utils.Pagination;

@Service
public class RolePermissionServiceImpl implements RolePermissionInterface {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PermissionRepository permissionRepository;

	@Autowired
	private RolePermissionRepository rolePermissionRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;

	@Override
	public void add(AssignPermission assignPermission) {
		try {
			ArrayList<RolePermissionEntity> rolePermissions = new ArrayList<>();
			RoleEntity role = this.roleRepository.findById(assignPermission.getRoleId())
					.orElseThrow(() -> new ResourceNotFoundException(ErrorMessageCode.ROLE_NOT_FOUND));

			for (int i = 0; i < assignPermission.getPermissionId().size(); i++) {
				final Long permissionID = assignPermission.getPermissionId().get(i);
				PermissionEntity permissionEntity = this.permissionRepository.findById(permissionID)
						.orElseThrow(() -> new ResourceNotFoundException(ErrorMessageCode.PERMISSION_NOT_FOUND));

				RolePermissionEntity rolePermissionEntity = new RolePermissionEntity();
				rolePermissionEntity.setRoleId(role);
				rolePermissionEntity.setPermissionId(permissionEntity);

				rolePermissions.add(rolePermissionEntity);
			}
			rolePermissionRepository.saveAll(rolePermissions);
		} catch (Exception e) {
			throw new ResourceNotFoundException(e.getMessage());

		}

	}

	@Override
	public Page<IListRolePermissions> getAllRolePermissions(String search, String pageNo, String pageSize) {
		Page<IListRolePermissions> iListRolePermissions;

		Pageable pageable = new Pagination().getPagination(pageNo, pageSize);
		iListRolePermissions = this.rolePermissionRepository.findByRolePermissions(search, pageable, IListRolePermissions.class);
		return iListRolePermissions;
	}

	@Override
	public ArrayList<String> getPermissionByUserId(Long userId) {
		ArrayList<UserRoleEntity> roleIds = userRoleRepository.getRolesOfUser(userId);
		ArrayList<Long> roles = new ArrayList<>();

		for (int i = 0; i < roleIds.size(); i++) {
			roles.add(roleIds.get(i).getRoleId().getId());
		}
		ArrayList<RolePermissionEntity> rolesPermission = rolePermissionRepository.findPermissionByRole(roles);
		ArrayList<Long> permissions = new ArrayList<>();

		for (int i = 0; i < rolesPermission.size(); i++) {
			permissions.add(rolesPermission.get(i).getPermissionId().getId());
		}

		ArrayList<PermissionEntity> permissionEntity = permissionRepository.findByIdIn(permissions);
		ArrayList<String> actionName = new ArrayList<>();

		for (int i = 0; i < permissionEntity.size(); i++) {
			actionName.add(permissionEntity.get(i).getActionName());
		}
		return actionName;
	}

	@Override
	public List<IUserPermissionDto> getPermissionsByUserId(long id) {
		List<IUserPermissionDto> list=this.rolePermissionRepository.getPermissionById(id, IUserPermissionDto.class);
		
		return list;
		
		
		
	}


}
