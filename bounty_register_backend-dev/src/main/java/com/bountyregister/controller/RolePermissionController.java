package com.bountyregister.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bountyregister.dto.ErrorResponseDto;
import com.bountyregister.dto.ListResponseDto;
import com.bountyregister.dto.PaginationResponse;
import com.bountyregister.dto.SuccessResponseDto;
import com.bountyregister.iListDto.IListRolePermissions;
import com.bountyregister.service.RolePermissionInterface;
import com.bountyregister.utils.ApiUrls;
import com.bountyregister.utils.Constant;
import com.bountyregister.utils.ErrorMessageCode;
import com.bountyregister.utils.ErrorMessageKey;
import com.bountyregister.utils.SuccessMessageCode;
import com.bountyregister.utils.SuccessMessageKey;

@RestController
@RequestMapping(ApiUrls.ROLE_PERMISSION)
public class RolePermissionController {

	@Autowired
	private RolePermissionInterface rolePermissionInterface;

	@PreAuthorize("hasRole('RolePermission_Details')")
	@GetMapping("{id}")
	public ResponseEntity<?> getPermissionByUserId(@PathVariable("id") Long id) {
		try {

			ArrayList<String> user = this.rolePermissionInterface.getPermissionByUserId(id);

			return new ResponseEntity<>(new SuccessResponseDto(SuccessMessageCode.FETCH_USER_ROLE_PERMISSION,
					SuccessMessageKey.ROLE_PERMISSION_M032001, user), HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(new ErrorResponseDto(ErrorMessageCode.USER_ROLE_PERMISSION_NOT_FOUND,
					ErrorMessageKey.ROLE_PERMISSION_E032002), HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasRole('RolePermission_List')")
	@GetMapping()
	public ResponseEntity<?> getAllRolePermissions(@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = Constant.DEFAULT_PAGENUMBER, value = Constant.PAGENUMBER) String pageNo,
			@RequestParam(defaultValue = Constant.DEFAULT_PAGESIZE, value = Constant.PAGESIZE) String pageSize) {

		Page<IListRolePermissions> rolePermission = rolePermissionInterface.getAllRolePermissions(search, pageNo,
				pageSize);
		PaginationResponse paginationResponse = new PaginationResponse();
		paginationResponse.setPageNumber(rolePermission.getNumber()+1);
		paginationResponse.setPageSize(rolePermission.getSize());
		paginationResponse.setTotal(rolePermission.getTotalElements());

		return new ResponseEntity<>(new ListResponseDto(rolePermission.getContent(), paginationResponse),
				HttpStatus.OK);

	}
}
