package com.cinematrics.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document
public class ScreenDto {

	@Id
	private Long movieId;

	private String movieName;

	@JsonProperty("movieDate")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate movieDate;

	private List<ShowTime> showTimes;

	private Integer screenNo;

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

	public Integer getScreenNo() {
		return screenNo;
	}

	public void setScreenNo(Integer screenNo) {
		this.screenNo = screenNo;
	}

	public Integer getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(Integer noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

}
