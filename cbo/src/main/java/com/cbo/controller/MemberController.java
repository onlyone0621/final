package com.cbo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cbo.member.model.MemberDTO;
import com.cbo.member.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService service;
	
	public MemberController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("memberJoin")
	public String memberJoinForm() {
		return "member/memberJoin";
	}
	
	@ResponseBody
	@GetMapping("memberIdCheck")
	public String checkMemberId(@RequestParam("user_id") String user_id) {
		String userId = null;
		try {
			userId = service.getMemberId(user_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userId;
	}
	
	@PostMapping("memberJoin")
	public ModelAndView memberJoin(MemberDTO dto) {
		int result = 0;
		try {
			result = service.memberJoin(dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView();
		String msg = result > 0 ? "회원가입 성공" : "회원가입 실패";
		mav.addObject("msg", msg);
		mav.setViewName("member/memberMsg");
		return mav;
	}
}
