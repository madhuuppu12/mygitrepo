package com.cinematrics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinematrics.dto.MovieDto;
import com.cinematrics.mapper.Mapper;
import com.cinematrics.util.MovieNotFoundException;
import com.cinematrics.vo.MovieVo;

@Service
public class MovieServiceImpl implements MovieService {

	//@Autowired
	//MovieDao movieDao;

	@Autowired
	Mapper mapper;

	@Override
	public void addMovie(MovieVo vo) {

		MovieDto dto = mapper.convertVotoDto(vo);

		//movieDao.save(dto);
	}

	@Override
	public void deleteMovie(MovieVo movie) throws MovieNotFoundException {

		//Optional<MovieDto> dto = movieDao.findById(movie.getId());
		//if (dto.isPresent()) {

			//movieDao.delete(dto.get());
		//}

		//else
			//throw new MovieNotFoundException("Movie not found with id " + movie.getId());
	}

}
