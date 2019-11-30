package com.cinematrics.service;

import com.cinematrics.util.MovieNotFoundException;
import com.cinematrics.vo.MovieVo;

public interface MovieService {

	void addMovie(MovieVo movie);

	void deleteMovie(MovieVo movie) throws MovieNotFoundException;

}
