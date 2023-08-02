package com.bountyregister.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bountyregister.dto.AssignRole;
import com.bountyregister.dto.ErrorResponseDto;
import com.bountyregister.dto.ListResponseDto;
import com.bountyregister.dto.PaginationResponse;
import com.bountyregister.dto.SuccessResponseDto;
import com.bountyregister.iListDto.IListUserRoles;
import com.bountyregister.service.UserRoleInterface;
import com.bountyregister.utils.ApiUrls;
import com.bountyregister.utils.Constant;
import com.bountyregister.utils.ErrorMessageKey;
import com.bountyregister.utils.SuccessMessageCode;
import com.bountyregister.utils.SuccessMessageKey;

@RestController
@RequestMapping(ApiUrls.USER_ROLE)
public class UserRoleController {

	@Autowired
	private UserRoleInterface userRoleInterface;

//	@PreAuthorize("hasRole('UserRole_Add')")
//	@PostMapping
//	public ResponseEntity<?> addUserRole(@Valid @RequestBody AssignRole assignRole) {
//		try {
//			userRoleInterface.add(assignRole);
//			return new ResponseEntity<>(new SuccessResponseDto(SuccessMessageCode.USER_ROLE_ADDED,
//					SuccessMessageKey.USER_ROLE_M031904, assignRole), HttpStatus.OK);
//		} catch (Exception e) {
//
//			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), ErrorMessageKey.USER_ROLE_E031902),
//					HttpStatus.BAD_REQUEST);
//		}
//
//	}

	@PreAuthorize("hasRole('UserRole_List')")
	@GetMapping()
	public ResponseEntity<?> getAllUserRole(@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = Constant.DEFAULT_PAGENUMBER, value = Constant.PAGENUMBER) String pageNo,
			@RequestParam(defaultValue = Constant.DEFAULT_PAGESIZE, value = Constant.PAGESIZE) String pageSize) {

		Page<IListUserRoles> userRole = userRoleInterface.getAllUserRole(search, pageNo, pageSize);
		PaginationResponse paginationResponse = new PaginationResponse();
		paginationResponse.setPageNumber(userRole.getNumber());
		paginationResponse.setPageSize(userRole.getSize());
		paginationResponse.setTotal(userRole.getTotalElements());

		return new ResponseEntity<>(new ListResponseDto(userRole.getContent(), paginationResponse), HttpStatus.OK);

	}

}
