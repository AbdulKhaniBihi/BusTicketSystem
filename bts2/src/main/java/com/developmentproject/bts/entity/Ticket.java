package com.developmentproject.bts.entity;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="ticket")
public class Ticket {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ticketId;
	@JsonManagedReference
	@OneToOne
	@JoinColumn(name = "seat_id", referencedColumnName = "seatId")
	private Seat seat;
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "bus_session_id")
	private BusSession busSession;
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public Ticket(){
		super();
	}
	
	public Ticket(Long ticketId, Seat seat, BusSession busSession, User user) {
		super();
		this.ticketId = ticketId;
		this.seat = seat;
		this.busSession = busSession;
		this.user = user;
	}
	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}


	
	public BusSession getBusSession() {
		return busSession;
	}

	public void setBusSession(BusSession busSession) {
		this.busSession = busSession;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(busSession, seat, ticketId, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		return Objects.equals(busSession, other.busSession) && Objects.equals(seat, other.seat)
				&& Objects.equals(ticketId, other.ticketId) && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", seat=" + seat + ", busSession=" + busSession + ", user=" + user
				+ "]";
	}
	
	public void setUser(Long user) {
		this.getUser().getUserId();
		
	}

	

}
