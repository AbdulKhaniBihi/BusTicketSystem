package com.developmentproject.bts.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.developmentproject.bts.entity.User;
import com.developmentproject.bts.repository.RoleRepository;
import com.developmentproject.bts.repository.UserRepository;

@Service
public class UserPrincipalDetailsService implements UserDetailsService{
	private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Autowired
    public UserPrincipalDetailsService(UserRepository userRepository,
    		RoleRepository roleRepository) {
        this.userRepository = userRepository;
    }
    
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
    	
    return this.userRepository.findByUsername(username);
    }
    }
    
    


