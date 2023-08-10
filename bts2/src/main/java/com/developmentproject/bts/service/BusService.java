package com.developmentproject.bts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.developmentproject.bts.entity.Bus;
import com.developmentproject.bts.repository.BusRepository;

@Service
public class BusService {
	@Autowired
	private final BusRepository busRepository;
	
	@Autowired
	public BusService(BusRepository busRepository) {
		this.busRepository = busRepository;
		
	}
	 public List<Bus> getAllBuses() {  
	        return busRepository.findAll();
	    }

	    public Optional<Bus> getBusById(Long id) {  
	    	Optional <Bus> busId = busRepository.findById(id);
	    		
	    	if(busId == null) {
	    		throw new IllegalStateException(
	    				busId +	 
	  				"  Does not exist."); 
	    	}else
	        return busRepository.findById(id);
	    }

	    
	    public Bus addBus(Bus bus) {
	    	
	    	Optional<Bus> busRegNumber = busRepository.findBusByBusRegNumber(bus.getBusRegNumber());
	    	
			 if(busRegNumber.isPresent()) {
	    		throw new IllegalStateException(bus.getBusRegNumber() +
	    				 "  The Bus Reg already exists please use another Reg."); 
	    	}
	        return busRepository.save(bus);
	    }

	    //Update film details.
		public Bus updateBus(@RequestBody Bus bus) {
			
				bus.setBusId(bus.getBusId());
				bus.setBusOrigin(bus.getBusOrigin());
				bus.setBusDestination(bus.getBusDestination());
				bus.setBusDriver(bus.getBusRegNumber());
				bus.setBusDriver(bus.getBusDriver());
				
				return busRepository.saveAndFlush(bus);
			
		}
	    public void deleteBusById(Long id) {
	        if (getBusById(id) != null) {
	        	busRepository.deleteById(id);
	        }
	    }

	    public Bus findBusById(Long busId) {
	        Optional<Bus> busOptional = busRepository.findById(busId);
	        return busOptional.orElse(null);
	    }

}
