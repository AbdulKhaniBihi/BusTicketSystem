package com.developmentproject.bts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.developmentproject.bts.entity.BusStation;
@Repository
public interface BusStationRepository extends JpaRepository<BusStation, Long> {
	void deleteById(Long id);
	Optional<BusStation> findById(Long id);
	Optional<BusStation> findByStationLocation(String stationLocation);

}
