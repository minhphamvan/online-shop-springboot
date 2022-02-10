package com.shopspringboot.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private int id;
	private String image;
	private String name;
	private String address;
	private String email;
	private String phone;
	
	private String username;
	private String password;
	private String role;
	private Boolean active;
}
