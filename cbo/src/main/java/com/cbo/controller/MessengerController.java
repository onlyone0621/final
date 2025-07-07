package com.cbo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cbo.member.model.ChatMemberDTO;
import com.cbo.member.model.InviteeDTO;
import com.cbo.member.model.MemberDTO;
import com.cbo.member.model.OrganDTO;
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
	public List<MessageListDTO> chatContent(@RequestParam("id") int id, @RequestParam("room_id") int room_id) {
	    List<MessageListDTO> lists = new ArrayList<>();
	    Map<String, Object> param = new HashMap<>();
	    param.put("id", id);
	    param.put("room_id", room_id);
	    try {
	        lists = service.getMessageList(param);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return lists;
	}
	
	@ResponseBody
	@GetMapping("createPrivateChatRoom")
	public String createOneToOneChatRoom(@RequestParam("target_id") int target_id, 
	                                     @RequestParam("type") String type,
	                                     @RequestParam("target_name") String target_name,
	                                     HttpSession session) {
		MemberDTO udto = (MemberDTO) session.getAttribute(com.cbo.constant.MemberConst.USER_KEY);
		int roomId = 0;

		try {
			Integer existingRoomId = service.findPrivateRoom(udto.getId(), target_id);
			if (existingRoomId != null) {
				return existingRoomId.toString(); 
			}

			ChatRoomDTO cdto = new ChatRoomDTO();
			cdto.setType("private");
			cdto.setName(udto.getName() + "-" + target_name);  // 필요시 유저 정보 가져와 이름 변경
			cdto.setDescription(udto.getName() + "-" + target_name);
			service.createChatRoom(cdto);  // 생성 시 ID 자동 생성됨 (mybatis useGeneratedKeys 등)

			service.addChatMember(new ChatRoom_MemberDTO(cdto.getId(), udto.getId(), null));
			service.addChatMember(new ChatRoom_MemberDTO(cdto.getId(), target_id, null));

			roomId = cdto.getId();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return String.valueOf(roomId);
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
				ChatRoom_MemberDTO cmdto = new ChatRoom_MemberDTO(cdto.getId(), udto.getId(), null);
				addChatMember = service.addChatMember(cmdto);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@ResponseBody
	@GetMapping("memberInvite")
	public List<InviteeDTO> memberInviteForm(@RequestParam("room_id")int room_id){
		List<InviteeDTO> lists = null;
		try {
			lists = service.getInvitee(room_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lists;
	}
	
	@PostMapping("memberInvite")
	@ResponseBody
	public ResponseEntity<?> memberInvite(@RequestBody Map<String, Object> m) {
	    int roomId;
	    try {
	        roomId = Integer.parseInt(String.valueOf(m.get("room_id")));
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().body("room_id 값이 올바르지 않습니다.");
	    }

	    Object idsObj = m.get("member_ids");
	    List<Integer> memberIds;

	    try {
	        if (idsObj instanceof List<?>) {
	            memberIds = ((List<?>) idsObj).stream()
	                .map(id -> Integer.valueOf(String.valueOf(id)))
	                .collect(Collectors.toList());
	        } else {
	            return ResponseEntity.badRequest().body("member_ids는 리스트여야 합니다.");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().body("member_ids 파싱 오류: " + e.getMessage());
	    }

	    if (memberIds.isEmpty()) {
	        return ResponseEntity.badRequest().body("No members selected");
	    }

	    for (Integer memberId : memberIds) {
	        ChatRoom_MemberDTO dto = new ChatRoom_MemberDTO(roomId, memberId, null);
	        try {
	            service.addChatMember(dto);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                                 .body("초대 실패: " + memberId);
	        }
	    }

	    return ResponseEntity.ok(Collections.singletonMap("message", "초대 완료"));
	}
	
	@ResponseBody
	@GetMapping("showChatMember")
	public List<ChatMemberDTO> showChatMember(@RequestParam("room_id")int room_id) {
		List<ChatMemberDTO> lists = null;
		try {
			lists = service.getChatMember(room_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lists;
	}
	
	@ResponseBody
	@GetMapping("outChatRoom")
	public String outChatRoom(ChatRoom_MemberDTO dto) {
		int result = 0;
		String msg = "";
		try {
			result = service.delChatMember(dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		msg = result > 0 ? "성공" : "실패";
		return msg;
	}
	
	@ResponseBody
	@GetMapping("memberDepartmentList")
	public List<OrganDTO> memberDepartmentList(@RequestParam("id")int id){
		List<OrganDTO> lists = null;
		try {
			lists = service.getMembers(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lists;
	}
}
