package com.cinematrics.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinematrics.service.MovieService;
import com.cinematrics.util.MovieNotFoundException;
import com.cinematrics.vo.MovieVo;
import com.cinematrics.vo.ScreenVo;

@RestController
@RequestMapping("/admin")
public class AdminController {

	private static final Logger log = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	MovieService movieService;

	@GetMapping("/home")
	public String adminHome() {

		return "You are in admin home";
	}

	@PostMapping("/addMovie")
	private ResponseEntity<?> addMovie(@RequestBody ScreenVo vo) throws ParseException {

		movieService.addMovie(vo);

      
		return new ResponseEntity<>(HttpStatus.OK);

	}
	
	@GetMapping("/getMoviesData")
	public ResponseEntity<?> getMovieData(){
		
		List<ScreenVo> list=new ArrayList<>();
		list=movieService.getMoviesData();
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

	@PostMapping("/deleteMovie")
	private ResponseEntity<?> deleteMovie(@RequestBody MovieVo movie) {

		try {
			movieService.deleteMovie(movie);
		} catch (MovieNotFoundException e) {
			log.error("Exception occurred {}", e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}

		return new ResponseEntity<>(HttpStatus.OK);

	}
}
