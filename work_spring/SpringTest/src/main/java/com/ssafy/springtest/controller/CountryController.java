package com.ssafy.springtest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.springtest.dto.CountryStatusDto;
import com.ssafy.springtest.service.CountryStatusService;

@CrossOrigin(allowCredentials = "true", originPatterns = { "*" })
@RestController
@RequestMapping("/")
public class CountryController {
	@Autowired
	private CountryStatusService cService;
	
	@GetMapping("/country-status")
	public ResponseEntity<?> selectCountryList(){
		List<CountryStatusDto> list = cService.select();
		
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<CountryStatusDto>>(list, HttpStatus.OK);
		}
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/country-status/{국가명}")
	public ResponseEntity<?> searchCountry(@PathVariable("국가명") String cname){
		CountryStatusDto dto = cService.search(cname);

		if(dto != null) {
			return new ResponseEntity<CountryStatusDto>(dto, HttpStatus.OK);
		}
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/insert-country-status")
	public ResponseEntity<?> insertCountry(@RequestBody CountryStatusDto dto){
		int result = cService.insert(dto);
		if(result > 0) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	}
	
	@PutMapping("/update-country-status")
	public ResponseEntity<?> updateCountry(@RequestBody CountryStatusDto dto){
		int result = cService.update(dto);
		if(result > 0) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	}
	
	@DeleteMapping("/delete-country-status/{국가명}")
	public ResponseEntity<?> deleteCountry(@PathVariable("국가명") String cname){
		int result = cService.delete(cname);
		if(result > 0) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	}
	
	@GetMapping("/country-status-by-order")
	public ResponseEntity<?> selectByOrdert(){
		List<CountryStatusDto> list = cService.selectByOrder();
		
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<CountryStatusDto>>(list, HttpStatus.OK);
		}
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
