package com.developmentproject.bts.entity;

import java.sql.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "Bus")
public class Bus {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long busId;
	@Column(name = "busOrigin", nullable = false, columnDefinition = "TEXT")
	private String busOrigin;
	@Column(name = "busDestination", nullable = false, columnDefinition = "TEXT")
	private String busDestination;
	@Column(name = "busRegNumber", nullable = false, columnDefinition = "TEXT")
	private String busRegNumber;
	private String busDriver;
	private Date departureDate;
	 
	public Bus() {
		super();
	}
	

	public Bus(Long busId, String busOrigin, String busDestination, String busRegNumber, String busDriver,
			Date departureDate) {
		super();
		this.busId = busId;
		this.busOrigin = busOrigin;
		this.busDestination = busDestination;
		this.busRegNumber = busRegNumber;
		this.busDriver = busDriver;
		this.departureDate = departureDate;
	}
	
	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Long getBusId() {
		return busId;
	}
	public void setBusId(Long busId) {
		this.busId = busId;
	}
	public String getBusOrigin() {
		return busOrigin;
	}
	public void setBusOrigin(String busOrigin) {
		this.busOrigin = busOrigin;
	}
	public String getBusDestination() {
		return busDestination;
	}
	public void setBusDestination(String busDestination) {
		this.busDestination = busDestination;
	}
	public String getBusRegNumber() {
		return busRegNumber;
	}
	public void setBusRegNumber(String busRegNumber) {
		this.busRegNumber = busRegNumber;
	}
	public String getBusDriver() {
		return busDriver;
	}
	public void setBusDriver(String busDriver) {
		this.busDriver = busDriver;
	}

	@Override
	public int hashCode() {
		return Objects.hash(busDestination, busDriver, busId, busOrigin, busRegNumber, departureDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bus other = (Bus) obj;
		return Objects.equals(busDestination, other.busDestination) && Objects.equals(busDriver, other.busDriver)
				&& Objects.equals(busId, other.busId) && Objects.equals(busOrigin, other.busOrigin)
				&& Objects.equals(busRegNumber, other.busRegNumber)
				&& Objects.equals(departureDate, other.departureDate);
	}

	@Override
	public String toString() {
		return "Bus [busId=" + busId + ", busOrigin=" + busOrigin + ", busDestination=" + busDestination
				+ ", busRegNumber=" + busRegNumber + ", busDriver=" + busDriver + ", departureDate=" + departureDate
				+ "]";
	}
	
	

}
