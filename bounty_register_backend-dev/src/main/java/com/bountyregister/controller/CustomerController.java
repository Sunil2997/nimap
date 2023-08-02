package com.bountyregister.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bountyregister.dto.CustomerMasterDto;
import com.bountyregister.dto.CustomerPagingResponse;
import com.bountyregister.dto.ErrorResponseDto;
import com.bountyregister.dto.SuccessResponseDto;
import com.bountyregister.service.CustomerMasterInterface;
import com.bountyregister.utils.ErrorKeyConstant;
import com.bountyregister.utils.ErrorMessageConstant;
import com.bountyregister.utils.SuccessKeyConstant;
import com.bountyregister.utils.SuccessMessageConstant;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerMasterInterface CustomerMasterService;

	@PostMapping
	public ResponseEntity<?> addCustomer(@Valid @RequestBody CustomerMasterDto userMasterDto) {

		boolean customer = CustomerMasterService.addCustomer(userMasterDto);
		if (customer) {

			return new ResponseEntity<>(new SuccessResponseDto(SuccessMessageConstant.USER_ADDED,
					SuccessKeyConstant.USER_M031104, userMasterDto), HttpStatus.CREATED);

		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateCustomer(@Valid @RequestBody CustomerMasterDto userMasterDto,
			@PathVariable("id") long id) {
		CustomerMasterService.updatedCustomer(userMasterDto, id);
		return new ResponseEntity<>("record updated ", HttpStatus.OK);
	}

	@PostMapping("/{id}")
	public ResponseEntity<String> changeStatus(@PathVariable("id") long customerId) {
		CustomerMasterService.changeStatus(customerId);
		return new ResponseEntity<>("status changed successfully", HttpStatus.CREATED);
	}

	
	@PatchMapping("/{id}/activate")
	public ResponseEntity<?> reactivateCustomer(@PathVariable Long id) {

		try {
			CustomerMasterService.reActiveUserById(id);
			return new ResponseEntity<>(
					new SuccessResponseDto(SuccessMessageConstant.USER_REACTIVATED, SuccessKeyConstant.USER_M031105,id),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new ErrorResponseDto(ErrorMessageConstant.INVALID_ID, ErrorKeyConstant.USER_E031107),
					HttpStatus.NOT_FOUND);
		}
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<CustomerMasterDto> getCustomerById(@PathVariable("id") long id) {
		CustomerMasterDto customer = CustomerMasterService.getCustomerById(id);
		return new ResponseEntity<CustomerMasterDto>(customer, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<CustomerPagingResponse> getAllCustomers(
			@RequestParam(value = "page", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize) {
		CustomerPagingResponse customers = CustomerMasterService.getAllCustomers(pageNo, pageSize);
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCustomerById(@PathVariable("id") long id) {
		CustomerMasterService.deleteCustomer(id);
		return new ResponseEntity<>("record delete", HttpStatus.OK);
	}

}
