package com.developmentproject.bts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developmentproject.bts.repository.BusStationRepository;
import com.developmentproject.bts.entity.BusStation;


@Service
public class BusStationService {
	private BusStationRepository busStationRepository;
	
	@Autowired
    public BusStationService(BusStationRepository busStationRepository) {
        this.busStationRepository = busStationRepository;
    }
	 public Optional<BusStation> findById(Long id){
		 return busStationRepository.findById(id);
	 }
	 public void deleteById(Long id) {
		 busStationRepository.deleteById(id);
	 }
	 public List<BusStation> getAllBusStation() {
	        return busStationRepository.findAll();
	    }
	 public BusStation addBusStation(BusStation busStation) {
	    	//check if the file data exist.
	    	Optional<BusStation> stationLocation = busStationRepository.findByStationLocation(busStation.getStationLocation());
	    		
	    	 if(stationLocation.isPresent()) {
	    		throw new IllegalStateException(busStation.getStationLocation() +
	    				 "Use another location"); 
	    	}else 
	        return busStationRepository.save(busStation);
	        		
	    }
	 public void deleteBusStation(Long busStationId) {
		 busStationRepository.deleteById(busStationId);
	 }
	 

}
