package com.developmentproject.bts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="reservation")
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reservationId;
	
	@OneToOne
	@JoinColumn(name = "seat_id", referencedColumnName = "seatId")
	private Seat seat;
	
	@ManyToOne
	@JoinColumn(name = "bus_session_id")
	private BusSession busSession;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

}
