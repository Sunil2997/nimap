package com.bountyregister.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bountyregister.dto.CustomerMasterDto;
import com.bountyregister.dto.CustomerPagingResponse;
import com.bountyregister.entities.CustomerMaster;
import com.bountyregister.exceptionHandling.ResourceNotFoundException;
import com.bountyregister.repositories.CustomerMasterRepository;
import com.bountyregister.service.CustomerMasterInterface;

@Service
public class CustomerMasterServiceImpl implements CustomerMasterInterface {

	
	@Autowired
	private CustomerMasterRepository customerMasterRepo;

	@Autowired
	private  ModelMapper mapper;

	@Override
	public boolean addCustomer(CustomerMasterDto userMasterDto) {
		CustomerMaster customerMaster = mapper.map(userMasterDto,CustomerMaster.class );	
		customerMasterRepo.save(customerMaster);
		return customerMaster.getId() != null;
	}

	@Override
	public void updatedCustomer(CustomerMasterDto userMasterDto, long id) {
		CustomerMaster customerMaster = customerMasterRepo.findById(id)
				                       .orElseThrow(()-> new ResourceNotFoundException("record not found with id "+ id));
		
		customerMaster.setName(userMasterDto.getName());
		customerMaster.setEmail(userMasterDto.getEmail());
		customerMaster.setGender(userMasterDto.getGender());
		customerMaster.setPasscode(userMasterDto.getPasscode());
		customerMaster.setPhoneNumber(userMasterDto.getPhoneNumber());
		customerMaster.setWeight(userMasterDto.getWeight());
		CustomerMaster updatedCustomer = customerMasterRepo.save(customerMaster);
		
	}

	@Override
	public void deleteCustomer(Long id) {
		CustomerMaster customerMaster = customerMasterRepo.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("record not found with id "+ id));
		customerMasterRepo.delete(customerMaster);
	}

	@Override
	public void changeStatus(long id) {
		CustomerMaster customerMaster = customerMasterRepo.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("record not found with id "+ id));
		customerMaster.setIsActive(false);
		customerMasterRepo.save(customerMaster);

	}

	@Override
	public CustomerMasterDto getCustomerById(long id) {
		CustomerMaster customerMaster = customerMasterRepo.findById(id)
		        .orElseThrow(()-> new ResourceNotFoundException("record not found with id "+ id));
		return mapToDTO(customerMaster);
	}

//	@Override
//	public List<CustomerMasterDto> getAllCustomer() {
//		List<CustomerMaster> list = customerMasterRepo.findAll();
//	 return list.stream().map((customer)-> mapToDTO(customer)).collect(Collectors.toList());
//		  
//	}
	
	public CustomerPagingResponse getAllCustomers(int pageNo, int pageSize) {

		PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
	 Page<CustomerMaster> page = customerMasterRepo.findAll(pageRequest);
		List<CustomerMaster> contents = page.getContent();
		List<CustomerMasterDto> list = contents.stream().map((content) -> mapToDTO(content)).collect(Collectors.toList());
		
		CustomerPagingResponse customerResponse = new CustomerPagingResponse();
		customerResponse.setCustomersDto(list);
		customerResponse.setPageNo(page.getNumber());
		customerResponse.setPageSize(page.getSize());
		customerResponse.setTotalElements(page.getTotalElements());
		customerResponse.setTotalPages(page.getTotalPages());
		customerResponse.setLast(page.isLast());
		return customerResponse;
	

}
	@Override
	public void reActiveUserById(Long id) {

		customerMasterRepo.activateIsActive(id);

	}
	
	// convert Entity into DTO
	private CustomerMasterDto mapToDTO(CustomerMaster CustomerMaster) {
//		CustomerMasterDto productDto = mapper.map(CustomerMaster, CustomerMasterDto.class);
//		return productDto;
		return mapper.map(CustomerMaster, CustomerMasterDto.class);
	}

	// convert DTO to entity
	private CustomerMaster mapToEntity(CustomerMasterDto customermasterdto) {
		CustomerMaster product = mapper.map(customermasterdto, CustomerMaster.class);
		return product;
	}

}
