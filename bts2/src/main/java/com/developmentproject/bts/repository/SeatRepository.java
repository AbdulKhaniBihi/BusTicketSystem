package com.developmentproject.bts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.developmentproject.bts.entity.Seat;
@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
	Optional<Seat> findBySeatNumber(int i);

}
