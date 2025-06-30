package com.cbo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.cbo.constant.MemberConst;
import com.cbo.member.model.MemberDTO;
import com.cbo.message.model.MessageDTO;
import com.cbo.message.service.MessageService;

@Controller
public class MessageController {
	private final MessageService messageService;
	
	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}
	
	@GetMapping("unreadMessages")
	public ModelAndView unreadMessages(@SessionAttribute(MemberConst.USER_KEY) MemberDTO userInfo) {
		return null;
	}
	
	@GetMapping("receivedMessages")
	public ModelAndView receivedMessages(@SessionAttribute(MemberConst.USER_KEY) MemberDTO userInfo) {
		return null;
	}
	
	@GetMapping("sentMessages")
	public ModelAndView sentMessages(@SessionAttribute(MemberConst.USER_KEY) MemberDTO userInfo) {
		return null;
	}
	
	@GetMapping("sendMessage")
	public String sendMessageForm() {
		return null;
	}
	
	@PostMapping("sendMessage")
	public ModelAndView sendMessage(MessageDTO dto) {
		return null;
	}
	
	@GetMapping("messageContent")
	public ModelAndView messageContent(@RequestParam int id) {
		return null;
	}
	
	@PostMapping("deleteMessage")
	public ModelAndView deleteMessage() {
		return null;
	}
	
	@PostMapping("markAsRead")
	public ModelAndView markAsRead() {
		return null;
	}
}
