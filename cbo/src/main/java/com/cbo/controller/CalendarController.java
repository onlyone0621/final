package com.cbo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CalendarController {

	@RequestMapping("/myCalendar")
	public String myCalendar() {
		
		return "schedule/myCalendar";
	}
	
	@RequestMapping("/calendarWorkReg")
	public String calendarWorkReg() {
		
		
		return "schedule/calendarWorkReg";
	}
}
