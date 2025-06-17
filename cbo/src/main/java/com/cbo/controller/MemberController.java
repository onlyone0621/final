package com.cbo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
	
	
	public MemberController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("memberJoin")
	public String memberJoinForm() {
		return "member/memberJoin";
	}
}
