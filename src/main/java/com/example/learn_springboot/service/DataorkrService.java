package com.example.learn_springboot.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DataorkrService {
    public Object epostMap(Map param) {
        RestTemplate restTemplate = new RestTemplate();
        Map params = new HashMap<String, String>();
        Map receiveObject = new HashMap<String, String>();
        String targetUri = null;
        String keyId = null;

        params.put("serviceKey",
                "BoygPZjC27pxm92hSposjnSob2u36vziS1rzIzxkrL9QxmlhB0SMARwLfNlBE3wrE7nnw34zLmmv0a6amvW4xg%3D%3D");
        params.put("searchSe", "dong");
        params.put("srchwrd", "주월동%20408-1");
        params.put("currentPage", "1");
        params.put("countPerPage", "10");

        targetUri = "http://openapi.epost.go.kr/postal/retrieveNewAdressAreaCdService/retrieveNewAdressAreaCdService/getNewAddressListAreaCd";
        receiveObject = (Map) restTemplate.getForObject(targetUri, Object.class, params);
        Object resultObject = (Map) receiveObject.get("NewAddressListResponse");

        return resultObject;
    }
}