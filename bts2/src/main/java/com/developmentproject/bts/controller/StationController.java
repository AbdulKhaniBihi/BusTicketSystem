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
import org.springframework.web.servlet.ModelAndView;

import com.developmentproject.bts.entity.Station;
import com.developmentproject.bts.service.StationService;

@Controller
@RequestMapping("station")
public class StationController {
	@Autowired
	private final StationService stationService;
	
	public StationController(StationService stationService) {
		this.stationService = stationService;
	}
	
	@RequestMapping(value = "stations", method = RequestMethod.GET)
	public ModelAndView allStations() {
		ModelAndView mav = new ModelAndView("Station/station");
        mav.addObject("station", stationService.getAllStations());
        return mav;
	}
	@RequestMapping(value = "showstations", method = RequestMethod.GET)
 	public ModelAndView showStations() {
 		ModelAndView mav = new ModelAndView("Station/new_station");
 		Station station = new Station();
 		mav.addObject("station",station);
 		return mav;
 	}
	@RequestMapping(value = "addstation", method = RequestMethod.POST)
		public String saveStation(@Valid @ModelAttribute("station") Station station,BindingResult bindingResult, Model model) {
			 boolean thereAreErrors = bindingResult.hasErrors(); 
		  if (thereAreErrors) { 
			  model.addAttribute("station",station);
		  return "Station/new_station"; 
		  } else
		 
			  stationService.addStation(station);
		
			 return "redirect:stations";	
		}
	@GetMapping("editStation/{id}")
		public ModelAndView editStationForm(@PathVariable("id") Long stationId, Model model) {
		Station station = this.stationService.getStationById(stationId);
		ModelAndView mav = new ModelAndView("Station/update-station");
		model.addAttribute("station", station);
		
		return mav;
			
		}
	@RequestMapping(value ="updatestation/{id}", method= {RequestMethod.PUT, RequestMethod.GET})
		public String updateStation(Station station) {
		stationService.updateStation(station);	
			
			return "redirect:/station/stations";
			
		}
	@GetMapping("station/{stationId}")
		public String deleteStation(@PathVariable Long stationId) {
		stationService.deleteStationById(stationId);
			
			return "redirect:/station";
		}

}
