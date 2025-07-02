package com.cbo.messenger.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbo.mapper.MessengerMapper;
import com.cbo.member.model.ChatMemberDTO;
import com.cbo.member.model.InviteeDTO;
import com.cbo.messenger.model.*;

@Service
public class ChatMessageServiceImple implements ChatMessageService {
	@Autowired
	private MessengerMapper mapper;
	
	@Override
	public int addMessage(ChatMessageDTO dto) throws Exception {
		int result = mapper.insertMessage(dto);
		return result;
	}
	
	@Override
	public List<MessageListDTO> getMessageList(int room_id) throws Exception {
		List<MessageListDTO> list = mapper.selectMessage(room_id);
		return list;
	}
	
	@Override
	public List<ChatRoomListDTO> getChatRoomList(int member_id) throws Exception {
		List<ChatRoomListDTO> list = mapper.selectChatRoomList(member_id);
		return list;
	}
	
	@Override
	public int createChatRoom(ChatRoomDTO dto) throws Exception {
		int result = mapper.insertChatRoom(dto);
		return result;
	}
	
	@Override
	public int addChatMember(ChatRoom_MemberDTO dto) throws Exception {
		int result = mapper.insertChatMember(dto);
		return result;
	}
	
	@Override
	public List<InviteeDTO> getInvitee(int room_id) throws Exception {
		List<InviteeDTO> list = mapper.selectInvitee(room_id);
		return list;
	}
	
	@Override
	public List<ChatMemberDTO> getChatMember(int room_id) throws Exception {
		List<ChatMemberDTO> list = mapper.selectChatMember(room_id);
		return list;
	}
	
	@Override
	public int delChatMember(ChatRoom_MemberDTO dto) throws Exception {
		int result = mapper.deleteChatMember(dto);
		return result;
	}

}
