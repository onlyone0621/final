package com.cbo.controller;

import java.util.ArrayList;
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
	public List<MessageListDTO> chatContent(@RequestParam("id") int id) {
	    List<MessageListDTO> lists = new ArrayList<>();
	    try {
	        lists = service.getMessageList(id);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return lists;
	}
	
	@ResponseBody
	@GetMapping("createChatRoom")
	public void createChatRoom(ChatRoomDTO cdto, HttpSession session) {
		MemberDTO udto = (MemberDTO) session.getAttribute(com.cbo.constant.MemberConst.USER_KEY);
		int craeteChatRoom = 0;
		int addChatMember = 0;
		try {
			craeteChatRoom = service.createChatRoom(cdto);
			if(craeteChatRoom > 0) {
				System.out.println(cdto.getId()+""+udto.getId());
				ChatRoom_MemberDTO cmdto = new ChatRoom_MemberDTO(cdto.getId(), udto.getId(), null);
				addChatMember = service.addChatMember(cmdto);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
