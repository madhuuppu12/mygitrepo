package com.cinematrics.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class UserController {

	@GetMapping("/")
	public String userHome() {
		return "You are in User home";

	}
	
	
}
