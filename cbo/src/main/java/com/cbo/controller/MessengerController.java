package com.cbo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MessengerController {
	
	public MessengerController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("messengerMain")
	public String messengerForm() {
		return "messenger/messengerMain";
	}
}
