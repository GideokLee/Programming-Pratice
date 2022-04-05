package com.ssafy.ws.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ws.model.service.WorldService;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api/world")
public class WorldRestController {
	
	@Autowired
	private WorldService wService;
	
	@GetMapping("/dbDay0103/{code}")
	public ResponseEntity<?> dbDay0103(@PathVariable String code) {
		Map<String, Object> result = wService.dbDay0103(code);
		
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
}
