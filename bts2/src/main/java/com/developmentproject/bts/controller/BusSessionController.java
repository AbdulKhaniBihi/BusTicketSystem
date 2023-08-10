package com.developmentproject.bts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.developmentproject.bts.entity.Bus;
import com.developmentproject.bts.entity.BusSession;
import com.developmentproject.bts.entity.BusStation;
import com.developmentproject.bts.service.BusService;
import com.developmentproject.bts.service.BusSessionService;
import com.developmentproject.bts.service.BusStationService;

@RestController
@RequestMapping("session")
public class BusSessionController {
	
	private final BusSessionService busSessionService;
	private final BusService busService;
	private final BusStationService busStationService;
	
	@Autowired
	public BusSessionController(BusSessionService busSessionService, BusService busService,
                             BusStationService busStationService) {
		this.busSessionService = busSessionService;
		this.busService = busService;
		this.busStationService = busStationService;
	}
	
	@GetMapping(value = "busSession")
	public ModelAndView allBusSessions() {
		ModelAndView mav = new ModelAndView("BusSession/busSession");
		mav.addObject("busSession", busSessionService.getAllBusSessions());
		return mav;
	}
	
	@GetMapping(value = "showbussession")
	public ModelAndView showBusSession() {
		List<Bus> buses = busService.getAllBuses();
		List<BusStation> busStations = busStationService.getAllBusStation();
		ModelAndView mav = new ModelAndView("BusSession/new_bussession");
		BusSession busSession = new BusSession();
		mav.addObject("busList", buses);
		mav.addObject("busStationList", busStations);
		mav.addObject("busSession", busSession);
		return mav;
	}
	
	@PostMapping(value = "saveBusSession")
	public RedirectView addSession(@ModelAttribute("busSession") BusSession busSession) {
		busSessionService.addBusSession(busSession);
		RedirectView redirectView = new RedirectView("showbussession", true);
		return redirectView;
	}
	
	@GetMapping("busSession/{busSessionId}")
	public String deleteBusSession(@PathVariable Long busSessionId) {
		busSessionService.deleteBusSessionById(busSessionId);
		return "redirect:/bussession";
	}
	
	@GetMapping("editBusSession/{id}")
	public ModelAndView editBusSessionForm(@PathVariable("id") Long BusSessionId, Model model) {
		BusSession busSession = this.busSessionService.getBusSessionById(BusSessionId);
		ModelAndView mav = new ModelAndView("BusSession/update-busSession");
		List<Bus> buses = busService.getAllBuses();
		List<BusStation> busStation = busStationService.getAllBusStation();
		mav.addObject("busList", buses);
		mav.addObject("busList",busStation);
		mav.addObject("busSession", busSession);
		return mav;
	}
		
		@RequestMapping(value = "updateBusSession/{id}", method = { RequestMethod.PUT, RequestMethod.GET })
		public BusSession update(@RequestBody BusSession busSession) {
			busSessionService.updateBusSession(busSession);
			return busSession;
		}
	}
	
	
