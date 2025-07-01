package com.cbo.messenger.service;

import java.util.List;
import java.util.Map;

import com.cbo.messenger.model.*;


public interface ChatMessageService {
	public int addMessage(ChatMessageDTO dto) throws Exception;
	public List<MessageListDTO> getMessageList(int room_id)throws Exception;
	public List<ChatRoomListDTO> getChatRoomList(int member_id)throws Exception;
	public int createChatRoom(ChatRoomDTO dto)throws Exception;
	public int addChatMember(ChatRoom_MemberDTO dto)throws Exception;
}
