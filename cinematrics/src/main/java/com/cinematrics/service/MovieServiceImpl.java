package com.cinematrics.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cinematrics.dao.MovieDao;
import com.cinematrics.dao.ScreenDao;
import com.cinematrics.dto.MasterMovieDto;
import com.cinematrics.dto.MovieDto;
import com.cinematrics.dto.ScreenDto;
import com.cinematrics.dto.SeatDto;
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
	public List<MasterMovieDto> getMoviesList() {

		return movieDao.findAll();
	}

	@Override
	public void saveMoviesList(List<MasterMovieDto> movie) {
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
			// Create seatsList
			if (data.getMovieDate().equals(vo.getMovieDate())) {
				List<String> l = new ArrayList<>();
				vo.getShowTimes().forEach(d -> {
					l.add(d.getName());
				});

				data.getShowTimes().forEach(shows -> {
					System.out.println("show times  " + Arrays.asList(vo.getShowTimes()));
					if (l.contains(shows.getName())) {

						throw new RuntimeException("Show is already exists");
					}

				}

			);
			}

		}

		);

		List<SeatDto> seatsList = new ArrayList<SeatDto>();
		for (Long i = 0l; i < vo.getNoOfSeats(); i++) {
			SeatDto singleSeat = new SeatDto();
			singleSeat.setSeatStatus(false);
			singleSeat.setSeatId(i);
			if (i <= 20) {
				singleSeat.setSeatClass("NORMAL");
				singleSeat.setSeatFare(75d);

			}
			if (i > 20 && i < 40) {
				singleSeat.setSeatClass("EXECUTIVE");
				singleSeat.setSeatFare(150d);
			}
			if (i > 40) {
				singleSeat.setSeatClass("VIP");
				singleSeat.setSeatFare(300d);
			}

			seatsList.add(singleSeat);
		}

		vo.getShowTimes().forEach(show -> {
			show.setSeats(seatsList);
		});
		vo.setShowTimes(vo.getShowTimes());

		Optional<ScreenDto> rec = sortedList.stream().findFirst();

		if (rec.isPresent()) {
			vo.setMovieId(rec.get().getMovieId() + 1);
		} else
			vo.setMovieId(1l);
		screenDao.save(vo);

	}

	@Override
	public void saveMoviesListWithImage(String name, Long id, MultipartFile file) {
		MasterMovieDto movieDto = new MasterMovieDto();
		movieDto.setId(id);
		movieDto.setName(name);
		try {
			movieDto.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		movieDao.save(movieDto);

	}

}
