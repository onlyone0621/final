package com.cbo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.cbo.constant.MemberConst;
import com.cbo.constant.MessageConst;
import com.cbo.member.model.MemberDTO;
import com.cbo.message.model.MessageDTO;
import com.cbo.message.service.MessageService;
import com.cbo.pagination.Pagination;

@Controller
public class MessageController {
	private final MessageService messageService;
	
	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}
	
	@GetMapping("unreadMessages")
	public ModelAndView unreadMessages(@SessionAttribute(MemberConst.USER_KEY) MemberDTO userInfo,
			@RequestParam(defaultValue = "1") int curPage) {
		List<MessageDTO> res = null;
		try {
			res = messageService.getUnreadMessages(userInfo.getId(), curPage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView("message/receivedMessages");
		mav.addObject("unreadMessages", res);
		
		int maxRows = res != null && res.size() > 0 ? res.get(0).getMax_rows() : 1;
		
		String pageBar = Pagination.makePaging("unreadMessages", maxRows, MessageConst.ROWS, MessageConst.PAGES, curPage);
		mav.addObject("pageBar", pageBar);
		
		return mav;
	}
	
	@GetMapping("receivedMessages")
	public ModelAndView receivedMessages(@SessionAttribute(MemberConst.USER_KEY) MemberDTO userInfo,
			@RequestParam(defaultValue = "1") int curPage) {
		List<MessageDTO> res = null;
		try {
			res = messageService.getReceivedMessages(userInfo.getId(), curPage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView("message/receivedMessages");
		mav.addObject("receivedMessages", res);
		
		int maxRows = res != null && res.size() > 0 ? res.get(0).getMax_rows() : 1;
		
		String pageBar = Pagination.makePaging("receivedMessages", maxRows, MessageConst.ROWS, MessageConst.PAGES, curPage);
		mav.addObject("pageBar", pageBar);
		
		return mav;
	}
	
	@GetMapping("sentMessages")
	public ModelAndView sentMessages(@SessionAttribute(MemberConst.USER_KEY) MemberDTO userInfo,
			@RequestParam(defaultValue = "1") int curPage) {
		List<MessageDTO> res = null;
		try {
			res = messageService.getSentMessages(userInfo.getId(), curPage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView("message/receivedMessages");
		mav.addObject("sentMessages", res);
		
		int maxRows = res != null && res.size() > 0 ? res.get(0).getMax_rows() : 1;
		
		String pageBar = Pagination.makePaging("sentMessages", maxRows, MessageConst.ROWS, MessageConst.PAGES, curPage);
		mav.addObject("pageBar", pageBar);
		
		return mav;
	}
	
	@GetMapping("sendMessage")
	public String sendMessageForm(Model model) {
		return "message/sendMessages";
	}
	
	@PostMapping("sendMessage")
	public ModelAndView sendMessage(MessageDTO dto, List<Integer> receiverIds) {
		boolean isSent = false; 
		try {
			isSent = messageService.sendMessages(dto, receiverIds);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String msg = isSent ? "메시지 전송 성공" : "메시지 전송 실패";
		ModelAndView mav = new ModelAndView("message/messageMsg");
		mav.addObject("msg", msg);
		mav.addObject("dest", "receivedMessages");
		return mav;
	}
	
	@GetMapping("messageContent")
	public ModelAndView messageContent(@RequestParam int id) {
		ModelAndView mav = new ModelAndView();
		MessageDTO res = null;
		try {
			res = messageService.getMessageContent(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (res == null) {
			mav.setViewName("message/messageMsg");
			mav.addObject("msg", "삭제된 메시지 또는 오류");
			mav.addObject("dest", "receivedMessages");
			return mav;
		}
		
		mav.setViewName("message/messageContent");
		mav.addObject("messageContent", res);
		return mav;
	}
	
	@PostMapping("deleteMessage")
	public ModelAndView deleteMessage(List<Integer> selectedIds) {
		return null;
	}
	
	@PostMapping("markAsRead")
	public ModelAndView markAsRead(List<Integer> selectedIds) {
		return null;
	}
}
