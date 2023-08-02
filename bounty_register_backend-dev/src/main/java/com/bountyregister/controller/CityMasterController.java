package com.bountyregister.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bountyregister.dto.CityMasterDto;
import com.bountyregister.dto.DeleteId;
import com.bountyregister.dto.ErrorResponseDto;
import com.bountyregister.dto.ListResponseDto;
import com.bountyregister.dto.PaginationResponse;
import com.bountyregister.dto.SuccessResponseDto;
import com.bountyregister.iListDto.IListCityMaster;
import com.bountyregister.service.CityMasterInterface;
import com.bountyregister.utils.ApiUrls;
import com.bountyregister.utils.Constant;
import com.bountyregister.utils.ErrorMessageKey;
import com.bountyregister.utils.GlobalFunctions;
import com.bountyregister.utils.SuccessMessageCode;
import com.bountyregister.utils.SuccessMessageKey;

@RestController
@RequestMapping(ApiUrls.CITY)
public class CityMasterController {

	@Autowired
	private CityMasterInterface cityMasterInterface;

	@PreAuthorize("hasRole('City_Add')")
	@PostMapping
	public ResponseEntity<?> addCity(@Valid @RequestBody CityMasterDto cityMasterDto,
			@RequestAttribute(GlobalFunctions.CUSTUM_ATTRIBUTE_USER_ID) Long userId) {
		try {
			cityMasterInterface.addCity(cityMasterDto, userId);
			return new ResponseEntity<>(new SuccessResponseDto(SuccessMessageCode.CITY_ADDED_SUCCESSFULLY,
					SuccessMessageKey.CITY_M032601, cityMasterDto), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), ErrorMessageKey.CITY_E032601),
					HttpStatus.BAD_REQUEST);
		}

	}

	@PreAuthorize("hasRole('City_Update')")
	@PutMapping("/{id}")
	public ResponseEntity<?> editCity(@PathVariable("id") Long id, @Valid @RequestBody CityMasterDto cityMasterDto,
			@RequestAttribute(GlobalFunctions.CUSTUM_ATTRIBUTE_USER_ID) Long userId) {
		try {
			cityMasterInterface.editCity(id, cityMasterDto, userId);
			return new ResponseEntity<>(new SuccessResponseDto(SuccessMessageCode.CITY_UPDATED_SUCCESSFULLY,
					SuccessMessageKey.CITY_M032602, cityMasterDto), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), ErrorMessageKey.CITY_E032601),
					HttpStatus.BAD_REQUEST);
		}

	}

	@PreAuthorize("hasRole('City_Delete')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCity(@PathVariable("id") Long id) {
		try {
			cityMasterInterface.deleteCity(id);
			return new ResponseEntity<>(new SuccessResponseDto(SuccessMessageCode.CITY_DELETED_SUCCESSFULLY,
					SuccessMessageKey.CITY_M032603), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), ErrorMessageKey.CITY_E032601),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('City_List')")
	@GetMapping
	public ResponseEntity<?> getAllCities(@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "", value = Constant.PAGENUMBER) String pageNo,
			@RequestParam(defaultValue = "", value = Constant.PAGESIZE) String pageSize) throws Exception {
		Page<IListCityMaster> page = this.cityMasterInterface.getAllCities(search, pageNo, pageSize);

		PaginationResponse paginationResponse = new PaginationResponse();

		paginationResponse.setPageSize(page.getSize());
		paginationResponse.setTotal(page.getTotalElements());
		paginationResponse.setPageNumber(page.getNumber() + 1);

		return new ResponseEntity<>(new SuccessResponseDto(SuccessMessageCode.CITY_GET_SUCCESSFULLY,
				SuccessMessageKey.CITY_M032604, new ListResponseDto(page.getContent(), paginationResponse)),
				HttpStatus.OK);
	}

	@PreAuthorize("hasRole('City_Delete')")
	@DeleteMapping()
	public ResponseEntity<?> deleteAllCities(@RequestBody DeleteId ids,
			@RequestAttribute(GlobalFunctions.CUSTUM_ATTRIBUTE_USER_ID) Long userId) {
		try {
			this.cityMasterInterface.deleteMultipleCities(ids, userId);

			return new ResponseEntity<>(new SuccessResponseDto(SuccessMessageCode.CITY_DELETED_SUCCESSFULLY,
					SuccessMessageKey.CITY_M032603), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), ErrorMessageKey.CITY_E032601),
					HttpStatus.BAD_REQUEST);
		}

	}

}
