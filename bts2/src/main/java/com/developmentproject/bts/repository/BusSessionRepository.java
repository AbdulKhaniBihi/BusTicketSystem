package com.developmentproject.bts.repository;

import java.sql.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.developmentproject.bts.entity.BusSession;
import com.developmentproject.bts.entity.BusStation;

@Repository
public interface BusSessionRepository extends JpaRepository<BusSession, Long> {
	Optional<BusSession> findById(Long id);

	Optional<BusSession> findByBusDate(Date busDate);

	Optional<BusSession> findByOriginTime(String originTime);

	Optional<BusSession> findByBusStation(BusStation busStation);

}
