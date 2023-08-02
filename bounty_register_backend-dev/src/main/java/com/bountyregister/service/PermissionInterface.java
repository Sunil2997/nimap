package com.bountyregister.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.bountyregister.dto.DeleteId;
import com.bountyregister.dto.PermissionDto;
import com.bountyregister.dto.PermissionModuleList;
import com.bountyregister.iListDto.IListPermissionDto;
import com.bountyregister.iListDto.PermissionWithSFDetail;

public interface PermissionInterface {

//	void addPermission(@Valid PermissionDto dto, Long userId) throws Exception;

//	void deletePermission(Long id, Long userId);

	public List<IListPermissionDto> getAllPermissions();

	public PermissionDto getPermissionById(Long id);

//	public PermissionDto updatePermission(PermissionDto dto, Long id, Long userId) throws ResourceNotFoundException;

	public Page<IListPermissionDto> getAllPermissions(String search, String pageNo, String pageSize);

	List<PermissionModuleList> modulePermissionList();

	List<String> getUserPermissions(Long userId);

//	public void uploadPermissions(MultipartFile file, Long userId) throws IOException;

	List<IListPermissionDto> findAllList(Class<IListPermissionDto> class1);

	void deleteMultiplePermissionsById(DeleteId id, Long userId);

	PermissionWithSFDetail getUserPermissionsAndSFDetail(Long userId);

}
