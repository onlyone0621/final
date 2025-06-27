package com.cbo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cbo.messenger.model.ChatMessageDTO;
import com.cbo.messenger.model.MessageListDTO;
import com.cbo.messenger.service.ChatMessageService;

@Controller
public class MessengerController {
	@Autowired
	ChatMessageService service;
	
	public MessengerController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("messengerMain")
	public ModelAndView messengerForm() {
		List<MessageListDTO> lists = null;
		try {
			lists = service.getMessageList(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		if (lists != null) {
			for (MessageListDTO msg : lists) {
				sb.append(msg.getName()).append(" : ").append(msg.getContent()).append("\n");
			}
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("msgList", sb.toString());
		mav.setViewName("messenger/messengerMain");
		return mav;
	}
}
