package com.cbo.mapper;

import java.util.List;
import java.util.Map;

import com.cbo.member.model.*;
import com.cbo.messenger.model.*;


public interface MessengerMapper {
	public int insertMessage(ChatMessageDTO dto)throws Exception;
	public List<MessageListDTO> selectMessage(Map map) throws Exception;
	public List<ChatRoomListDTO> selectChatRoomList(int member_id) throws Exception;
	public int insertChatRoom(ChatRoomDTO dto) throws Exception;
	public int insertChatMember(ChatRoom_MemberDTO dto)throws Exception;
	public List<InviteeDTO> selectInvitee(int room_id) throws Exception;
	public List<ChatMemberDTO> selectChatMember(int room_id) throws Exception;
	public int deleteChatMember(ChatRoom_MemberDTO dto)throws Exception;
	public List<OrganDTO> selectMembers(int id)throws Exception;
	public Integer selectPrivateRoom(int member1_id, int member2_id)throws Exception;
}
