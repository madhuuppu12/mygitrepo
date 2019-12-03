package com.cinematrics.controller;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.cinematrics.dao.ScreenDao;

import com.cinematrics.dto.MasterMovieDto;
import com.cinematrics.dto.ScreenDto;
import com.cinematrics.service.MovieService;
import com.cinematrics.service.ScreenService;
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

	@Autowired
	private ScreenService screenService;

	// Add movies to Screen with show date show time and with seats associated
	@PostMapping("/addMovieInScreen")
	public GateWayResponse<?> addMovieInScreen(@RequestBody ScreenDto dto) throws ParseException {

		movieService.addMovieInScreen(dto);

		return new GateWayResponse<>(HttpStatus.OK);

	}

	// get all Movies in Screen

	@GetMapping("/getAllAddedMoviesInScreen")
	public GateWayResponse<?> getAllAddedMoviesInScreen() {
		try {
			List<ScreenDto> result = screenDao.findAll();

			return new GateWayResponse<>(true, HttpStatus.OK, result);
		} catch (Exception e) {

			return new GateWayResponse<>(HttpStatus.BAD_REQUEST, "Records not found");
		}

	}

	@PostMapping("/deleteAShowInScreen")
	public GateWayResponse<?> deleteAShowInScreen(@RequestBody ScreenDto dto) {
		try {
			screenService.deleteAShowInScreen(dto);
			return new GateWayResponse<>(HttpStatus.OK, "success");
		} catch (Exception e) {
			log.error("Error in deleteAShowInScreen {} ", e);
			return new GateWayResponse<>(HttpStatus.BAD_REQUEST, e.getMessage());
		}

	}

	/*// ============Delete movie by Id===
	@PostMapping("/deleteMovie")
	public ResponseEntity<?> deleteMovie(@RequestBody MovieVo movie) {

		try {
			movieService.deleteMovie(movie);
		} catch (MovieNotFoundException e) {
			log.error("Exception occurred {}", e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}

		return new ResponseEntity<>(HttpStatus.OK);

	}*/
/*
	// =============== Add Master movies List===========================
	@GetMapping("/getMoviesList")
	public GateWayResponse<?> getMoviesList() {
		List<MasterMovieDto> list;
		try {
			list = movieService.getMoviesList();
			return new GateWayResponse<>(true, HttpStatus.OK, list);
		} catch (Exception e) {
			log.error("Exception {}", e);
			return new GateWayResponse<>(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	// save master data with  images
	@GetMapping("/saveMoviesListWithImage")
			public GateWayResponse<?> saveMoviesListWithImage(@RequestParam String name,@RequestParam String id,@RequestParam MultipartFile file) throws IOException {
				
				Long l=Long.parseLong(id);
				movieService.saveMoviesListWithImage(name,l,file);
				return new GateWayResponse<>(HttpStatus.OK, "success");

			}
	// =================Get master movies List
	@PostMapping("/saveMoviesList")
	public GateWayResponse<?> saveMoviesList(@RequestBody List<MasterMovieDto> movie) {
		try {
			movieService.saveMoviesList(movie);
		} catch (Exception e) {
			log.error("Exception {}", e);
		}

		return new GateWayResponse<>(HttpStatus.OK, "success");

	}
*/
	// =================== findAllmovies and seats associated with Screen on
	// date;
	@GetMapping("/findByMovieNameAndMovieDate")
	public GateWayResponse<?> findByMovieNameAndMovieDate(@RequestParam String movieName, @RequestParam String date) {

		try {
			DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate localDate = LocalDate.parse(date, formatter1);

			List<ScreenDto> result = screenDao.findByMovieNameAndMovieDate(movieName, localDate);
			return new GateWayResponse<>(true, HttpStatus.OK, result);
		} catch (Exception e) {
			log.error("Exception {}", e);
			return new GateWayResponse<>(HttpStatus.BAD_REQUEST, e.getMessage());
		}

	}

	

}
