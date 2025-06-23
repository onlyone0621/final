package com.cbo.calendar.service;

import java.util.List;

import com.cbo.calendar.model.CalendarDTO;

public interface CalendarService {

	
	public int insertWork(CalendarDTO dto)throws Exception;
	public List<CalendarDTO> selectList()throws Exception;
	public int deleteWork(CalendarDTO dto)throws Exception;
	public int updateWork(CalendarDTO dto)throws Exception;
	
}
