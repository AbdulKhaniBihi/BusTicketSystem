package com.developmentproject.bts.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.developmentproject.bts.entity.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Role findByRoleName(String roleName);
	
	@Query(
	        value = "SELECT * FROM role WHERE id NOT IN (SELECT role_id FROM user_role WHERE user_id = ?1)", 
	        nativeQuery = true
	)
	Set<Role> getUserNotRoles(Long userId);

}
