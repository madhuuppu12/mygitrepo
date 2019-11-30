package com.cinematrics.service;

import com.cinematrics.util.MovieNotFoundException;
import com.cinematrics.vo.MovieVo;
import com.cinematrics.vo.ScreenVo;

public interface MovieService {

	void addMovie(ScreenVo screen);

	void deleteMovie(MovieVo movie) throws MovieNotFoundException;

}
