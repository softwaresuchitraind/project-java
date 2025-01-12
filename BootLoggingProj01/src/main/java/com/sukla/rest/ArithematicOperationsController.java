
package com.sukla.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ArithematicOperationsController
{

	@GetMapping("/div")
	public String div()
	{
		log.info("At begining of div() method");
		try {
			log.trace("Performing Arithmatic operation");
			float result = 100 /0;
			log.trace("Performing Arithmatic operation suceesfully");
			return "result is :" + result;
		} catch (Exception e) {
			log.error("problem in Arithmatic operation" + e.getMessage());
			e.printStackTrace();
			return "problem " + e.getMessage();
		}
	}
}
