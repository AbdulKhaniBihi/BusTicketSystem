package com.developmentproject.bts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.developmentproject.bts.entity.BusStation;
import com.developmentproject.bts.entity.Row;
import com.developmentproject.bts.entity.Seat;
import com.developmentproject.bts.service.BusStationService;
import com.developmentproject.bts.service.RowService;
import com.developmentproject.bts.service.SeatService;

@RestController
@RequestMapping("row")
public class RowController {
	
	private final RowService rowService;
	private final SeatService seatService;
	private final BusStationService busStationService;
	
	@Autowired
    public RowController(RowService rowService, BusStationService busStationService, SeatService seatService) {
        this.rowService = rowService;
		this.seatService = seatService;
        this.busStationService = busStationService;
    }
	@RequestMapping(value = "busStationRows", method = RequestMethod.GET)
    public ModelAndView allBusStationRows() {
		ModelAndView mav = new ModelAndView("Seats/busstation_rows");
        mav.addObject("busStationRows", rowService.getAllBusStationRows());
        return mav;
    }
	@RequestMapping(value = "showrows", method = RequestMethod.GET)
  	public ModelAndView showRows() {
  		List <Seat> seat = seatService.getAllSeats();
		List<BusStation> busStation = busStationService.getAllBusStation();
  		ModelAndView mav = new ModelAndView("Seats/new_rows");
  		Row busStationRows = new Row();
  		mav.addObject("seatList",seat); 
  		mav.addObject("busStationList",busStation);
  		mav.addObject("busStationrow",busStationRows);
  		
  		return mav;
  	}
	@RequestMapping(value = "addrows", method = RequestMethod.POST)
	public RedirectView saveRows(@ModelAttribute("busStationrow") Row busStationRows) {
		rowService.addrows(busStationRows);
		RedirectView redirectView = new RedirectView("busStationRows", true);
		return redirectView;		
		
	}
	@GetMapping("busStationrows/{rowId}")
	public String deleteBusStationRows(@PathVariable Long rowId) {
		
		rowService.deleteBusStationRowById(rowId);
		
		return "redirect:/busStationRows";
	}
	

}
