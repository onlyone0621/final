package com.cbo.mapper;

import java.util.List;
import java.util.Map;

import com.cbo.messenger.model.*;


public interface MessengerMapper {
	public int insertMessage(ChatMessageDTO dto)throws Exception;
	public List<MessageListDTO> selectMessage(int room_id) throws Exception;
	public List<ChatRoomListDTO> selectChatRoomList(int member_id) throws Exception;
	public int insertChatRoom(ChatRoomDTO dto) throws Exception;
	public int insertChatMember(ChatRoom_MemberDTO dto)throws Exception;
}
