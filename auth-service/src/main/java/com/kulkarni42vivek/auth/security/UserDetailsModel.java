package com.kulkarni42vivek.auth.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDetailsModel implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8627532988795104219L;
	
	public String username ;
	public String password;
	public List<SimpleGrantedAuthority> authorities = new ArrayList<>();
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

}
