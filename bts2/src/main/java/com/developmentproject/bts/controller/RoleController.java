package com.developmentproject.bts.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.developmentproject.bts.entity.Role;
import com.developmentproject.bts.entity.Bus;
import com.developmentproject.bts.entity.User;
import com.developmentproject.bts.service.BusService;
import com.developmentproject.bts.service.RoleService;



@RestController
@RequestMapping(name = "roles")
public class RoleController {
	private final RoleService roleService;
	@Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
	@RequestMapping(value = "roles", method = RequestMethod.GET)
    public ModelAndView allRoles() {
		ModelAndView mav = new ModelAndView("Users/role");
        mav.addObject("roles", roleService.findAll());
        return mav;
    }
	@RequestMapping(value = "showroles", method = RequestMethod.GET)
	public ModelAndView showRoles() {
		ModelAndView mav = new ModelAndView("Users/new_role");
		Role roles = new Role();
		mav.addObject("role",roles);
		
		return mav;
	
	}
	@GetMapping(value = "showrole")
	public ModelAndView showRoleForm() {
		ModelAndView mav = new ModelAndView("Users/new_role");
		Role role = new Role();
		mav.addObject("role",role);
		
		return mav;
	
	}
	@RequestMapping(value = "addrole", method = RequestMethod.POST)
	public RedirectView saveRole(@ModelAttribute("role") Role role) {
		roleService.saveRole(role);
		
		RedirectView redirectView = new RedirectView("role", true);
		return redirectView;		
		
	}
	@RequestMapping(value = "role", method = RequestMethod.GET, params = {"roleId"})
    public String getRoleEdit(@RequestParam Long roleId, Model model) {
        model.addAttribute("role", roleService.findById(roleId));
        		
        return "role";
    }
	
	@GetMapping("editRole/{id}")
	public ModelAndView editRoleForm(@PathVariable("id") Long roleId, Model model) {
		Optional<Role> role = this.roleService.findById(roleId);
		
	ModelAndView mav = new ModelAndView("Users/update-role");
	model.addAttribute("role", role);
	
	return mav;
		
	}
	@GetMapping("role/{roleId}")
	public String deleteRole(@PathVariable Long roleId) {
		roleService.deleteById(roleId);
		return "redirect:/role";
	}
	@RequestMapping("role/assign/{userId}/{roleId}")
	public String assignRole(@PathVariable Long userId, 
	                         @PathVariable Long roleId){
		roleService.assingUserRole(userId, roleId);
	    
	    return "redirect:/user/Edit/"+userId;
	}
	@RequestMapping("role/unassign/{userId}/{roleId}")
	public String unassignRole(@PathVariable Long userId,
	                           @PathVariable Long roleId){
		roleService.unassignUserRole(userId, roleId);
	    
	    return "redirect:/user/Edit/"+userId;
	}
	

}
