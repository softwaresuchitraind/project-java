package com.sukla.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //(@Controller + @ResponseBody)
public class WishMessageOperationController 
{
	
	private static Logger logger = LoggerFactory.getLogger(WishMessageOperationController.class);
	
	@GetMapping("/greet")
	public ResponseEntity<String> showMessge()
	{
		logger.debug("At the beginning of showMessage()");
		String msg = "Good Morning";
		logger.debug("At the End of showMessage()");
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
}
