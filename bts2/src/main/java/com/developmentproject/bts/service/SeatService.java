package com.developmentproject.bts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developmentproject.bts.repository.SeatRepository;
import com.developmentproject.bts.entity.Seat;

@Service
public class SeatService {
	private SeatRepository seatRepository;
	//private List<Seat> seats = Arrays.asList();	
	@Autowired
	public SeatService(SeatRepository seatRepository) {
		super();
		this.seatRepository = seatRepository;
	
	}
	

	//get all seats
    public List<Seat> getAllSeats() {
    	
        List<Seat> seats = new ArrayList<>();
    	
        seatRepository.findAll()
        .forEach(seats::add);
    	
        return seats;		
    }
    // find a single seat by id
    public Optional<Seat> findSeatById(Long id) {
    	Optional<Seat> SeatByid = seatRepository.findById(id);
    	if(SeatByid.equals(null)) {
    		throw new IllegalStateException("Seat does not exist" + SeatByid.get());
    	}else
		return SeatByid;
    	
    }
   
  //Create new Seat
  	public Seat addSeat(Seat seat) {
  		Optional <Seat> seatNumber = seatRepository.findBySeatNumber(seat.getSeatNumber());
  	
  		if(seatNumber.isPresent()) {
  			throw new IllegalStateException(
  					
  				"  Seat Number: " + seat.getSeatNumber() +	 " already exist, please choose another one."); 
  		}else
		return seatRepository.save(seat);
		  
  	}

    public void deleteSeatById(Long id) {
    	if (id != null) {
    		seatRepository.deleteById(id);
        }
    }

}
