package com.cbo.mapper;

import java.util.List;

import com.cbo.calendar.model.CalendarDTO;

public interface CalendarMapper {

	public int insertWork(CalendarDTO dto)throws Exception;
	public int insertTime(CalendarDTO dto)throws Exception;
	public List<CalendarDTO> selectList(int id)throws Exception;
	public int deleteWork(CalendarDTO dto)throws Exception;
	public int updateWork(CalendarDTO dto)throws Exception;
	public int saveWork(CalendarDTO dto)throws Exception;
	public int findId(String saveid)throws Exception;
	public int findDept(String saveid)throws Exception;
}
