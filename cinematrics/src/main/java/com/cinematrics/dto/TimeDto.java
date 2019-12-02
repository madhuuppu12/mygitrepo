package com.cinematrics.dto;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeDto {

	private LocalDateTime startDate;

	private String timings;
	
	private Blob moviePoster;
	
	private String status;
	
	private String movieName;

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public String getTimings() {
		return timings;
	}

	public void setTimings(String timings) {
		this.timings = timings;
	}

	public Blob getMoviePoster() {
		return moviePoster;
	}

	public void setMoviePoster(Blob moviePoster) {
		this.moviePoster = moviePoster;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	
	

	

	
	
	

	

}
