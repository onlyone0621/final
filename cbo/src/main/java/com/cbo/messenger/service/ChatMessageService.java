package com.cbo.messenger.service;

import java.util.List;
import java.util.Map;

import com.cbo.member.model.*;
import com.cbo.messenger.model.*;


public interface ChatMessageService {
	public int addMessage(ChatMessageDTO dto) throws Exception;
	public List<MessageListDTO> getMessageList(Map map)throws Exception;
	public List<ChatRoomListDTO> getChatRoomList(int member_id)throws Exception;
	public int createChatRoom(ChatRoomDTO dto)throws Exception;
	public int addChatMember(ChatRoom_MemberDTO dto)throws Exception;
	public List<InviteeDTO> getInvitee(int room_id)throws Exception;
	public List<ChatMemberDTO> getChatMember(int room_id)throws Exception;
	public int delChatMember(ChatRoom_MemberDTO dto)throws Exception;
	public List<OrganDTO> getMembers(int id)throws Exception;
	public Integer findPrivateRoom(int member1_id, int member2_id)throws Exception;
}
