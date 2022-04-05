package com.ssafy.springtest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.springtest.dto.CountryStatusDto;
import com.ssafy.springtest.mapper.CountryStatusMapper;

@Service
public class CountryStatusServiceImpl implements CountryStatusService{

	@Autowired
	private CountryStatusMapper cMapper;
	
	
	@Override
	public List<CountryStatusDto> select() {
		return cMapper.select();
	}


	@Override
	public CountryStatusDto search(String name) {
		return cMapper.search(name);
	}


	@Override
	public int insert(CountryStatusDto dto) {
		return cMapper.insert(dto);
	}


	@Override
	public int update(CountryStatusDto dto) {
		return cMapper.update(dto);
	}


	@Override
	public int delete(String name) {
		return cMapper.delete(name);
	}


	@Override
	public List<CountryStatusDto> selectByOrder() {
		return cMapper.selectByOrder();
	}

}
