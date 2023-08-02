package com.bountyregister.dto;

import java.util.ArrayList;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.bountyregister.utils.ErrorMessageCode;
import com.bountyregister.utils.ErrorMessageKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssignRole {

	
	private Long userId;

	@NotEmpty(message = ErrorMessageCode.ROLE_ID_REQUIRED + "*" + ErrorMessageKey.ROLE_E031204)
	@NotNull(message = ErrorMessageCode.ROLE_ID_REQUIRED + "*" + ErrorMessageKey.ROLE_E031204)
	private  ArrayList<Long> roleId;	
	
}
