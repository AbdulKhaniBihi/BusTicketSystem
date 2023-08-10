package com.developmentproject.bts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developmentproject.bts.entity.Station;
import com.developmentproject.bts.repository.StationRepository;

@Service
public class StationService {
	private final StationRepository stationRepository;
	@Autowired
    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }
	public List<Station> getAllStations() {
	    return stationRepository.findAll();
	}
	public Optional<Station> findById(Long id) {
	    return stationRepository.findById(id);
	}
	public Station addStation(Station station) {
	    Optional<Station> stationName = stationRepository.findByStationName(station.getStationName());

	    if (stationName.isPresent()) {
	        throw new IllegalStateException(station.getStationName() +
	                " This station name is already exist, please use another name.");
	    }

	    return stationRepository.save(station);
	}
	public void updateStation(Station station) {
	    Long stationId = station.getStationId();
	    if (stationId != null) {
	        Optional<Station> optionalStation = stationRepository.findById(stationId);
	        if (optionalStation.isPresent()) {
	            Station existingStation = optionalStation.get();
	            existingStation.setAddress(station.getAddress());
	            existingStation.setStationName(station.getStationName());
	            existingStation.setEmail(station.getEmail());
	            stationRepository.save(existingStation);
	        } else {
	            throw new IllegalStateException("The Station with ID " + stationId + " does not exist.");
	        }
	    } 
	}
	

	public void deleteStationById(Long id) {
	    if (findById(id).isPresent()) {
	        stationRepository.deleteById(id);
	    } else {
	        throw new IllegalStateException("Station with ID " + id + " does not exist.");
	    }
	}

	public Station getStationById(Long stationId) {
        return stationRepository.findById(stationId)
                .orElseThrow(() -> new IllegalArgumentException("Station with ID " + stationId + " not found."));
    }

}
