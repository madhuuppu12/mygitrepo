package com.cinematrics.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cinematrics.dao.ScreenDao;
import com.cinematrics.dto.Movie;
import com.cinematrics.dto.MovieDto;
import com.cinematrics.dto.ScreenDto;
import com.cinematrics.service.MovieService;
import com.cinematrics.util.GateWayResponse;
import com.cinematrics.util.MovieNotFoundException;
import com.cinematrics.vo.MovieVo;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

	private static final Logger log = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private MovieService movieService;

	@Autowired
	private ScreenDao screenDao;

	// Add movies to Screen with show date show time and with seats associated

	@PostMapping("/addMovie")
	public GateWayResponse<?> addMovie(@RequestBody ScreenDto dto) throws ParseException {

		movieService.addMovie(dto);

		return new GateWayResponse<>(HttpStatus.OK);

	}

	// get all Movies in Screen

	@GetMapping("/getAllAddedMovies")
	public GateWayResponse<?> getAllAddedMovies() {
		try {
			List<ScreenDto> result = screenDao.findAll();

			return new GateWayResponse<>(true, HttpStatus.OK, result);
		} catch (Exception e) {

			return new GateWayResponse<>(HttpStatus.BAD_REQUEST, "Records not found");
		}

	}

	@PostMapping("/addNewMovie")
	public ResponseEntity<?> addNewMovie(@RequestBody MovieDto dto) throws ParseException {

		movieService.addNewMovie(dto);

		return new ResponseEntity<>(HttpStatus.OK);

	}

	// ============Delete movie by Id===
	@PostMapping("/deleteMovie")
	public ResponseEntity<?> deleteMovie(@RequestBody MovieVo movie) {

		try {
			movieService.deleteMovie(movie);
		} catch (MovieNotFoundException e) {
			log.error("Exception occurred {}", e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}

		return new ResponseEntity<>(HttpStatus.OK);

	}

	// =============== Add Master movies List===========================
	@GetMapping("/getMoviesList")
	public GateWayResponse<?> getMoviesList() {
		List<Movie> list;
		try {
			list = movieService.getMoviesList();
			return new GateWayResponse<>(true, HttpStatus.OK, list);
		} catch (Exception e) {
			log.error("Exception {}", e);
		}

		return new GateWayResponse<>(HttpStatus.BAD_REQUEST, "No Records Found");
	}

	// =================Get master movies List
	@PostMapping("/saveMoviesList")
	public GateWayResponse<?> saveMoviesList(@RequestBody List<Movie> movie) {
		movieService.saveMoviesList(movie);
		return new GateWayResponse<>(HttpStatus.OK, "success");

	}

	// =================== findAllmovies and seats associated with Screen on date;
	@GetMapping("/findByMovieNameAndMovieDate")
	public GateWayResponse<?> findByMovieNameAndMovieDate(@RequestParam String movieName, @RequestParam String date) {
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate localDate = LocalDate.parse(date, formatter1);

		List<ScreenDto> result = screenDao.findByMovieNameAndMovieDate(movieName, localDate);
		return new GateWayResponse<>(true, HttpStatus.OK, result);

	}

	@PostMapping("/bookMyTicket")
	public GateWayResponse<?> bookMyTicket(@RequestBody ScreenDto dto) {
		List<ScreenDto> result = screenDao.findByMovieNameAndMovieDate(dto.getMovieName(), dto.getMovieDate());
		// screenDao.save(dto);
		result.get(0).getShowTimes().forEach(show -> {
			show.getSeats().forEach(seat -> {
				if (seat.getSeatId() >= 50) {
					seat.setSeatStatus(true);
				}
			});
		});
		screenDao.save(result.get(0));
		return new GateWayResponse<>(true, HttpStatus.OK, result);

	}

}
