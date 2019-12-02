package com.cinematrics.dto;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ScreenDto {

	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//private Long id;

	private String screenName;

	private List<TimeDto> timeDto;

//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public List<TimeDto> getTimeDto() {
		return timeDto;
	}

	public void setTimeDto(List<TimeDto> timeDto) {
		this.timeDto = timeDto;
	}

	
}
