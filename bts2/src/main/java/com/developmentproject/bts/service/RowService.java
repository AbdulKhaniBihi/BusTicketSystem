package com.developmentproject.bts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developmentproject.bts.entity.Row;
import com.developmentproject.bts.repository.RowRepository;
import com.developmentproject.bts.repository.SeatRepository;

@Service
public class RowService {
	private final RowRepository rowRepository;
    private final SeatRepository seatRepository;
    @Autowired
    public RowService(RowRepository rowRepository, SeatRepository seatRepository) {
        this.rowRepository = rowRepository;
		this.seatRepository = seatRepository;
    }
    public List<Row> getAllBusStationRows() {
        return rowRepository.findAll();
    }
    @SuppressWarnings("unlikely-arg-type")
	public Row addrows(Row busStationRows) {
  		
  		if(busStationRows.equals(busStationRows.getRowIndex()) && busStationRows.equals(busStationRows.getBusStation().getStationLocation()) ) {
  			throw new IllegalStateException(
  				"This already exists"); 
  		}else
		 
		return rowRepository.save(busStationRows);
		  
  	}
  	 public void deleteBusStationRowById(Long id) {
  		 
            rowRepository.deleteById(id);
         
     }
    }
    


