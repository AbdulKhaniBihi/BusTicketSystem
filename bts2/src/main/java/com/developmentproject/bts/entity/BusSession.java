package com.developmentproject.bts.entity;

import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class BusSession {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long busSessionId;
	@ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;
    private Date busDate;
    private double price;
    private String originTime;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "busStation_id")
    private BusStation busStation;
    @JsonManagedReference
    @OneToMany( mappedBy = "busSession", cascade =  CascadeType.ALL , orphanRemoval=true)
    private Set<Ticket> tickets = new HashSet<>();
    
    public BusSession() {
    	super();
    }

	public BusSession(Long busSessionId, Bus bus, Date busDate, double price, String originTime, BusStation busStation,
			Set<Ticket> tickets) {
		super();
		this.busSessionId = busSessionId;
		this.bus = bus;
		this.busDate = busDate;
		this.price = price;
		this.originTime = originTime;
		this.busStation = busStation;
		this.tickets = tickets;
	}

	public Long getBusSessionId() {
		return busSessionId;
	}

	public void setBusSessionId(Long busSessionId) {
		this.busSessionId = busSessionId;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public Date getBusDate() {
		return busDate;
	}

	public void setBusDate(Date busDate) {
		this.busDate = busDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getOriginTime() {
		return originTime;
	}

	public void setOriginTime(String originTime) {
		this.originTime = originTime;
	}

	public BusStation getBusStation() {
		return busStation;
	}

	public void setBusStation(BusStation busStation) {
		this.busStation = busStation;
	}

	public Set<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bus, busDate, busSessionId, busStation, originTime, price, tickets);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BusSession other = (BusSession) obj;
		return Objects.equals(bus, other.bus) && Objects.equals(busDate, other.busDate)
				&& Objects.equals(busSessionId, other.busSessionId) && Objects.equals(busStation, other.busStation)
				&& Objects.equals(originTime, other.originTime)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Objects.equals(tickets, other.tickets);
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		return "BusSession [busSessionId=" + busSessionId + ", bus=" + bus + ", busDate=" + busDate + ", price=" + price
				+ ", originTime=" + originTime + ", busStation=" + busStation + ", tickets="
				+ (tickets != null ? toString(tickets, maxLen) : null) + "]";
	}

	private String toString(Collection<?> collection, int maxLen) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int i = 0;
		for (Iterator<?> iterator = collection.iterator(); iterator.hasNext() && i < maxLen; i++) {
			if (i > 0)
				builder.append(", ");
			builder.append(iterator.next());
		}
		builder.append("]");
		return builder.toString();
	}

	

	

}
