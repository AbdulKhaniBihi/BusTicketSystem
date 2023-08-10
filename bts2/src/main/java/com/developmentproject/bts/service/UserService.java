package com.developmentproject.bts.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.developmentproject.bts.entity.Role;
import com.developmentproject.bts.entity.User;
import com.developmentproject.bts.repository.RoleRepository;
import com.developmentproject.bts.repository.TicketRepository;
import com.developmentproject.bts.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
	@Autowired
    private final UserRepository userRepository;
	@Autowired
    private final RoleRepository roleRepository;
	
	@Autowired
    private final TicketRepository ticketRepository;
	
	@Autowired
    private final BCryptPasswordEncoder encoder;
	
	public UserService(UserRepository userRepository, TicketRepository ticketRepository, 
			BCryptPasswordEncoder encoder,  RoleRepository roleRepository ) {
        this.userRepository = userRepository;
		this.roleRepository = roleRepository;
        this.ticketRepository = ticketRepository;
        this.encoder = encoder;
    }
	 public List<User> findAll() {
	        return userRepository.findAll();
	    }

	    public User findByEmail(String email) {
	        return userRepository.findByEmail(email);
	    }
	    public Optional<User> findById(Long id) {
	        return userRepository.findById(id);
	    }
	    public void saveUser(User user) {
			User email = userRepository.findByEmail(user.getEmail());
			
			if(user.getFirstName().isBlank() || user.getLastName().isBlank() || user.getEmail().isBlank()
					|| user.getPassword().isBlank()) {
				throw new IllegalStateException("Feilds cannot be empty");
			}else if(email !=null){
				throw new RuntimeException(" Email is taken: ");
			}else {
			
//			user.setPassword(encoder.encode(user.getPassword()));
			user.setActive(1);
			user.setUsername(user.getEmail());
			
			Role roleUser = roleRepository.findByRoleName("USER");
			user.addRole(roleUser);
			userRepository.save(user);
			}
	    

}
	    @Transactional
	    public User updateUser(@RequestBody User user,
	                           @RequestParam(required = false) String password,
	                           @RequestParam(required = false) String username) {
	        User existingUser = userRepository.getReferenceById(user.getUserId());
	        existingUser.setUserId(user.getUserId());
	        existingUser.setFirstName(user.getFirstName());
	        existingUser.setLastName(user.getLastName());
	        existingUser.setUsername(user.getEmail());
	        existingUser.setEmail(user.getEmail());
	        return userRepository.save(user);
	    }
	   

	    
	    



	    public void deleteUserById(Long id) {
	        if (getUserById(id) != null) {
	            userRepository.deleteById(id);
	        }
	    }

	    public Optional<User> getUserById(Long id) {
	    	Optional<User> userId = userRepository.findById(id);
	    	if(userId == null) {
	    		throw new IllegalStateException(
	  					userId +	 
	  				"  Does not exist."); 
	    	}else
	        return userRepository.findById(id);
	    }
	  public User findUserById(Long userId) {
			
			return userRepository.getReferenceById(userId);
		}
	       
	    }
	    
