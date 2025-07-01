package com.cbo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cbo.member.model.MemberDTO;
import com.cbo.messenger.model.*;

import com.cbo.messenger.service.ChatMessageService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MessengerController {
	@Autowired
	ChatMessageService service;
	
	public MessengerController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("messengerMain")
	public ModelAndView messengerForm(HttpSession session) {
		MemberDTO dto = (MemberDTO) session.getAttribute(com.cbo.constant.MemberConst.USER_KEY);
		List<ChatRoomListDTO> lists = null;
		try {
			lists = service.getChatRoomList(dto.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("chatList", lists);
		mav.setViewName("messenger/messengerMain");
		return mav;
	}
	
	@ResponseBody
	@GetMapping("chatContent")
	public String chatContent(@RequestParam("id") int id) {
	    List<MessageListDTO> lists = null;
	    StringBuilder sb = new StringBuilder();

	    try {
	        lists = service.getMessageList(id);
	        if (lists != null) {
	            for (MessageListDTO msg : lists) {
	                sb.append(msg.getName())
	                  .append(" : ")
	                  .append(msg.getContent())
	                  .append("\n");
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return sb.toString();
	}
}
