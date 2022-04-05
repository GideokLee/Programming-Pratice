package com.ssafy.springtest.mapper;

import java.util.List;

import com.ssafy.springtest.dto.CountryStatusDto;


public interface CountryStatusMapper {
	
	List<CountryStatusDto> select();
	
	CountryStatusDto search(String name);
	
	int insert(CountryStatusDto dto);
	
	int update(CountryStatusDto dto);
	
	int delete(String name);
	
	List<CountryStatusDto> selectByOrder();
}
