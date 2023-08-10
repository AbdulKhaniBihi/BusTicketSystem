package com.developmentproject.bts.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.developmentproject.bts.entity.User;
import com.developmentproject.bts.entity.Role;

public class UserPrincipal implements UserDetails {
	private static final long serialVersionUID = 146232847499014513L;
	private User user;
	@Autowired
    public UserPrincipal(User user){
        this.user = user;

}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role>roles = user.getRoles(); 
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;

	}
	@Override
	public String getPassword() {
		
		 return this.user.getPassword();
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.user.getEmail();
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
		return this.user.getActive() == 1;
	}
	}
