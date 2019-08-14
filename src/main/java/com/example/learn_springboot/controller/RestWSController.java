/**
 * 
 */
package com.example.learn_springboot.controller;

import java.util.HashMap;
import java.util.Map;

import com.example.learn_springboot.service.OpenweatherService;
import com.example.learn_springboot.service.ShareDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author ohsanghun
 *
 */
@RestController
public class RestWSController {

	@Autowired
	private ShareDataService shareDataService;

	@Autowired
	private OpenweatherService openweatherService;

	// Restful
	@CrossOrigin // allow to access others site
	@RequestMapping(value = "/ws/{action}", method = { RequestMethod.GET, RequestMethod.POST })
	public Object actionMethod(Map<String, Object> paramMap, @PathVariable String action) {

		// List<Object> resultObject = new ArrayList<Object>();
		Object resultObject = new Object();

		// divided depending on action value
		String viewName = "/organization/list";

		if ("organizationList".equalsIgnoreCase(action)) {
			resultObject = shareDataService.getList(viewName, paramMap);
		} else if ("organizationMap".equalsIgnoreCase(action)) {
			resultObject = shareDataService.getObject(viewName, paramMap);
		}

		return resultObject;
	}

	@CrossOrigin // allow to access others site
	@RequestMapping(value = "/remote/{action}", method = { RequestMethod.GET, RequestMethod.POST })
	public Object actionRemoteMethod(@RequestParam Map<String, Object> paramMap, @PathVariable String action) {

		// List<Object> resultObject = new ArrayList<Object>();
		Object resultObject = new Object();

		// divided depending on action value
		String viewName = "/organization/list";

		RestTemplate restTemplate = new RestTemplate();
		Object receiveObject = new HashMap<String, String>();
		String targetUri = null;
		String keyId = null;

		if ("weatherMap".equalsIgnoreCase(action)) { // 도시 날씨
			keyId = "73989af8356ce6d4600e10678529880e";
			targetUri = "https://api.openweathermap.org/data/2.5/weather?id=2172797&appid=" + keyId;
			resultObject = restTemplate.getForObject(targetUri, Object.class);
		} else if ("weatherList".equalsIgnoreCase(action)) { // 도로명주소 우편번호 조회서비스
			keyId = "73989af8356ce6d4600e10678529880e";
			targetUri = "https://samples.openweathermap.org/data/2.5/history/city?q=London,UK&appid=" + keyId;
			receiveObject = restTemplate.getForObject(targetUri, Object.class);
			resultObject = ((Map) receiveObject).get("list");
		} else if ("addressMap".equalsIgnoreCase(action)) { // 도로명주소 우편번호 조회서비스
			resultObject = openweatherService.addressMap(paramMap);
		}

		return resultObject;
	}
}
