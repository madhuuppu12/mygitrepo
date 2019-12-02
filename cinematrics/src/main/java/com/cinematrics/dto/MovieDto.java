package com.cinematrics.dto;

import java.time.LocalDate;
import java.util.List;

public class MovieDto {

	private Long movieId;

	private String movieName;

	private LocalDate movieDate;

	private List<ShowTime> showTimes;

	private ScreenDto screen;

	private Integer noOfSeats;

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public LocalDate getMovieDate() {
		return movieDate;
	}

	public void setMovieDate(LocalDate movieDate) {
		this.movieDate = movieDate;
	}

	public List<ShowTime> getShowTimes() {
		return showTimes;
	}

	public void setShowTimes(List<ShowTime> showTimes) {
		this.showTimes = showTimes;
	}

	public ScreenDto getScreen() {
		return screen;
	}

	public void setScreen(ScreenDto screen) {
		this.screen = screen;
	}

	public Integer getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(Integer noOfSeats) {
		this.noOfSeats = noOfSeats;
	}
	
	
	
	
	

}
