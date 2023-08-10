package com.developmentproject.bts.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.developmentproject.bts.entity.Bus;
import com.developmentproject.bts.service.BusService;

@Controller
@RequestMapping("bus")
public class BusController {

    private final BusService busService;

    @Autowired
    public BusController(BusService busService) {
        this.busService = busService;
    }
    
	//gets buses
    @RequestMapping(value = "buses", method = RequestMethod.GET)
    public ModelAndView allBuses() {
		ModelAndView mav = new ModelAndView("Bus/Bus");
        mav.addObject("bus", busService.getAllBuses());
        return mav;
    }
    
	// Show bus form before creating one.
	@RequestMapping(value = "showbuses", method = RequestMethod.GET)
	public ModelAndView showBuses() {
		ModelAndView mav = new ModelAndView("Bus/new_bus");
		Bus bus = new Bus();
		mav.addObject("bus", bus);
		return mav;
	}
	
	// Add bus
	@RequestMapping(value = "addbus", method = RequestMethod.POST)
	public String saveBus(@Valid @ModelAttribute("bus") Bus bus , BindingResult bindingResult, Model model) {
		boolean thereAreErrors = bindingResult.hasErrors(); 
		if (thereAreErrors) { 
			model.addAttribute("bus", bus);
			return "Bus/new_bus"; 
		} else {
			busService.addBus(bus);
			return "redirect:buses";	
		}
	}
    
	@RequestMapping(value = "bus", method = RequestMethod.GET, params = {"busId"})
    public String getEditBus(@RequestBody Long busId, Model model) {
        model.addAttribute("bus", busService.getBusById(busId));
        return "buses";
    }
	
	// Get bus form for editing it
	@GetMapping("editBus/{id}")
	public ModelAndView editBusForm(@PathVariable("id") Long busId, Model model) {
		Bus bus = this.busService.findBusById(busId);
		ModelAndView mav = new ModelAndView("Bus/update_bus");
		model.addAttribute("bus", bus);
		return mav;
	}
	
	// Update bus
	@RequestMapping(value = "updatebus/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
	public RedirectView updateBus(Bus bus) {
		busService.updateBus(bus);
		RedirectView redirectView = new RedirectView("buses", true);
		return redirectView;	
	}
	
	// Handler method to handle delete bus request
	@GetMapping("buses/{busId}")
	public String deleteBus(@PathVariable Long busId) {
		busService.deleteBusById(busId);
		return "redirect:/buses";
	}
}

