/**
 * It's Designed For incubation Center
 * @author ohsanghun
 * @version     %I%, %G%
 * @since       1.0
 */
package com.example.learn_springboot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.learn_springboot.service.OrganizationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ohsanghun
 * get it Mapping classlevel (JavaBean, HttpServletRequest, Model, View, ModelAndView)
 */

@Controller
public class OrganizationController {

	private final static String MAPPING = "/organization/";

	@Autowired
	private OrganizationService service;
    
	// Receive Parameters from Html Using @RequestParam Map with @PathVariable
	@RequestMapping(value = MAPPING+"{action}", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView actionMethod(@RequestParam Map<String, Object> paramMap, @PathVariable String action,
			ModelAndView modelandView) {

		String viewName = MAPPING + action;
		String forwardView = (String) paramMap.get("forwardView") ;

		Object resultMap = new HashMap<String, Object>() ;

		// divided depending on action value
		if ("edit".equalsIgnoreCase(action)) {
			((Map<String, Object>) resultMap).put("PARENT_ORGANIZATION_SEQ", paramMap.get("PARENT_ORGANIZATION_SEQ"));
		} else if ("update".equalsIgnoreCase(action)) {
			resultMap = service.getObject(paramMap);
			paramMap.put("action", action);
		} else if ("merge".equalsIgnoreCase(action)) {
			resultMap = service.saveObject(paramMap);
		} else if ("read".equalsIgnoreCase(action)) {
			resultMap = service.getObject(paramMap);
		} else if ("list".equalsIgnoreCase(action)) {
			resultMap = service.getList(paramMap);
		} else if ("delete".equalsIgnoreCase(action)) {
			resultMap = service.deleteObject(paramMap);
		} 

		if(forwardView != null){
			viewName = forwardView;
		}

		modelandView.setViewName(viewName);

		modelandView.addObject("paramMap", paramMap);
		modelandView.addObject("resultMap", resultMap);
		return modelandView;
	}
}
