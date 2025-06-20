package com.cbo.calendar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbo.calendar.model.CalendarDTO;
import com.cbo.mapper.CalendarMapper;

@Service
public class CalendarServiceImple implements CalendarService {

	
	@Autowired
	private CalendarMapper mapper;
	
	
	@Override
	public int insertWork(CalendarDTO dto) throws Exception {
		int count = mapper.insertWork(dto);
		
		return count;
	}
	
	@Override
	public List<CalendarDTO> selectList() throws Exception {
		
		List<CalendarDTO> list = mapper.selectList();
		
		return list;
		
	}
	
}
