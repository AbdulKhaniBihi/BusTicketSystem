package com.developmentproject.bts.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.developmentproject.bts.entity.Seat;
import com.developmentproject.bts.entity.Ticket;
import com.developmentproject.bts.entity.User;
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
	
	List<Ticket> findAll();
	
	Collection<Ticket> findTicketsByUser(User user);
	Collection<Ticket> findTicketsBySeat(Seat seat);

}
