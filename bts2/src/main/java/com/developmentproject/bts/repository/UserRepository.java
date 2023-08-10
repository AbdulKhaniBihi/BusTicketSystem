package com.developmentproject.bts.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.developmentproject.bts.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	List<User> findAll();
	
	User findByEmail(String email);
	 
    Optional<User> findById(Long id);
    
    void deleteById(Long id);
    
	User findByUsername(String username);
	

}
