package com.cbo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cbo.community.model.CommunityDTO;
import com.cbo.community.service.CommunityService;

@Controller
public class CommunityController {
	
	@Autowired
	private CommunityService service;
	
	
	public CommunityController() {
		
	}
	
	
	@GetMapping("communityMainNewest")
public ModelAndView CommunityMainNewest() {
		
		List<CommunityDTO> lists=null;
		try {
			lists=service.communityList();
		}catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("lists", lists);
		mav.setViewName("community/CommunityMainNewest");
		
		return mav;
	}
	
	
	
	@GetMapping("communityMainJoin")
	public String mainJoinList() {
		return "community/communityMainJoin";
	}

//전체 목록 조회'
	@GetMapping("communityMainAll")
	public ModelAndView CommunityList() {
		
		List<CommunityDTO> lists=null;
		try {
			lists=service.communityList();
		}catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav=new ModelAndView();
		mav.addObject("lists", lists);
		mav.setViewName("community/communityMainAll");
		return mav;
	}
	
	
	//커뮤니티 생성 get방식 이동
	@GetMapping("communityCreate")
	public String communityCreate() {
		return "community/manage/communityCreate";
	}
	//커뮤니티 생성 post방식
	@PostMapping("communityCreate")
	public ModelAndView insertCommunity(CommunityDTO dto) {
		
	    int result = 0;
	    String msg = null;

	    try {
	        dto.setMember_id(1); //임시로 했음
	        result = service.insertCommunity(dto);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    msg = result > 0 ? "커뮤니티 생성 성공!" : "커뮤니티 생성 실패!";
	    ModelAndView mav = new ModelAndView();
	    mav.addObject("msg", msg); 
	    mav.setViewName("community/communityMsg"); 
	    return mav;
	}
	
	// 커뮤니티 정보 수정
	@GetMapping("community/{id}Update")
	public ModelAndView communityUpdate(@PathVariable("id")String id) {
		//model.addAttribute("id", id);
		ModelAndView mav=new ModelAndView();
		mav.addObject("id", id);
		mav.setViewName("community/manage/communityUpdate");
		return mav;
	}
	
	
	//커뮤니티 멤버 관리
	@GetMapping("communityMember")
	public String communityMember() {
		return "community/manage/communityMember";
	}
	
	
	//커뮤니티 페쇄 URL 이동
	@GetMapping("/community/{id}/close")
	public ModelAndView closePage(@PathVariable("id") String id) {
	    ModelAndView mav = new ModelAndView();
	    mav.addObject("id", id);
	    mav.setViewName("community/manage/communityClose");
	    return mav;
	}
	//커뮤니티 폐쇄 기능
	
	@PostMapping("/community/{id}/close")
	public ModelAndView deleteCommunity(@PathVariable("id") int id) {	
		
		int result = 0;
	    String msg = null;
	    try {
	    	result = service.deleteCommunity(id);
			msg = result > 0 ? "커뮤니티삭제 성공" : "커뮤니티삭제 실패";
			
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg); 
		mav.setViewName("community/communityMsg");
		return mav;
	}
	
	
	
	
	
	
	
}
