package com.cbo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.cbo.constant.MemberConst;
import com.cbo.constant.MessageConst;
import com.cbo.member.model.MemberDTO;
import com.cbo.member.model.OrganDTO;
import com.cbo.message.model.MessageDTO;
import com.cbo.message.service.MessageService;
import com.cbo.pagination.Pagination;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MessageController {
	private final MessageService messageService;
	
	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}
	
	@GetMapping("unreadMessages")
	public ModelAndView unreadMessages(@SessionAttribute(MemberConst.USER_KEY) MemberDTO userInfo,
			@RequestParam(defaultValue = "1") int curPage,
			HttpServletRequest req) {
		List<MessageDTO> res = null;
		try {
			res = messageService.getUnreadMessages(userInfo.getId(), curPage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("unreadMessages", res);
		
		// Get paging bar
		int maxRows = res != null && res.size() > 0 ? res.get(0).getMax_rows() : 1;
		
		String pageBar = Pagination.makePaging("unreadMessages", maxRows, MessageConst.ROWS, MessageConst.PAGES, curPage);
		mav.addObject("pageBar", pageBar);
		
		// If req is XmlHttpRequest
		if ("XMLHttpRequest".equals(req.getHeader("X-Requested-With"))) {
			mav.setViewName("message/unreadMessages :: tableBody");
			return mav;
		}
		
		mav.setViewName("message/unreadMessages");
		mav.addObject("curPage", curPage);
		mav.addObject("pageName", "unreadMessages");
		
		return mav;
	}
	
	@GetMapping("receivedMessages")
	public ModelAndView receivedMessages(@SessionAttribute(MemberConst.USER_KEY) MemberDTO userInfo,
			@RequestParam(defaultValue = "1") int curPage,
			HttpServletRequest req) {
		List<MessageDTO> res = null;
		try {
			res = messageService.getReceivedMessages(userInfo.getId(), curPage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("receivedMessages", res);
		
		// Get paging bar
		int maxRows = res != null && res.size() > 0 ? res.get(0).getMax_rows() : 1;
		
		String pageBar = Pagination.makePaging("receivedMessages", maxRows, MessageConst.ROWS, MessageConst.PAGES, curPage);
		mav.addObject("pageBar", pageBar);
		
		// If req is XmlHttpRequest
		if ("XMLHttpRequest".equals(req.getHeader("X-Requested-With"))) {
			mav.setViewName("message/receivedMessages :: tableBody");
			return mav;
		}
		
		mav.setViewName("message/receivedMessages");
		mav.addObject("curPage", curPage);
		mav.addObject("pageName", "receivedMessages");
		
		return mav;
	}
	
	@GetMapping("sentMessages")
	public ModelAndView sentMessages(@SessionAttribute(MemberConst.USER_KEY) MemberDTO userInfo,
			@RequestParam(defaultValue = "1") int curPage,
			HttpServletRequest req) {
		List<MessageDTO> res = null;
		try {
			res = messageService.getSentMessages(userInfo.getId(), curPage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView("message/sentMessages");
		mav.addObject("sentMessages", res);
		
		int maxRows = res != null && res.size() > 0 ? res.get(0).getMax_rows() : 1;
		
		String pageBar = Pagination.makePaging("sentMessages", maxRows, MessageConst.ROWS, MessageConst.PAGES, curPage);
		mav.addObject("pageBar", pageBar);
		
		if ("XMLHttpRequest".equals(req.getHeader("X-Requested-With"))) {
			mav.setViewName("message/sentMessages :: tableBody");
			return mav;
		}
		
		mav.setViewName("message/sentMessages");
		mav.addObject("curPage", curPage);
		mav.addObject("pageName", "sentMessages");
		
		return mav;
	}
	
	@GetMapping("sendMessages")
	public String sendMessagesForm(Model model) {
		Map<String, List<OrganDTO>> membersByDept = null;
		
		try {
			membersByDept = messageService.getMembers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("membersByDept", membersByDept);
		return "message/sendMessages";
	}
	
	@PostMapping("sendMessages")
	public ModelAndView sendMessages(MessageDTO dto, List<Integer> receiverIds) {
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
	
	@ResponseBody
	@PostMapping("deleteMessages")
	public int deleteMessages(@RequestBody List<Integer> selectedIds) {
		try {
			return messageService.deleteMessages(selectedIds);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	@ResponseBody
	@PostMapping("markAsRead")
	public int markAsRead(@RequestBody List<Integer> selectedIds) {
		try {
			return messageService.markAsRead(selectedIds);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
}
