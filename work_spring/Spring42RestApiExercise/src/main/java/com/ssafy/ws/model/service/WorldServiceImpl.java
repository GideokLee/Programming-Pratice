package com.ssafy.ws.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ws.model.repo.ObjectRepo;

@Service
public class WorldServiceImpl implements WorldService {

	@Autowired
	private ObjectRepo repo;

	@Override
	public Map<String, Object> dbDay0103(String code) {
		return repo.dbDay0103(code);
	} 

}
