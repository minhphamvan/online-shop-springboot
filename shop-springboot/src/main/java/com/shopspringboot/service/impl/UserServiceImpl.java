package com.shopspringboot.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopspringboot.dao.UserDao;
import com.shopspringboot.entity.User;
import com.shopspringboot.modelDTO.UserDTO;
import com.shopspringboot.service.UserService;
import com.shopspringboot.utils.PasswordGenerator;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public void addUser(UserDTO userDTO) {
		userDao.addUser(convertToUser(userDTO));
	}

	@Override
	public void updateUser(UserDTO userDTO) {
		userDao.updateUser(convertToUser(userDTO));
	}
	
	@Override
	public void updateUserNoChangePassword(UserDTO userDTO) {
		User user = new User();

		user.setId(userDTO.getId());
		user.setImage(userDTO.getImage());
		user.setName(userDTO.getName());
		user.setAddress(userDTO.getAddress());
		user.setEmail(userDTO.getEmail());
		user.setPhone(userDTO.getPhone());

		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		user.setRole(userDTO.getRole());
		user.setActive(userDTO.getActive());
		
		userDao.updateUserNoChangePassword(user);
	}

	@Override
	public void deleteUser(int id) {
		userDao.deleteUser((userDao.getUserById(id)));
	}

	@Override
	public UserDTO getUserById(int id) {
		return convertToUserDTO(userDao.getUserById(id));
	}

	@Override
	public List<UserDTO> getUserByName(String name) {
		List<User> users = userDao.getUserByName(name);
		List<UserDTO> userDTOs = new ArrayList<>();

		for (User u : users) {
			userDTOs.add(convertToUserDTO(u));
		}

		return userDTOs;
	}

	@Override
	public List<UserDTO> getAllUser() {
		List<User> users = userDao.getAllUser();
		List<UserDTO> userDTOs = new ArrayList<>();

		for (User u : users) {
			userDTOs.add(convertToUserDTO(u));
		}

		return userDTOs;
	}
	
	@Override
	public long countUser() {	
		return userDao.countUser();
	}
	
	@Override
	public Map<String, Long> countByAddress() {
		return userDao.countByAddress();
	}

	// Convert User - UserDTO
	private UserDTO convertToUserDTO(User user) {
		UserDTO userDTO = new UserDTO();

		userDTO.setId(user.getId());
		userDTO.setImage(user.getImage());
		userDTO.setName(user.getName());
		userDTO.setAddress(user.getAddress());
		userDTO.setEmail(user.getEmail());
		userDTO.setPhone(user.getPhone());

		userDTO.setUsername(user.getUsername());
		userDTO.setPassword(user.getPassword());
		userDTO.setRole(user.getRole());
		userDTO.setActive(user.getActive());

		return userDTO;
	}

	private User convertToUser(UserDTO userDTO) {
		User user = new User();

		user.setId(userDTO.getId());
		user.setImage(userDTO.getImage());
		user.setName(userDTO.getName());
		user.setAddress(userDTO.getAddress());
		user.setEmail(userDTO.getEmail());
		user.setPhone(userDTO.getPhone());

		user.setUsername(userDTO.getUsername());
		user.setPassword(PasswordGenerator.getHashString(userDTO.getPassword()));
		user.setRole(userDTO.getRole());
		user.setActive(userDTO.getActive());

		return user;
	}

	

	
}
