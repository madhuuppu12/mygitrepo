package com.cinematrics.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cinematrics.dto.MasterMovieDto;
import com.cinematrics.dto.MovieDto;
import com.cinematrics.dto.ScreenDto;
import com.cinematrics.util.MovieNotFoundException;
import com.cinematrics.vo.MovieVo;
import com.cinematrics.vo.ScreenVo;

public interface MovieService {

	void addMovieInScreen(ScreenDto vo);

	void deleteMovie(MovieVo movie) throws MovieNotFoundException;

	List<ScreenVo> getMoviesData();

	List<MasterMovieDto> getMoviesList();

	void saveMoviesList(List<MasterMovieDto> movie);

	void addNewMovie(MovieDto dto);

	void addMovie(ScreenVo vo);

	void saveMoviesListWithImage(String name, Long l, MultipartFile file);
}
