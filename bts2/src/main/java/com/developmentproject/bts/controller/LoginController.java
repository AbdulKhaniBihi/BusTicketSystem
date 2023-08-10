package com.developmentproject.bts.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class LoginController {
	
	@GetMapping("home")
	public ModelAndView index(){
		 ModelAndView mav = new ModelAndView("index");
		  return mav;
   }
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String loginPage(Model model, Authentication authentication) {
	    if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
	    	return "redirect:/bus/buses";
	    } else if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("USER"))) {
	    	return "redirect:/";
	    } else {
	    	return "redirect:/";
	    }
	}
	@RequestMapping("login") 
	  public ModelAndView loginPage() {
		 
		  ModelAndView mav = new ModelAndView("login");
		  return mav;
		
	  }	

}
