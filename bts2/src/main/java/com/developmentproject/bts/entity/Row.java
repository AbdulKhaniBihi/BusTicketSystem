package com.developmentproject.bts.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="busRows")
public class Row {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rowId;
	@Enumerated(EnumType.STRING)
	private RowIndex rowIndex;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "row")
	private Set<Seat> seats;
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "busStationID")
	private BusStation busStation;

	public Row() {
		super();
	}

	public Row(Long rowId, RowIndex rowIndex, Set<Seat> seats, BusStation busStation) {
		super();
		this.rowId = rowId;
		this.rowIndex = rowIndex;
		this.seats = seats;
		this.busStation = busStation;
	}

	public Long getRowId() {
		return rowId;
	}

	public void setRowId(Long rowId) {
		this.rowId = rowId;
	}

	public RowIndex getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(RowIndex rowIndex) {
		this.rowIndex = rowIndex;
	}

	public Set<Seat> getSeats() {
		return seats;
	}

	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
	}

	public BusStation getBusStation() {
		return busStation;
	}

	public void setBusStation(BusStation busStation) {
		this.busStation = busStation;
	}
	

}
