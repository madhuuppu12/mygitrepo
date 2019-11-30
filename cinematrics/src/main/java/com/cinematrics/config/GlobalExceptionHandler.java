package com.cinematrics.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Configuration
@ControllerAdvice
public class GlobalExceptionHandler extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	private ResponseEntity<Object> myException(Exception ex) {
		log.info("In Global Exception Handler");
		log.error("Exception occurred at {} ", ex);
		return new ResponseEntity<Object>(ex, HttpStatus.BAD_REQUEST);

	}

}
