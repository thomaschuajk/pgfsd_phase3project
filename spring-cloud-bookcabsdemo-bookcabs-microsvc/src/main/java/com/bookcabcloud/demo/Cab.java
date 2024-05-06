package com.bookcabcloud.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="cab")
@Entity
public class Cab {
	
	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String cabId;
	
	private String driverId;
	
	private int seatersCap;
	
	private String cabStatus;
	
	private String toLoc;
	
	private String fromLoc;
	
	private int bookingId;

	public String getCabId() {
		return cabId;
	}

	public void setCabId(String cabId) {
		this.cabId = cabId;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public int getSeatersCap() {
		return seatersCap;
	}

	public void setSeatersCap(int seatersCap) {
		this.seatersCap = seatersCap;
	}

	public String getCabStatus() {
		return cabStatus;
	}

	public void setCabStatus(String cabStatus) {
		this.cabStatus = cabStatus;
	}

	public String getToLoc() {
		return toLoc;
	}

	public void setToLoc(String toLoc) {
		this.toLoc = toLoc;
	}

	public String getFromLoc() {
		return fromLoc;
	}

	public void setFromLoc(String fromLoc) {
		this.fromLoc = fromLoc;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}	

}