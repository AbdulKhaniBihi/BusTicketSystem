package com.developmentproject.bts.controller;

import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;

import com.developmentproject.bts.entity.BusStation;
import com.developmentproject.bts.entity.Station;
import com.developmentproject.bts.service.BusStationService;
import com.developmentproject.bts.service.StationService;

@Controller
@RequestMapping("busstation")
public class BusStationController {
		
		private final StationService stationService;
		
		private final BusStationService busStationService;
		
		@Autowired
		public BusStationController(StationService stationService, BusStationService busStationService) {
	        this.stationService = stationService;
	        this.busStationService = busStationService;
	    }
		
		    
	    @RequestMapping(value = "BusStations", method = RequestMethod.GET)
	    public ModelAndView allBusStations() {
	    	
			ModelAndView mav = new ModelAndView("BusStation/busStation");
			
	        mav.addObject("busStation", busStationService.getAllBusStation());
	        return mav;
	    }
	    
		
	 	@RequestMapping(value = "showbusStations", method = RequestMethod.GET)
	 	public ModelAndView showBuses() {
	 		List<Station> station = stationService.getAllStations();
	 		ModelAndView mav = new ModelAndView("BusStation/new_busStation");
	 		BusStation busStation = new BusStation();
	 		mav.addObject("stationList",station); 
	 		mav.addObject("busStation",busStation);
	 		
	 		return mav;
	 	}
	 	
	 	@RequestMapping(value = "saveBusStation", method = RequestMethod.POST)
		  public String  saveBusStation(@Valid @ModelAttribute("busStation") BusStation busStation , BindingResult bindingResult, Model model) {
	 		boolean thereAreErrors = bindingResult.hasErrors(); 
			  if (thereAreErrors) { 
				  model.addAttribute("busStation",busStation);
			  return "BusStation/new_busStation"; 
			  } else
	 		 
	 		 busStationService.addBusStation(busStation);
			
			  return "redirect:list";	
			  
		  }

	 	
	 	 @GetMapping("deleteBusStation/{busStationId}")
			public String deleteBusStation(@PathVariable ("busStationId") Long busStationId, Model model) {
				busStationService.deleteBusStation(busStationId);
			
				model.addAttribute("busStation", this.allBusStations()); 
				return "redirect:/list";
				
			}

}
