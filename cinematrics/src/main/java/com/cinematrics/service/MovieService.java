package com.cinematrics.service;

import java.util.List;

import com.cinematrics.dto.Movie;
import com.cinematrics.dto.MovieDto;
import com.cinematrics.dto.ScreenDto;
import com.cinematrics.util.MovieNotFoundException;
import com.cinematrics.vo.MovieVo;
import com.cinematrics.vo.ScreenVo;

public interface MovieService {

	void addMovieInScreen(ScreenDto vo);

	void deleteMovie(MovieVo movie) throws MovieNotFoundException;

	List<ScreenVo> getMoviesData();

	List<Movie> getMoviesList();

	void saveMoviesList(List<Movie> movie);

	void addNewMovie(MovieDto dto);

	void addMovie(ScreenVo vo);
}
