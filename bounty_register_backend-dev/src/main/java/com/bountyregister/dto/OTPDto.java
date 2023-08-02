package com.bountyregister.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.bountyregister.utils.ErrorMessageCode;
import com.bountyregister.utils.ErrorMessageKey;
import com.bountyregister.utils.Validator;

public class OTPDto {

	@NotBlank(message = ErrorMessageCode.EMAIL_REQUIRED + "*" + ErrorMessageKey.USER_E031204)
	@NotEmpty(message = ErrorMessageCode.EMAIL_REQUIRED + "*" + ErrorMessageKey.USER_E031204)
	@NotNull(message = ErrorMessageCode.EMAIL_REQUIRED + "*" + ErrorMessageKey.USER_E031204)
	@Pattern(regexp = Validator.MAIL_PATTERN, message = ErrorMessageCode.INVALID_EMAIL + "*"
			+ ErrorMessageKey.EMAIL_E033701)
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
