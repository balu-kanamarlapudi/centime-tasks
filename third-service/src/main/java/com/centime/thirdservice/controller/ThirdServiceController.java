package com.centime.thirdservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.centime.thirdservice.model.FirstServiceRequest;

@RestController
public class ThirdServiceController {
	
	Logger logger = LoggerFactory.getLogger(ThirdServiceController.class);
	
	@GetMapping(value="welcome")
	public ResponseEntity<?> welcome() {
		return ResponseEntity.ok("welcome to Centime Third Service.");
	}
	
	@PostMapping(value="api/third-service/concatenated-response")
	public ResponseEntity<?> getResponse(@RequestBody FirstServiceRequest request) {
		
		logger.trace("In Second Service");
		String response = request.getName() + " " + request.getSurName();
		
		return ResponseEntity.ok(response);
	}
	

}
