package com.centime.secondservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecondServiceController {
	
	Logger logger = LoggerFactory.getLogger(SecondServiceController.class);
	
	@GetMapping(value="/welcome")
	public ResponseEntity<?> welcome() {
		
		return ResponseEntity.ok("Welcome to Centime Second Service");
	}
	
	@GetMapping(value="/api/second-service/get-hello")
	public ResponseEntity<?> getHello() {
		logger.trace("In Third Service");
		
		return ResponseEntity.ok("Hello");
	}

}
