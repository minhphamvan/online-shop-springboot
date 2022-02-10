package com.shopspringboot.service;

import java.util.List;
import java.util.Map;

import com.shopspringboot.modelDTO.UserDTO;

public interface UserService {
	public void addUser(UserDTO userDTO);
	
	public void updateUser(UserDTO userDTO);
	
	public void updateUserNoChangePassword(UserDTO userDTO);
	
	public void deleteUser(int id);
	
	public UserDTO getUserById(int id);
	
	public List<UserDTO> getUserByName(String name);
	
	public List<UserDTO> getAllUser();
	
	public long countUser();
	
	public Map<String, Long> countByAddress();
}
