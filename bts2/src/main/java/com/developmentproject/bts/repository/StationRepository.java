package com.developmentproject.bts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.developmentproject.bts.entity.Station;
@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
	Optional<Station> findById(Long id);
	Optional<Station> findByStationName(String stationName);

}
