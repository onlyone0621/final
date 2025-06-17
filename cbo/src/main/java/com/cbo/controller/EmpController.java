package com.cbo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmpController {

	
	
	
	@RequestMapping("/myCalendar")
	public String myCalendar() {
		
		return "schedule/myCalendar";
	}
}
