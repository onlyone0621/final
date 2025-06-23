package com.cbo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	public String mainNewest() {
		return "community/communityMainNeWest";
	}
	
	@GetMapping("communityMainJoin")
	public String mainJoinList() {
		return "community/communityMainJoin";
	}

	@GetMapping("communityMainAll")
	public String mainAllList() {
		return "community/communityMainAll";
	}
	
	@GetMapping("communityList")
	public String CommunityList() {
		return "community/communitylist";
	}
	//커뮤니티 생성
	@GetMapping("communityCreate")
	public String communityCreate() {
		return "community/manage/communityCreate";
	}
	
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
	@GetMapping("communityUpdate")
	public String communityUpdate() {
		return "community/manage/communityUpdate";
	}
	//커뮤니티 멤버 관리
	@GetMapping("communityMember")
	public String communityMember() {
		return "community/manage/communityMember";
	}
	//커뮤니티 페쇄
	@GetMapping("communityClose")
	public String communityClose() {
		return "community/manage/communityClose";
	}
}
