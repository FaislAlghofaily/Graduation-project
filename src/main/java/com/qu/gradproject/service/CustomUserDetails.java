package com.qu.gradproject.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.qu.gradproject.entity.UserEntity;
 
public class CustomUserDetails implements UserDetails {
	
	private UserEntity userEntity;

	public CustomUserDetails(UserEntity user) {
        this.userEntity = user;
    }
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		
			auths.add(new SimpleGrantedAuthority(userEntity.getRole()));
		return auths;
	}

	@Override
	public String getPassword() {
		
		return userEntity.getPassword();
		
	}

	@Override
	public String getUsername() {
		return userEntity.getEmail();
		
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
}

