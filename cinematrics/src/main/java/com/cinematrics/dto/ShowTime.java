package com.cinematrics.dto;

import java.util.List;

public class ShowTime {
	
	private Long id;
	
	private String name;
	
	private boolean checked;
	
	private List<SeatDto> seats;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public List<SeatDto> getSeats() {
		return seats;
	}

	public void setSeats(List<SeatDto> seats) {
		this.seats = seats;
	}
	
	
	

}
