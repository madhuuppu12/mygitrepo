package com.cinematrics.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cinematrics.dao.ScreenDao;
import com.cinematrics.dto.ScreenDto;
import com.cinematrics.util.GateWayResponse;

@CrossOrigin
@RestController
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private ScreenDao screenDao;

	@GetMapping("/")
	public String userHome() {
		return "You are in User home";

	}

	// =========== findAllScreensInTheatre

	@GetMapping("/findAllScreensInTheatre")
	public GateWayResponse<?> findByMovieName() {
		try {
			List<ScreenDto> result = screenDao.findAll();
			return new GateWayResponse<>(true, HttpStatus.OK, result);
		} catch (Exception e) {
			log.error("Exception {}", e);
			return new GateWayResponse<>(HttpStatus.BAD_REQUEST, e.getMessage());
		}

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
