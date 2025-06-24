package com.cbo.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cbo.calendar.model.CalendarDTO;
import com.cbo.calendar.service.CalendarService;

@Controller
public class CalendarController {

	@Autowired
	private CalendarService service;
	
	@RequestMapping("/myCalendar")
	public String myCalendar() {
		
		return "schedule/myCalendar";
	}
	
	@RequestMapping("/calendarWorkReg") // 달력눌러서 일정 등록
	public String calendarWorkReg(@RequestParam("start_time") String start_time,@RequestParam("end_time") String end_time, Model model) {
		
		//if(end_date==)
		model.addAttribute("start_time",start_time);
		model.addAttribute("end_time",end_time);
		
		return "schedule/calendarWorkReg";
	}
	
	@RequestMapping("/workReg")
	public ModelAndView workReg(CalendarDTO dto) {
		
		ModelAndView mav = new ModelAndView();
		String msg ="";
		List<CalendarDTO> list = null;
		try {
			int result = service.insertWork(dto);
			//msg = result>0?"등록성공":"등록실패";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("msg","완료");
		mav.setViewName("schedule/CalendarMsg");
		
		return mav;
	}
	
	@PostMapping("/api/calendar/add")
	@ResponseBody
	public Map<String, Object> addEvent(CalendarDTO dto) {
	    Map<String, Object> result = new HashMap<>();
	    try {
	        int count = service.insertWork(dto); 
	        result.put("success", count > 0);
	    } catch (Exception e) {
	        result.put("success", false);
	    }
	    return result;
	}
	
	@PostMapping("/api/calendar/deleteWork")
	@ResponseBody
	public int calendarOption(CalendarDTO dto) {
		
		int result = 0;
		
		try {
			result = service.deleteWork(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	@PostMapping("/api/calendar/updateWork")
	@ResponseBody
	public int calendarUpdate(CalendarDTO dto) {
		
		int result = 0;
		
		try {
			result = service.updateWork(dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	
	@GetMapping("/api/calendar/events")
	@ResponseBody //json으로 받음
    public List<Map<String, Object>> getEvents() {
		
		List<CalendarDTO> list = null;
		try {
			list = service.selectList();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		List<Map<String, Object>> events = new ArrayList<>();

		for(int i = 0; i<list.size(); i++) {

		Map<String, Object> event = new HashMap<>();
		if(list.get(i).getEnd_time()!=null&&!(list.get(i).getEnd_time().equals(""))) {
		event.put("id", list.get(i).getId());
		event.put("title", list.get(i).getTitle());
		event.put("start", list.get(i).getStart_time());
		event.put("end", list.get(i).getEnd_time());
		if(list.get(i).getContent()!=null&&!(list.get(i).getContent().equals(""))) {
		event.put("content",list.get(i).getContent());
		}
		events.add(event);
		}else {
		event.put("id", list.get(i).getId());
		event.put("title", list.get(i).getTitle());
		event.put("start", list.get(i).getStart_time());
		if(list.get(i).getContent()!=null&&!(list.get(i).getContent().equals(""))) {
		event.put("content",list.get(i).getContent());
		}
		}

		}

		return events;
		}
	
	@RequestMapping("/calendarOptionView")
	public String CalendarOptionView(@RequestParam("start_time") String start_date
			,@RequestParam("end_time") String end_date,@RequestParam("title")String title,
			@RequestParam("content")String content, 
			@RequestParam("id")String id,Model model) {
		
		model.addAttribute("id",id);
		model.addAttribute("start_time",start_date);
		model.addAttribute("end_time",end_date);
		model.addAttribute("title",title);
		model.addAttribute("content",content);
		
		return "schedule/calendarOption";
	}
	
	

}
