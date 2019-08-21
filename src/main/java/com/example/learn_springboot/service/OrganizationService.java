package com.example.learn_springboot.service;

import java.util.HashMap;
import java.util.Map;

import com.example.learn_springboot.repository.ShareDao;
import com.example.learn_springboot.util.CommonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService{

//	@Autowired
//	private ShareDao dao;
	
	@Autowired
	private ShareDao dao;

	@Autowired
	private CommonUtil commonUtil;

	public Object getList(Object dataMap) {
		String sqlMapId = "organization.list";

		Map resultObject = new HashMap<>();
		resultObject.put("resultList", dao.getList(sqlMapId, dataMap));
		
		return resultObject;
	}

	public Object getObject(Object dataMap) {
		String sqlMapId = "organization.read";

		Object resultObject = dao.getObject(sqlMapId, dataMap);
		
		return resultObject;
	}

	public Object saveObject(Map<String, Object> dataMap) {
		String uniqueSequence = (String) dataMap.get("ORGANIZATION_SEQ");
		
		if("".equals(uniqueSequence)){
			uniqueSequence = commonUtil.getUniqueSequence();
		}
		dataMap.put("ORGANIZATION_SEQ", uniqueSequence);
		
		String sqlMapId = "organization.merge";

		Integer resultKey = (Integer) dao.saveObject(sqlMapId, dataMap);
		
		sqlMapId = "organization.read";
		
		Object resultObject = dao.getObject(sqlMapId, dataMap);

		return resultObject;
	}

	public Object deleteObject(Object dataMap) {
		String sqlMapId = "organization.delete";

		Integer resultKey = (Integer) dao.deleteObject(sqlMapId, dataMap);

		sqlMapId = "organization.list";
		
		Map resultObject = new HashMap();
		resultObject.put("resultList", dao.getList(sqlMapId, dataMap));
		
		return resultObject;
	}
}
