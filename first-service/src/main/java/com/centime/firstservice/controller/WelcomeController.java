package com.centime.firstservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
	
	@GetMapping(value="/welcome")
	public ResponseEntity<?> welcomeMethod() {
		
		return ResponseEntity.ok("welcome to Centime First Service");
	}
	

}
