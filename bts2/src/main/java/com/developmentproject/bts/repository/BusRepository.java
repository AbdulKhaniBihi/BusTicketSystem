package com.developmentproject.bts.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.developmentproject.bts.entity.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
	Optional<Bus> findBusByBusRegNumber(String busRegNumber);
}