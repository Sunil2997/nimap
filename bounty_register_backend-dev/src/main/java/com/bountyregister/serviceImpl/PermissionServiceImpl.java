package com.bountyregister.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bountyregister.dto.DeleteId;
import com.bountyregister.dto.PermissionDto;
import com.bountyregister.dto.PermissionList;
import com.bountyregister.dto.PermissionModuleList;
import com.bountyregister.entities.PermissionEntity;
import com.bountyregister.exceptionHandling.ResourceNotFoundException;
import com.bountyregister.iListDto.IListOfPermissionsAndSFDetail;
import com.bountyregister.iListDto.IListPermission;
import com.bountyregister.iListDto.IListPermissionDto;
import com.bountyregister.iListDto.PermissionWithSFDetail;
import com.bountyregister.repositories.PermissionRepository;
import com.bountyregister.repositories.RolePermissionRepository;
import com.bountyregister.service.PermissionInterface;
import com.bountyregister.utils.ErrorMessageCode;
import com.bountyregister.utils.Pagination;

@Service
public class PermissionServiceImpl implements PermissionInterface {

	@Autowired
	private PermissionRepository permissionRepository;

//	@Autowired
//	private CacheOperation cacheOperation;

	@Autowired
	RolePermissionRepository rolePermissionRepository;

//	@Override
//	public void addPermission(PermissionDto dto, Long userId) throws Exception {
//		PermissionEntity permission = this.permissionRepository.findByActionNameIgnoreCase(dto.getActionName().trim());
//		if (permission != null) {
//			throw new ResourceNotFoundException(ErrorMessageCode.PERMISSION_ALREADY_PRESENT);
//		}
//
//		PermissionEntity permissionEntity = new PermissionEntity();
//		permissionEntity.setActionName(dto.getActionName().trim());
//		permissionEntity.setDescription(dto.getDescription());
//		permissionEntity.setMethod(dto.getMethod());
//		permissionEntity.setUrl(dto.getUrl());
//		permissionEntity.setCreatedBy(userId);
//		permissionRepository.save(permissionEntity);
//		cacheOperation.removeAllKeysStartingWith();
//
//	}

//	@Override
//	public void deletePermission(Long id, Long userId) {
//		PermissionEntity permissionEntity = permissionRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException(ErrorMessageCode.PERMISSION_NOT_FOUND));
//		permissionEntity.setUpdatedBy(userId);
//		permissionEntity.setActive(false);
//		permissionRepository.save(permissionEntity);
//		List<RolePermissionEntity> rolePermissionEntity = rolePermissionRepository.findByPermissionId(id);
//		rolePermissionRepository.deleteAll(rolePermissionEntity);
//		cacheOperation.removeAllKeysStartingWith();
//	}

	@Override
	public List<IListPermissionDto> getAllPermissions() {
		List<IListPermissionDto> iListPermissionDtos = this.permissionRepository
				.findByOrderByActionNameAsc(IListPermissionDto.class);
		return iListPermissionDtos;
	}

	@Override
	public PermissionDto getPermissionById(Long id) {
		PermissionEntity entity = this.permissionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorMessageCode.PERMISSION_NOT_FOUND));

		this.permissionRepository.save(entity);

		return this.permissionToDto(entity);
	}

//	@Override
//	public PermissionDto updatePermission(PermissionDto dto, Long id, Long userId) throws ResourceNotFoundException {
//
//		PermissionEntity permissionEntity = this.permissionRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException(ErrorMessageCode.PERMISSION_NOT_FOUND));
//		PermissionEntity permission = this.permissionRepository.findByActionNameIgnoreCase(dto.getActionName().trim());
//		if (permission != null) {
//			if (permission.getId() != permissionEntity.getId()) {
//				throw new ResourceNotFoundException(ErrorMessageCode.PERMISSION_ALREADY_PRESENT);
//			}
//		}
//		permissionEntity.setActionName(dto.getActionName().trim());
//		permissionEntity.setDescription(dto.getDescription());
//		permissionEntity.setUpdatedBy(userId);
//		permissionEntity.setMethod(dto.getMethod());
//		permissionEntity.setUrl(dto.getUrl());
//		this.permissionRepository.save(permissionEntity);
//		cacheOperation.removeAllKeysStartingWith();
//		return dto;
//	}

	@Override
	public Page<IListPermissionDto> getAllPermissions(String search, String pageNo, String pageSize) {
		Pageable pagingPageable = new Pagination().getPagination(pageNo, pageSize);

		return this.permissionRepository.findByActionName(search, pagingPageable, IListPermissionDto.class);
	}

	// dto to permission
	public PermissionEntity dtoToPermission(PermissionDto permissionDto) {
		PermissionEntity permissionEntity = new PermissionEntity();
		permissionEntity.setActionName(permissionDto.getActionName());
		permissionEntity.setDescription(permissionDto.getDescription());

		return permissionEntity;

	}

	// permission to dto
	public PermissionDto permissionToDto(PermissionEntity permissionEntity) {
		PermissionDto permissionDto = new PermissionDto();
		permissionDto.setActionName(permissionEntity.getActionName());
		permissionDto.setDescription(permissionEntity.getDescription());

		return permissionDto;

	}

	@Override
	public List<PermissionModuleList> modulePermissionList() {
		List<IListPermission> list;

		list = this.permissionRepository.findByModuleWisePermission(IListPermission.class);

		List<PermissionModuleList> permissionModuleList = new ArrayList<>();

		if (list != null) {

			list.forEach(pagePermission -> {

				if (permissionModuleList.isEmpty()
						|| !checkUserExists(permissionModuleList, pagePermission.getModuleId())) {

					PermissionModuleList permissionModule = new PermissionModuleList();

					permissionModule.setModuleId(pagePermission.getModuleId());
					permissionModule.setModuleName(pagePermission.getModuleName());

					List<PermissionList> permissions = new ArrayList<>();
					PermissionList permission = new PermissionList();

					permission.setPermissionId(pagePermission.getPermissionId());
					permission.setPermissionName(pagePermission.getPermissionName());
					permission.setDescription(pagePermission.getDescription());
					permission.setMethod(pagePermission.getMethod());
					permission.setUrl(pagePermission.getUrl());
					permissions.add(permission);
					permissionModule.setPermissionList(permissions);
					permissionModuleList.add(permissionModule);

				} else {

					PermissionModuleList permissionModule = getUserProjectFromList(permissionModuleList,
							pagePermission.getModuleId());

					PermissionList permission = new PermissionList();
					permission.setPermissionId(pagePermission.getPermissionId());
					permission.setPermissionName(pagePermission.getPermissionName());
					permission.setDescription(pagePermission.getDescription());
					permission.setMethod(pagePermission.getMethod());
					permission.setUrl(pagePermission.getUrl());

					permissionModule.getPermissionList().add(permission);
				}

			});

		}
		return permissionModuleList;
	}

	private PermissionModuleList getUserProjectFromList(List<PermissionModuleList> permissionModuleList,
			Long moduleId) {
		Optional<PermissionModuleList> permissionModuleOptional = permissionModuleList.stream()
				.filter(permission -> permission.getModuleId().equals(moduleId)).findFirst();
		if (permissionModuleOptional.isPresent()) {
			return permissionModuleOptional.get();
		}
		return null;
	}

	private boolean checkUserExists(List<PermissionModuleList> permissionModuleList, Long moduleId) {
		boolean moduleExists = false;
		moduleExists = permissionModuleList.stream().anyMatch(permission -> permission.getModuleId().equals(moduleId));
		return moduleExists;
	}

	@Override
	public List<String> getUserPermissions(Long userId) {
		// TODO Auto-generated method stub

		List<String> list = permissionRepository.getAllUserPermissions(userId);

		return list;
	}

	@Override
	public PermissionWithSFDetail getUserPermissionsAndSFDetail(Long userId) {

		PermissionWithSFDetail userDeatil = new PermissionWithSFDetail();
		List<String> permissions = this.permissionRepository.getAllUserPermissions(userId);
		IListOfPermissionsAndSFDetail userSfDeatil = permissionRepository.getAllUserPermissionsAndSFDetail(userId,
				IListOfPermissionsAndSFDetail.class);

		userDeatil.setUserId(userSfDeatil.getUserId());
		userDeatil.setUserName(userSfDeatil.getUserName());
		userDeatil.setPermissions(permissions);
		userDeatil.setLevelId(userSfDeatil.getFunctionId());
		userDeatil.setFunctionId(userSfDeatil.getFunctionId());
		userDeatil.setFunctionName(userSfDeatil.getFunctionName());
		userDeatil.setLevelId(userSfDeatil.getLevelId());
		userDeatil.setLevelName(userSfDeatil.getLevelName());
		userDeatil.setPositionTitle(userSfDeatil.getPositionTitle());
		userDeatil.setRegion(userSfDeatil.getRegion());
		userDeatil.setZone(userSfDeatil.getZone());
		userDeatil.setEmployeeGrade(userSfDeatil.getEmployeeGrade());
		userDeatil.setDepartmentName(userSfDeatil.getDepartment());
		userDeatil.setDepartmentId(userSfDeatil.getDepartmentId());
		userDeatil.setEmployeeEdp(userSfDeatil.getEmployeeEdp());
		

		return userDeatil;
	}

//	@Override

//	public void uploadPermissions(MultipartFile file, Long userId) throws IOException {
//		XSSFCell cell;
//		XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
//		XSSFSheet worksheet = workbook.getSheetAt(0);
//		int cells = worksheet.getRow(0).getPhysicalNumberOfCells();
//
//		List<PermissionEntity> entities = new ArrayList<>();
//
//		for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
//			PermissionEntity entity = new PermissionEntity();
//			XSSFRow row = worksheet.getRow(i);
//
//			for (int j = 0; j <= cells; j++) {
//				cell = row.getCell(j);
//
//				switch (j) {
//				case 0:
//					if (!cell.toString().isEmpty() && cell.toString() != "" && cell != null) {
//						entity.setActionName(cell.getStringCellValue());
//					} else {
//						throw new ResourceNotFoundException("ActionName is not present in row  " + (i + 1));
//					}
//					break;
//
//				case 1:
//					if (cell != null) {
//						entity.setDescription(cell.getStringCellValue());
//					} else {
//						entity.setDescription("");
//					}
//					break;
//
//				case 2:
//					if (cell != null) {
//						entity.setMethod(cell.getStringCellValue());
//					} else {
//						entity.setDescription("");
//					}
//
//				case 3:
//					if (cell != null) {
//						entity.setUrl(cell.getStringCellValue());
//					} else {
//						entity.setDescription("");
//					}
//
//				}
//
//			}
//
//			entity.setCreatedBy(userId);
//			entities.add(entity);
//		}
//
//		this.permissionRepository.saveAll(entities);
//	}

	@Override
	public List<IListPermissionDto> findAllList(Class<IListPermissionDto> class1) {
		List<IListPermissionDto> permissionDto = this.permissionRepository
				.findByOrderByIdDesc(IListPermissionDto.class);
		return permissionDto;
	}

	@Override
	public void deleteMultiplePermissionsById(DeleteId ids, Long userId) {

		List<PermissionEntity> entity = permissionRepository.findAllById(ids.getIds());
		for (int i = 0; i < entity.size(); i++) {
			entity.get(i).setUpdatedBy(userId);
		}

		permissionRepository.deleteAllByIdIn(ids.getIds());

	}
}
