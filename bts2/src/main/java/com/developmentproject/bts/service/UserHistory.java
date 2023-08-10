package com.developmentproject.bts.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserHistory {
	private UserDetailsService userService;
	private ReservationService reservationService;

}
