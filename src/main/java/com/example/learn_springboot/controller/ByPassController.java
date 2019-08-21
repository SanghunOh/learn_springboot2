package com.example.learn_springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ByPassController {
	@RequestMapping(value = {"/home", "/login", "/thymeleafLayout"})
	public void byPass() {
	}
}
