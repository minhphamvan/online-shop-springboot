package com.shopspringboot.dao;

import java.util.List;
import java.util.Map;

import com.shopspringboot.entity.User;

public interface UserDao {
	public void addUser(User user);
	
	public void updateUser(User user);
	
	public void updateUserNoChangePassword(User user);
	
	public void deleteUser(User user);
	
	public User getUserById(int id);
	
	public List<User> getUserByName(String name);
	
	public User getUserByUsername(String username);
	
	public List<User> getAllUser();
	
	public long countUser();
	
	public Map<String, Long> countByAddress();
}
