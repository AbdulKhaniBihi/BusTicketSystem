package com.developmentproject.bts.entity;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="busStation")
public class BusStation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long busStationID;
	@Column(name="stationLocation", unique=true,
			nullable = false,
			columnDefinition = "TEXT")
	private String stationLocation;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name= "station_id")
	private Station station;
	@JsonManagedReference
	@OneToMany(mappedBy = "busStation")
	private Set<Row> rows;
	@JsonManagedReference
	@OneToMany(mappedBy = "busStation")
	private Set<BusSession> busSessions;
	
	public BusStation() {
		super();
	}

	public BusStation(Long busStationID, String stationLocation, Station station, Set<Row> rows,
			Set<BusSession> busSessions) {
		super();
		this.busStationID = busStationID;
		this.stationLocation = stationLocation;
		this.station = station;
		this.rows = rows;
		this.busSessions = busSessions;
	}

	public Long getBusStationID() {
		return busStationID;
	}

	public void setBusStationID(Long busStationID) {
		this.busStationID = busStationID;
	}

	public String getStationLocation() {
		return stationLocation;
	}

	public void setStationLocation(String stationLocation) {
		this.stationLocation = stationLocation;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public Set<Row> getRows() {
		return rows;
	}

	public void setRows(Set<Row> rows) {
		this.rows = rows;
	}

	public Set<BusSession> getBusSessions() {
		return busSessions;
	}

	public void setBusSessions(Set<BusSession> busSessions) {
		this.busSessions = busSessions;
	}

	@Override
	public int hashCode() {
		return Objects.hash(busSessions, busStationID, rows, station, stationLocation);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BusStation other = (BusStation) obj;
		return Objects.equals(busSessions, other.busSessions) && Objects.equals(busStationID, other.busStationID)
				&& Objects.equals(rows, other.rows) && Objects.equals(station, other.station)
				&& Objects.equals(stationLocation, other.stationLocation);
	}
	@Override
	public String toString() {
		return "BusStation [busStationID=" + busStationID  + ", stationLocation=" + stationLocation  + ", station=" + station  + ", rows=" + rows
				+ ", busSessions=" + busSessions  + "]";
	}

	

}
