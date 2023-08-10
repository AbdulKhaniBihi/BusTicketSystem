package com.developmentproject.bts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developmentproject.bts.entity.BusSession;
import com.developmentproject.bts.repository.BusSessionRepository;

@Service
public class BusSessionService {
    private final BusSessionRepository busSessionRepository;

    @Autowired
    public BusSessionService(BusSessionRepository busSessionRepository) {
        this.busSessionRepository = busSessionRepository;
    }

    public List<BusSession> getAllBusSessions() {
        return busSessionRepository.findAll();
    }

    public BusSession addBusSession(BusSession busSession) {
        Optional<BusSession> sessionDate = busSessionRepository.findByBusDate(busSession.getBusDate());
        Optional<BusSession> sessionTime = busSessionRepository.findByOriginTime(busSession.getOriginTime());
        Optional<BusSession> sessionVenue = busSessionRepository.findByBusStation(busSession.getBusStation());

        if (sessionDate.isPresent() && sessionTime.isPresent() && sessionVenue.isPresent()) {
            throw new IllegalStateException("The Bus Session already exists");
        } else {
            return busSessionRepository.save(busSession);
        }
    }

    public Optional<BusSession> findSessionById(Long busSessionId) {
        return busSessionRepository.findById(busSessionId);
    }

    public Optional<BusSession> findBusSessionById(Long id) {
        Optional<BusSession> sessionId = busSessionRepository.findById(id);
        if (sessionId.isEmpty()) {
            throw new IllegalStateException("The Bus Session with ID " + id + " does not exist.");
        } else {
            return sessionId;
        }
    }

    // delete bus session if the ID exists
    public void deleteBusSessionById(Long id) {
        if (findBusSessionById(id).isPresent()) {
            busSessionRepository.deleteById(id);
        } else {
            throw new IllegalStateException("The Bus Session with ID " + id + " does not exist.");
        }
    }

    // Update BusSession.
    public void updateBusSession(BusSession busSession) {
        BusSession existingBusSession = findBusSessionById(busSession.getBusSessionId()).orElse(null);
        if (existingBusSession != null) {
            existingBusSession.setBus(busSession.getBus());
            existingBusSession.setBusDate(busSession.getBusDate());
            existingBusSession.setOriginTime(busSession.getOriginTime());
            existingBusSession.setBusStation(busSession.getBusStation());
            existingBusSession.setPrice(busSession.getPrice());
            busSessionRepository.save(existingBusSession);
        } else {
            throw new IllegalStateException("The Bus Session with ID " + busSession.getBusSessionId() + " does not exist.");
        }
    }

    public void capacity() {
        // TODO: Implement capacity logic if needed.
    }

    public BusSession getBusSessionById(Long busSessionId) {
        return busSessionRepository.getReferenceById (busSessionId);
    }
}
