package com.cbo.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cbo.calendar.model.CalendarDTO;
import com.cbo.calendar.service.CalendarService;
import com.cbo.member.model.MemberDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
	
	
	
	/*
	 * @RequestMapping("/workReg") public ModelAndView workReg(CalendarDTO dto) {
	 * 
	 * ModelAndView mav = new ModelAndView(); String msg =""; List<CalendarDTO> list
	 * = null; try { int result = service.insertWork(dto); //msg =
	 * result>0?"등록성공":"등록실패"; } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * mav.addObject("msg","완료"); mav.setViewName("schedule/CalendarMsg");
	 * 
	 * return mav; }
	 */
	
	@PostMapping("/api/calendar/addAllDay")
	@ResponseBody
	public Map<String, Object> addAllDay(HttpServletRequest request, HttpSession session ) {
	    CalendarDTO dto = new CalendarDTO();
	    dto.setTitle(request.getParameter("title"));
	    dto.setContent(request.getParameter("content"));
	    dto.setAllday(1);

	    // 날짜만 받음
	    String startDateStr = request.getParameter("start_date");
	    String endDateStr = request.getParameter("end_date");
	    LocalDate startDate = LocalDate.parse(startDateStr);
	    LocalDate endDate = LocalDate.parse(endDateStr);

	    dto.setStart_date(startDate);
	    dto.setEnd_date(endDate);
	    dto.setStart_time(startDate.atStartOfDay());
	    dto.setEnd_time(endDate.atStartOfDay());

	    // member_id, dept_id 세팅
	    

	    Map<String, Object> result = new HashMap<>();
	    MemberDTO udto = (MemberDTO) session.getAttribute(com.cbo.constant.MemberConst.USER_KEY);
	    String saveid = udto.getUser_id();
	    try {
	    	int dept = service.findDept(saveid);
		    int id = service.findId(saveid);
		    dto.setMember_id(id);
		    dto.setDept_id(dept);
		    
	        int count = service.insertWork(dto);
	        result.put("success", count > 0);
	    } catch (Exception e) {
	        e.printStackTrace();
	        result.put("success", false);
	    }
	    return result;
	}

	@PostMapping("/api/calendar/addTime")
	@ResponseBody
	public Map<String, Object> addTime(HttpServletRequest request, HttpSession session) {
	    CalendarDTO dto = new CalendarDTO();
	    dto.setTitle(request.getParameter("title"));
	    dto.setContent(request.getParameter("content"));
	    dto.setAllday(0);

	    // 날짜+시간 받음
	    String startTimeStr = request.getParameter("start_time");
	    String endTimeStr = request.getParameter("end_time");
	    LocalDateTime startTime = LocalDateTime.parse(startTimeStr);
	    LocalDateTime endTime = LocalDateTime.parse(endTimeStr);

	    dto.setStart_time(startTime);
	    dto.setEnd_time(endTime);

	    // member_id, dept_id 세팅
	    MemberDTO udto = (MemberDTO) session.getAttribute(com.cbo.constant.MemberConst.USER_KEY);
	    String saveid = udto.getUser_id();

	    Map<String, Object> result = new HashMap<>();
	    try {
		    int dept = service.findDept(saveid);
		    int id = service.findId(saveid);
		    dto.setMember_id(id);
		    dto.setDept_id(dept);
	        int count = service.insertTime(dto);
	        result.put("success", count > 0);
	    } catch (Exception e) {
	        e.printStackTrace();
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
	
	@PostMapping("/api/calendar/saveWork")
	@ResponseBody
	public int saveWork(@RequestBody List<CalendarDTO> dto) {
		int result = 0;
		try {
			result = service.saveWork(dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	

	@GetMapping("/api/calendar/events")
	@ResponseBody
	public List<Map<String, Object>> getEvents(HttpSession session) {
	    List<CalendarDTO> list = null;
	    int id = 0;
	    MemberDTO udto = (MemberDTO) session.getAttribute(com.cbo.constant.MemberConst.USER_KEY);
	    String saveid = udto.getUser_id();
	    try {
	    	id = service.findId(saveid);
	        list = service.selectList(id);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    System.out.println(id);

	    List<Map<String, Object>> events = new ArrayList<>();
	    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

	    for (CalendarDTO dto : list) {
	        Map<String, Object> event = new HashMap<>();
	        event.put("id", dto.getId());
	        event.put("title", dto.getTitle());

	        boolean isAllDay = dto.getAllday() == 1;
	        if (isAllDay) {
	            // 종일 일정: 날짜만
	        	event.put("start", dto.getStart_time().format(dateFormatter));
	        	 if (dto.getEnd_time() != null) {
	        	        LocalDate endPlusOne = dto.getEnd_time().toLocalDate().plusDays(1);
	        	        event.put("end", endPlusOne.format(dateFormatter));
	        	    }
	            event.put("allDay", true);
	        } else {
	            // 시간 일정: 날짜+시간
	            event.put("start", dto.getStart_time().format(dateTimeFormatter));
	            if (dto.getEnd_time() != null) event.put("end", dto.getEnd_time().format(dateTimeFormatter));
	            event.put("allDay", false);
	        }
	        event.put("content", dto.getContent());
	        events.add(event);
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
