package com.developmentproject.bts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.developmentproject.bts.entity.BusSession;
import com.developmentproject.bts.entity.Seat;
import com.developmentproject.bts.entity.Ticket;
import com.developmentproject.bts.entity.User;
import com.developmentproject.bts.repository.TicketRepository;
import com.developmentproject.bts.repository.UserRepository;
import com.developmentproject.bts.service.BusSessionService;
import com.developmentproject.bts.service.SeatService;
import com.developmentproject.bts.service.TicketService;

@RestController
@RequestMapping("ticket")
public class TicketController {
	private final TicketService ticketService;
	    private BusSessionService busSessionService;
	    private SeatService seatService;
	    private UserRepository userRepository;
	    private TicketRepository ticketRepository;


@Autowired
public TicketController(TicketService ticketService, 
		SeatService seatService, BusSessionService busSessionService, UserRepository userRepository) {
	super();
	this.ticketService = ticketService;
	this.busSessionService = busSessionService;
	this.seatService = seatService;
	this.userRepository = userRepository;
}
@GetMapping(value = "list")

public ModelAndView allTickets(Authentication auth) {
	ModelAndView mav = new ModelAndView("Ticket/ticket");
	 mav.addObject("ticket", ticketService.getAllTicket());
    return mav;
}
@GetMapping(value = "userticket")
public ModelAndView userTicket( Long userId, Authentication principal) {
 	
     ModelAndView mav = new ModelAndView("Ticket/ticket");
	 mav.addObject("ticket", ticketRepository.findAll());
	
    return mav;
	
}
@GetMapping(value = "showtickets") 
public ModelAndView showBuses() { 
	ModelAndView mav = new
	ModelAndView("Ticket/new_ticket"); 
	List <Seat> seats = seatService.getAllSeats();
	List<BusSession> busSession = busSessionService.getAllBusSessions();
	Ticket ticket = new Ticket(); 
	mav.addObject("seatList",seats); 
	mav.addObject("busSessionList",busSession);
	mav.addObject("ticket",ticket);
	
  return mav;
  
  }

@RequestMapping(value = "/addticket", method = RequestMethod.POST)
public RedirectView  addTicket(@ModelAttribute Ticket ticket) {
   String username =  SecurityContextHolder.getContext().getAuthentication().getName();
    User user = this.userRepository.findByUsername(username);
    ticket.setUser(user);
    ticket.setBusSession(ticket.getBusSession());
    ticket.setSeat(ticket.getSeat());
     
    ticketService.saveTicket(ticket);
    RedirectView redirectView = new RedirectView("list", true);
	return redirectView;		
	
}

	
	@GetMapping("tickets/{ticketId}")
	public String deleteTicket(@PathVariable Long ticketId) {
		ticketService.deleteTicketById(ticketId);
				
		return "redirect:/list";		
	}
}
