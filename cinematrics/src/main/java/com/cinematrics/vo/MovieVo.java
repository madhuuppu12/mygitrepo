package com.cinematrics.vo;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

public class MovieVo {

	private Long id;

	private String name;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	private String screenNo;

	private Integer noOfSeats;

	private MultipartFile moviePoster;
	private String slotName;

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

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public String getScreenNo() {
		return screenNo;
	}

	public void setScreenNo(String screenNo) {
		this.screenNo = screenNo;
	}

	public Integer getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(Integer noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public MultipartFile getMoviePoster() {
		return moviePoster;
	}

	public void setMoviePoster(MultipartFile moviePoster) {
		this.moviePoster = moviePoster;
	}

	public String getSlotName() {
		return slotName;
	}

	public void setSlotName(String slotName) {
		this.slotName = slotName;
	}
	

}
