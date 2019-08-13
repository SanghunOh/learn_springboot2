package com.example.learn_springboot.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles requests for the application Hellow Restful.
 */
@RestController
public class HellowController {

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@CrossOrigin // allow to access others site
	@RequestMapping(value = "/resthellow")
	public String hellow() {
		return "Welcome to Hellow World !! ";
	}

	@CrossOrigin // allow to access others site
	@RequestMapping(value = "/api/ajax", method = RequestMethod.POST)
	public String getParamWithAjax(@RequestParam Map<String, Object> paramMap) {
		String str = (String) paramMap.get("title");
		return "Welcome to Hellow World !! ";
	}

	@CrossOrigin // allow to access others site
	@RequestMapping(value = "/api/ajaxReturnParamMap", method = RequestMethod.POST)
	public Map getAndReturnParamWithAjax(@RequestParam Map<String, Object> paramMap) {
		String str = (String) paramMap.get("title");
		return paramMap;
	}
}
