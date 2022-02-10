package com.shopspringboot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopspringboot.dao.UserDao;
import com.shopspringboot.modelDTO.UserDTO;
import com.shopspringboot.modelDTO.UserPrincipal;

@Service
@Transactional
public class UserLoginServiceImpl implements UserDetailsService {

	@Autowired
	UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.shopspringboot.entity.User user = userDao.getUserByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("No user");
		}

		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.getRole()));

		UserPrincipal userPrincipal = new UserPrincipal(user.getUsername(), user.getPassword(), user.getActive(), true,
				true, true, authorities);
		
		userPrincipal.setId(user.getId());
		userPrincipal.setImage(user.getImage());
		userPrincipal.setName(user.getName());
		userPrincipal.setUsername(user.getUsername());
		
//		userPrincipal.setAddress(user.getAddress());
//		userPrincipal.setEmail(user.getEmail());
//		userPrincipal.setPhone(user.getPhone());
//		
//		userPrincipal.setPassword(user.getPassword());
//		userPrincipal.setRole(user.getRole());
//		userPrincipal.setActive(user.getActive());
		
		return userPrincipal;
	}
	
	public void updateUserLogin(UserDTO userDTO) { // Không GeneratePassword() nữa
		// Lấy người dùng đang đăng nhập hiện tại
		UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		
		userPrincipal.setId(userDTO.getId());
		userPrincipal.setImage(userDTO.getImage());
		userPrincipal.setName(userDTO.getName());
		userPrincipal.setUsername(userDTO.getUsername());
		
//		userPrincipal.setAddress(userDTO.getAddress());
//		userPrincipal.setEmail(userDTO.getEmail());
//		userPrincipal.setPhone(userDTO.getPhone());
//		
//		userPrincipal.setPassword(userDTO.getPassword());
//		userPrincipal.setRole(userDTO.getRole());
//		userPrincipal.setActive(userDTO.getActive());
	}
}
