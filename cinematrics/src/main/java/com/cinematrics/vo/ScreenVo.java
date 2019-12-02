package com.cinematrics.vo;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.web.multipart.MultipartFile;

public class ScreenVo {

	private String movieName;

	private String screenName;

	private LocalDateTime startDate;
	
	private LocalDateTime toDate;

	private String timings;
	
	private MultipartFile moviePoster;
	
	private LocalDateTime endDate;

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getToDate() {
		return toDate;
	}

	public void setToDate(LocalDateTime toDate) {
		this.toDate = toDate;
	}

	public String getTimings() {
		return timings;
	}

	public void setTimings(String timings) {
		this.timings = timings;
	}

	public MultipartFile getMoviePoster() {
		return moviePoster;
	}

	public void setMoviePoster(MultipartFile moviePoster) {
		this.moviePoster = moviePoster;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	
	
	
	

}
