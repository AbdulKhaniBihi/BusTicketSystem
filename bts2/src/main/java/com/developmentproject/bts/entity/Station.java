package com.developmentproject.bts.entity;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="station")
public class Station {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long stationId;
	@Column(name = "stationName", unique=true,
			nullable = false,
			columnDefinition= "TEXT")
	private String stationName;
	@Column(name = "address", nullable = false,
			columnDefinition= "TEXT")
	private String address;
	@Column(name = "email", nullable = false,
			columnDefinition= "TEXT")
	private String email;
	@OneToMany(mappedBy = "station")
	private Set<BusStation> busstations;
	
	public Station() {
		super();
	}

	public Station(long stationId, String stationName, String address, String email, Set<BusStation> busstations) {
		super();
		this.stationId = stationId;
		this.stationName = stationName;
		this.address = address;
		this.email = email;
		this.busstations = busstations;
	}

	public long getStationId() {
		return stationId;
	}

	public void setStationId(long stationId) {
		this.stationId = stationId;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<BusStation> getBusstations() {
		return busstations;
	}

	public void setBusstations(Set<BusStation> busstations) {
		this.busstations = busstations;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, busstations, email, stationId, stationName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Station other = (Station) obj;
		return Objects.equals(address, other.address) && Objects.equals(busstations, other.busstations)
				&& Objects.equals(email, other.email) && stationId == other.stationId
				&& Objects.equals(stationName, other.stationName);
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		return "Station [stationId=" + stationId + ", stationName=" + stationName + ", address=" + address + ", email="
				+ email + ", busstations=" + (busstations != null ? toString(busstations, maxLen) : null) + "]";
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
