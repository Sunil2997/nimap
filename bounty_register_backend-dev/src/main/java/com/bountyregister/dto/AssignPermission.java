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
public class AssignPermission {

	private Long roleId;
	
	@NotEmpty(message = ErrorMessageCode.PERMISSION_ID_REQUIRED + "*" + ErrorMessageKey.PERMISSION_E031304)
	@NotNull(message = ErrorMessageCode.PERMISSION_ID_REQUIRED + "*" + ErrorMessageKey.PERMISSION_E031304)
	private ArrayList<Long> permissionId;	
	
}
