package com.centime.firstservice.controller;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ConnectException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.centime.firstservice.model.FirstServiceRequest;

@RestController
public class FirstServiceController {

	String secondService = "http://localhost:8092/api/second-service/";
	String thirdService = "http://localhost:8093/api/third-service/";
	
	Logger logger = LoggerFactory.getLogger(FirstServiceController.class);
	
	@GetMapping(value="api/status")
	public ResponseEntity<?> getStatus() {
		logger.trace("Application Status");
		return ResponseEntity.ok("Up");
	}
	
	@PostMapping(value="api/get-concatenated-response")
	public ResponseEntity<?> concatenatedResponse(@RequestBody FirstServiceRequest request) {
		logger.trace("In First Service");
		String secondServiceResponse = "";
		String thirdServiceResponse = "";
		
		logger.trace("calling Second service");
		RestTemplate rest = new RestTemplate();
		String restUri = secondService + "get-hello" ;
		try {
			ResponseEntity<String> response1 = rest.getForEntity(restUri, String.class);
			secondServiceResponse =  response1.getBody();
		} catch(ResourceAccessException ex) {
			logger.trace(ex.getRootCause().getMessage());
//			logger.trace(ex.getResponseBodyAsString());
			secondServiceResponse = "second service: " + ex.getRootCause().getMessage();;
		}
		
		
		logger.trace("calling Third service");
		RestTemplate rest2 = new RestTemplate();
		String restUri2 = thirdService + "concatenated-response" ;
		try {
		ResponseEntity<String> response2 = rest2.postForEntity(restUri2, request, String.class);
		thirdServiceResponse = response2.getBody();
		} catch(ResourceAccessException ex) {
			logger.trace(ex.getRootCause().getMessage());
			thirdServiceResponse = "third service: " + ex.getRootCause().getMessage();;
		}
		
		return ResponseEntity.ok(secondServiceResponse + " " + thirdServiceResponse);
	}
	
	@GetMapping(value="api/first-service-logfiledata")
	public ResponseEntity<?> getLog() {
//		File file = new File("mylog.log");
//		String content = "";
//		try {
//			content = new String ( Files.readAllBytes( Paths.get("mylog.log") ) );
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		 StringBuilder contentBuilder = new StringBuilder();
		    try 
		    {
		    	Stream<String> stream = Files.lines( Paths.get("mylog.log"), StandardCharsets.UTF_8);
		        stream.forEach(s -> contentBuilder.append(s).append("\n"));
		    }
		    catch (IOException e) 
		    {
		        e.printStackTrace();
		    }
		return ResponseEntity.ok(contentBuilder.toString());
	}
	
}
