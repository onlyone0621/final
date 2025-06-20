package com.cbo.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	public String calendarWorkReg(@RequestParam("start_date") String start_date,@RequestParam("end_date") String end_date, Model model) {
		
		//if(end_date==)
		model.addAttribute("start_date",start_date);
		model.addAttribute("end_date",end_date);
		
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

       // 리스트나 그런곳 넣어와서 for문 돌리고 이벤트 이런식으로 돌린다음에 list.title뭐 이런거 해서하면 될거같은데?
        for(int i = 0; i<list.size(); i++) {
        	
        Map<String, Object> event = new HashMap<>();
        if(list.get(i).getEnd_time()!=null||!(list.get(i).getEnd_time().equals(""))) {
        event.put("title", list.get(i).getTitle());
        event.put("start", list.get(i).getStart_time());
        event.put("end", list.get(i).getEnd_time());
        events.add(event);
        }else {
        	event.put("title", list.get(i).getTitle());
            event.put("start", list.get(i).getStart_time());
        }
        
        }

        return events;
    }
	
	@RequestMapping("/calendarOption")
	public String CalendarOption(@RequestParam("start_date") String start_date
			,@RequestParam("end_date") String end_date,@RequestParam("title")String title, Model model) {
		
		model.addAttribute("start_date",start_date);
		model.addAttribute("end_date",end_date);
		model.addAttribute("title",title);
		
		return "schedule/calendarOption";
	}
}
