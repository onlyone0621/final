package com.cbo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddrController {

	
	@GetMapping("addrMain")
	public String addrMain() {
		
		
		return "addr/addrMain";
	}
	
	
}
