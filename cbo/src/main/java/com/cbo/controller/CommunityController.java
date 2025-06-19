package com.cbo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommunityController {
	
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
	
	@GetMapping("communityCreate")
	public String mainCreate() {
		return "community/communityCreate";
	}
	
	@GetMapping("communityList")
	public String CommunityList() {
		return "community/communitylist";
	}
}
