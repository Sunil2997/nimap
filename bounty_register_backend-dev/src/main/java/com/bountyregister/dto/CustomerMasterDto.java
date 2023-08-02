package com.bountyregister.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.bountyregister.entities.GenderEnum;

import lombok.Data;

@Data
public class CustomerMasterDto {
	
	
	@NotEmpty
	@Size(min = 2, message = "user name should have at least 2 characters")
	private String name;

	@NotBlank(message = "email is Required*emailNameRequired")
	@NotEmpty(message = "email is Required*emailNameRequired")
	@NotNull(message = "email is Required*emailRequired")
	@Email
	private String email;

	@NotNull
	private GenderEnum gender;
	
	@NotEmpty
	@Size(min = 4, message = "password should have at least 4 characters")
	private String passcode;
	
	@NotNull
    @Size(min = 10, max = 10, message = "phoneNumber must be exactly 10 characters")
	private String phoneNumber;
	
	@NotNull
    @Size(min = 0, max = Integer.MAX_VALUE, message = "weight should be in kg")
	private String weight;

//	private List<CustomerGym> customerGym;
//
//	private List<CustomerMachineSlot> customerMachineSlot;

}
