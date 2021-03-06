package com.cinematrics.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cinematrics.dto.ScreenDto;
@Repository
public interface ScreenDao extends MongoRepository<ScreenDto, Long>{
	
	List<ScreenDto> findByMovieNameAndMovieDate(String movieName, LocalDate date);

	List<ScreenDto> findByMovieName(String movieName);

}
