package com.developmentproject.bts.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.developmentproject.bts.entity.Row;
import com.developmentproject.bts.entity.Seat;
import com.developmentproject.bts.service.RowService;
import com.developmentproject.bts.service.SeatService;

@RestController
@RequestMapping("seat")
public class SeatController {

    private final SeatService seatService;
    private final RowService rowService;

    @Autowired
    public SeatController(SeatService seatService, RowService rowService) {
        this.seatService = seatService;
        this.rowService = rowService;
    }

    @RequestMapping(value = "listseats", method = RequestMethod.GET)
    public ModelAndView allSeats() {
        ModelAndView mav = new ModelAndView("Seats/seat");
        mav.addObject("seat", seatService.getAllSeats());
        return mav;
    }

    @RequestMapping(value = "seat/{id}")
    public Optional<Seat> getSeat(@PathVariable Long id) {
        return seatService.findSeatById(id);
    }

    @RequestMapping(value = "showseats", method = RequestMethod.GET)
    public ModelAndView showSeats() {
        List<Row> rows = rowService.getAllBusStationRows();
        ModelAndView mav = new ModelAndView("Seats/new_seat");
        Seat seat = new Seat();
        mav.addObject("rowList", rows);
        mav.addObject("seat", seat);
        return mav;
    }

    @RequestMapping(value = "addseat", method = RequestMethod.POST)
    public RedirectView saveSeat(@ModelAttribute("seat") Seat seat) {
        seatService.addSeat(seat);
        RedirectView redirectView = new RedirectView("seat", true);
        return redirectView;
    }

    @GetMapping("seat/{seatId}")
    public String deleteSeat(@PathVariable Long seatId) {
        seatService.deleteSeatById(seatId);
        return "redirect:/seat";
	    }
	}


