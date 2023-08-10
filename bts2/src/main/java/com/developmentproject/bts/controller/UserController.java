package com.developmentproject.bts.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.developmentproject.bts.entity.User;
import com.developmentproject.bts.service.UserService;


@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping(value = "users")
	public ModelAndView allUser() {
	    ModelAndView mav = new ModelAndView("Users/user"); 
	    mav.addObject("user", userService.findAll());
	    return mav;
	}


	@GetMapping(value = "showusers")
	public ModelAndView showUsers() {
		ModelAndView mav = new ModelAndView("Users/new_user");
		User user = new User();
		mav.addObject("users",user);
		
		return mav;
	
	}
	@RequestMapping(value = "adduser", method = RequestMethod.POST)
	public String saveUser(@Valid @ModelAttribute("users") User user, BindingResult bindingResult, Model model) {
			  userService.saveUser(user);
		
		  return "redirect:/user/users";
		
	}
	@GetMapping("edituser/{id}")
	public ModelAndView editBusForm(@PathVariable("id") Long userId, Model model) {
		User user = userService.findUserById(userId);
				
	ModelAndView mav = new ModelAndView("Users/update_user");
	model.addAttribute("user", user);
	
	return mav;
		
	}
	@RequestMapping(value = "updateuser/{id}", method = { RequestMethod.GET, RequestMethod.PUT })
	public RedirectView updateUser(User user, @RequestParam(required = false) String password,
	        @RequestParam(required = false) String username) {
	    userService.updateUser(user, password, username);
	    RedirectView redirectView = new RedirectView("/user/users", true);
	    return redirectView;
	}
	
	
	@GetMapping("users/{userId}")
	public String deleteUser(@PathVariable Long userId) {
		userService.deleteUserById(userId);
		
		return "redirect:/user/users";
	}
	
}

	
	


