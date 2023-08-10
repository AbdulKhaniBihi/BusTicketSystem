package com.developmentproject.bts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developmentproject.bts.entity.Ticket;
import com.developmentproject.bts.repository.BusSessionRepository;
import com.developmentproject.bts.repository.BusStationRepository;
import com.developmentproject.bts.repository.TicketRepository;
import com.developmentproject.bts.repository.UserRepository;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final BusSessionRepository busSessionRepository;
    private final UserRepository userRepository;

    // Constructor
    @Autowired
    public TicketService(TicketRepository ticketRepository, 
                         BusSessionRepository busSessionRepository, 
                         UserRepository userRepository) {
    	super();
        this.ticketRepository = ticketRepository;
        this.busSessionRepository = busSessionRepository;
        this.userRepository = userRepository;
    }
    public Optional<Ticket> findTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    public Ticket update(Ticket ticket) {
    	//validation and check
        return ticketRepository.save(ticket);
    }
    
    public List<Ticket> getAllTicket() {
        return ticketRepository.findAll();
    }
    
    public Ticket saveTicket(Ticket ticket) {
    	
        return ticketRepository.save(ticket);
		
	}
    
    public void deleteTicketById(Long ticketId) {
		ticketRepository.deleteById(ticketId);
		
	}
    
}

    
    


