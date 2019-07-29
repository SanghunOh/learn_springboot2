/**
 * 
 */
package com.example.learn_springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.learn_springboot.service.ShareDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	// Restful
	@CrossOrigin			// allow to access others site
	@RequestMapping(value = "/ws/{action}", method = { RequestMethod.GET, RequestMethod.POST })
	public Object actionMethod(Map<String, Object> paramMap, @PathVariable String action) {
		
//		List<Object> resultObject = new ArrayList<Object>();
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
	
	@CrossOrigin			// allow to access others site
	@RequestMapping(value = "/remote/{action}", method = { RequestMethod.GET, RequestMethod.POST })
	public Object actionRemoteMethod(Map<String, Object> paramMap, @PathVariable String action) {
		
//		List<Object> resultObject = new ArrayList<Object>();
		Object resultObject = new Object();

		// divided depending on action value
		String viewName = "/organization/list";
		
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> params = new HashMap<String, String>();
		Object tempObject = new Object();
		String targetUri = null;
		String keyId = null;

		if ("weatherMap".equalsIgnoreCase(action)) {		// 도시 날씨
			keyId = "73989af8356ce6d4600e10678529880e";
			targetUri = "https://api.openweathermap.org/data/2.5/weather?id=2172797&appid="+keyId;
			resultObject = restTemplate.getForObject(targetUri, Object.class);
		} else if ("addressMap".equalsIgnoreCase(action)) {	// 도로명주소 우편번호 조회서비스
			params.put("serviceKey", "BoygPZjC27pxm92hSposjnSob2u36vziS1rzIzxkrL9QxmlhB0SMARwLfNlBE3wrE7nnw34zLmmv0a6amvW4xg%3D%3D");
			params.put("searchSe", "dong");
			params.put("srchwrd", "%EC%A3%BC%EC%9B%94%EB%8F%99%20408-1");
			params.put("currentPage", "1");
			params.put("countPerPage", "10");

			targetUri = "http://openapi.epost.go.kr/postal/retrieveNewAdressAreaCdService/retrieveNewAdressAreaCdService/getNewAddressListAreaCd";
			tempObject = restTemplate.getForObject(targetUri, Object.class, params);
			resultObject = ((Map) tempObject).get("NewAddressListResponse");
		} 
 
		return resultObject;	
	}
}
