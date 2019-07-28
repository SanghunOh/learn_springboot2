package com.example.learn_springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles requests for the application Hellow Restful.
 */
@RestController
public class HellowController {
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/resthellow")
	public String hellow() {		
		return "Welcome to Hellow World !! ";
	}
}
