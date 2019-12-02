package com.cinematrics.dto;

public class SeatDto {

	private Long seatId;
	private String seatClass;
	private Double seatFare;
	private boolean seatStatus;

	public Long getSeatId() {
		return seatId;
	}

	public void setSeatId(Long seatId) {
		this.seatId = seatId;
	}

	public String getSeatClass() {
		return seatClass;
	}

	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}

	
	public Double getSeatFare() {
		return seatFare;
	}

	public void setSeatFare(Double seatFare) {
		this.seatFare = seatFare;
	}

	public boolean isSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(boolean seatStatus) {
		this.seatStatus = seatStatus;
	}

}
