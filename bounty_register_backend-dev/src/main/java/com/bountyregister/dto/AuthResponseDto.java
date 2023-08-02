package com.bountyregister.dto;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

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
public class AuthResponseDto {

	private String jwtToken;

	private String refreshToken;

	private ArrayList<String> permission;

	private String email;

	private String name;

	private Long id;

	private List<String> roles;

	

	@Override
	public String toString() {

// Create a new JSON object
		JSONObject jsonObject = new JSONObject();

// Add the key-value pairs to the JSON object
		jsonObject.put("jwtToken", jwtToken);
		jsonObject.put("refreshToken", refreshToken);
		jsonObject.put("permission", permission);
		jsonObject.put("email", email);
		jsonObject.put("name", name);
		jsonObject.put("id", id);
//		jsonObject.put("careerAspiration", careerAspiration);
		jsonObject.put("roles", roles);

// Convert the JSON object to a string
		return jsonObject.toString();
	}

}
