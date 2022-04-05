package com.ssafy.springtest.service;

import java.util.*;

import com.ssafy.springtest.dto.CountryStatusDto;


public interface CountryStatusService {
	
	List<CountryStatusDto> select();
	
	CountryStatusDto search(String name);
	
	int insert(CountryStatusDto dto);
	
	int update(CountryStatusDto dto);
	
	int delete(String name);
	
	List<CountryStatusDto> selectByOrder();
}
