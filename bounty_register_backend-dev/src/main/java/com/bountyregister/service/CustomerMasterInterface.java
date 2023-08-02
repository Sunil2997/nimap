package com.bountyregister.service;

import java.util.List;



import com.bountyregister.dto.CustomerMasterDto;
import com.bountyregister.dto.CustomerPagingResponse;

public interface CustomerMasterInterface {
	
	//create new  customer
	public boolean addCustomer(CustomerMasterDto userMasterDto); 
	
	//update  customer by id
	public void updatedCustomer(CustomerMasterDto userMasterDto,long id); 
	
	//delete customer Soft delete and hard delete by id
	public void deleteCustomer(Long userId);
	
	//soft delete
	public void changeStatus(long id);
	
	//get customer by id
	public CustomerMasterDto getCustomerById(long id);
	
	//get all  customer 
	//public List<CustomerMasterDto> getAllCustomer();
	public CustomerPagingResponse getAllCustomers(int pageNo, int pageSize) ;

	void reActiveUserById(Long id);

	//	List<IListUserDto> exportUserToExcel(HttpServletResponse response) throws IOException;
	
	
	

}
