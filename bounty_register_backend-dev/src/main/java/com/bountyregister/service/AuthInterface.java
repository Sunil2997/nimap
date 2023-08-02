package com.bountyregister.service;

import com.bountyregister.dto.ForgotPasswordConfirmDto;
import com.bountyregister.dto.OTPDto;
import com.bountyregister.dto.UserDto;

public interface AuthInterface {
	
//	public UserEntity saveNewEnrollEmployee(String email) throws HttpClientErrorException, Exception;
	
//	public UserDto registerUser(UserDto userDto) throws Exception;

	Boolean comparePassword(String password, String hashPassword);

//	UserDetails loadUserByUsername(String email)
//			throws UsernameNotFoundException, JsonMappingException, JsonProcessingException, Exception;

//	ArrayList<String> getPermissionByUser(Long userId) throws Exception;

//	ArrayList<String> getUserPermission(Long userId) throws IOException;

	Boolean updateUserWithPassword(ForgotPasswordConfirmDto payload) throws Exception;

//	void generateOtpAndSendEmail(OTPDto otpDto, Long userId, String emailTemplate) throws Exception;

//	public void saveGplEmployeeDetail(UserEntity userEntity) throws HttpClientErrorException, Exception;

}
