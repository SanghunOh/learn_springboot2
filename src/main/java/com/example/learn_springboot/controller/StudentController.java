package com.example.learn_springboot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.learn_springboot.model.Student;
import com.example.learn_springboot.repository.StudentRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class StudentController {

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private StudentRepository repository;

	@RequestMapping(value = "/student/{action}", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView actionMethod(@RequestParam Map<String, Object> paramMap, @PathVariable String action,
			ModelAndView modelandView) {

		String viewName = "/student/" + action ;

		Map<String, Object> resultMap = new HashMap<String, Object>() ;
		List<Object> resultList = new ArrayList<Object>();

		// divided depending on action value
		if ("edit".equals(action)) {
		} else if ("update".equals(action)) {
			resultMap = (Map) repository.getOne((Long)paramMap.get("id"));
			paramMap.put("action", action);
		} else if ("merge".equals(action)) {
			repository.saveAndFlush(new Student(paramMap.get("id"), paramMap.get("name"), paramMap.get("passportNumber")));
		} else if ("read".equals(action)) {
			resultMap = (Map) repository.getOne((Long)paramMap.get("id"));
		} else if ("list".equals(action)) {
		resultList = (List) repository.findAll();
		} else if ("delete".equals(action)) {
			repository.deleteById((Long)paramMap.get("id"));;
		} 
		
		modelandView.setViewName(viewName);

		modelandView.addObject("paramMap", paramMap);
		modelandView.addObject("resultMap", resultMap);
		modelandView.addObject("resultList", resultList);
		return modelandView;
	}
}
