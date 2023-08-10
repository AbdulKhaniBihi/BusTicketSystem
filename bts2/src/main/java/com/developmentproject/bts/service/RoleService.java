package com.developmentproject.bts.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developmentproject.bts.repository.RoleRepository;
import com.developmentproject.bts.repository.UserRepository;
import com.developmentproject.bts.entity.Role;
import com.developmentproject.bts.entity.User;

@Service
public class RoleService {
	
	private RoleRepository roleRepository;
	private UserRepository userRepository;
	
	@Autowired
	 public RoleService(RoleRepository roleRepository, UserRepository userRepository) {
		super();
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
	}


	public List<Role> findAll() {
	        return roleRepository.findAll();
	    }

	   
	    public Optional<Role> findById(Long id) {
	        return roleRepository.findById(id);
	    }

	    public void deleteById(Long id) {
	        roleRepository.deleteById(id);

	    }
	   //save role.
		public void saveRole(Role role) {
			Role roleName = roleRepository.findByRoleName(role.getRoleName());
					
		
			if(role.getRoleName().isBlank()) {
				throw new IllegalStateException("Feilds cannot be empty");
			}else if(roleName !=null){
				throw new RuntimeException(" Role already exist: ");
			}else {
				
				roleRepository.save(role);
			}
	            
	        }
		//Assign role to user.
		public void assingUserRole(Long userId, Long roleId) {
			User user = userRepository.findById(userId).orElse(null);
			Role role = roleRepository.findById(roleId).orElse(null);
			Set<Role> userRoles = user.getRoles();
			
			userRoles.add(role);
			user.setRoles(userRoles);
			
			userRepository.save(user);
		}
		
		//unassigning user role.
		public void unassignUserRole(Long userId, Long roleId) {
			User user = userRepository.findById(userId).orElse(null);
			Set<Role> userRoles = user.getRoles();

			
			userRoles.removeIf(x -> x.getRoleId() == roleId);
			userRepository.save(user);
		}
		
		
		public Set<Role> getUserRoles(User user){
			return user.getRoles();
		}
		public Set<Role> getUserNotRoles(User user){
			   return roleRepository.getUserNotRoles(user.getUserId());
			}
		

}
