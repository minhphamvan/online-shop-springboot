package com.shopspringboot.modelDTO;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;

@Data
public class UserPrincipal extends User implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String image;
	private String name;
	private String username;
	
//	private String address;
//	private String email;
//	private String phone;
//
//	private String password;
//	private String role;
//	private Boolean active;

	public UserPrincipal(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

}
