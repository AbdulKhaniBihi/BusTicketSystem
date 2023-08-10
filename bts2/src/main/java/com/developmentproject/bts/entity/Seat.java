package com.developmentproject.bts.entity;

import java.util.Objects;

import org.springframework.jdbc.core.RowCallbackHandler;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Entity
@Table(name="seat")
public class Seat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seatId;
	private int seatNumber;
	
	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "row_id", referencedColumnName = "rowId")
	private Row row;
	
	@Enumerated(EnumType.STRING)
	private SeatType seatType;

	public Seat() {
		super();
	}

	public Seat(Long seatId, int seatNumber,SeatType seatType, Row row) {
		super();
		this.seatId = seatId;
		this.seatNumber = seatNumber;
		this.row = row;
		this.seatType = seatType;
	}

	public SeatType getSeatType() {
		return seatType;
	}

	public void setSeatType(SeatType seatType) {
		this.seatType = seatType;
	}

	public Long getSeatId() {
		return seatId;
	}

	public void setSeatId(Long seatId) {
		this.seatId = seatId;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Row getRow() {
		return row;
	}

	public void setRow(Row row) {
		this.row = row;
	}

	@Override
	public int hashCode() {
		return Objects.hash(row, seatId, seatNumber, seatType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seat other = (Seat) obj;
		return Objects.equals(row, other.row) && Objects.equals(seatId, other.seatId) && seatNumber == other.seatNumber
				&& seatType == other.seatType;
	}

	@Override
	public String toString() {
		return "Seat [seatId=" + seatId + ", seatNumber=" + seatNumber + ", row=" + row + ", seatType=" + seatType
				+ "]";
	}
	
	
	

}
