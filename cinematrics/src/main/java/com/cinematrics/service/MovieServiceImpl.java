package com.cinematrics.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.cinematrics.dao.MovieDao;
import com.cinematrics.dao.ScreenDao;
import com.cinematrics.dto.Movie;
import com.cinematrics.dto.MovieDto;
import com.cinematrics.dto.ScreenDto;
import com.cinematrics.mapper.Mapper;
import com.cinematrics.util.MovieNotFoundException;
import com.cinematrics.vo.MovieVo;
import com.cinematrics.vo.ScreenVo;

@Service
public class MovieServiceImpl implements MovieService {

	// @Autowired
	// MovieDao movieDao;

	@Autowired
	ScreenDao screenDao;
	@Autowired
	MovieDao movieDao;
	@Autowired
	Mapper mapper;

	@Override
	public void addMovie(ScreenVo vo) {

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		List<LocalDateTime> dates = new ArrayList<>();
		long numOfDaysBetween = ChronoUnit.DAYS.between(vo.getStartDate(), vo.getEndDate());
		if (numOfDaysBetween > 0) {
			dates = IntStream.iterate(0, i -> i + 1).limit(numOfDaysBetween)
					.mapToObj(i -> vo.getStartDate().plusDays(i)).collect(Collectors.toList());
			dates.add(vo.getEndDate());
			System.out.println(dates);
		} else {
			dates.add(vo.getStartDate());
		}
		ScreenDto dto = mapper.convertSlotVotoDto(vo, dates);

		screenDao.save(dto);
	}

	@Override
	public void deleteMovie(MovieVo movie) throws MovieNotFoundException {

		// Optional<MovieDto> dto = movieDao.findById(movie.getId());
		// if (dto.isPresent()) {

		// movieDao.delete(dto.get());
		// }

		// else
		// throw new MovieNotFoundException("Movie not found with id " +
		// movie.getId());
	}

	@Override
	public List<ScreenVo> getMoviesData() {
		List<ScreenDto> list = screenDao.findAll();
		return mapper.convertScreenDtoToVo(list);
	}

	@Override
	public List<Movie> getMoviesList() {

		return movieDao.findAll();
	}

	@Override
	public void saveMoviesList(List<Movie> movie) {
		movieDao.saveAll(movie);
	}

	@Override
	public void addNewMovie(MovieDto dto) {

	}

	@Override
	public void addMovie(ScreenDto vo) {
		Sort sort = Sort.by(Order.desc("movieId"));
		List<ScreenDto> sortedList = screenDao.findAll(sort);

		sortedList.forEach(data -> {
			if (data.getMovieDate().equals(vo.getMovieDate())) {
				List<String> l = new ArrayList<>();
				vo.getShowTimes().forEach(d -> {
					l.add(d.getName());
				});
				data.getShowTimes().forEach(shows ->

				{
					System.out.println("show times  " + Arrays.asList(vo.getShowTimes()));
					if (l.contains(shows.getName())) {

						throw new RuntimeException("Show is already exists");
					}

				}

				);
			}

		}

		);

		Optional<ScreenDto> rec = sortedList.stream().findFirst();
		vo.setMovieId(rec.get().getMovieId() + 1);
		screenDao.save(vo);

	}

}
