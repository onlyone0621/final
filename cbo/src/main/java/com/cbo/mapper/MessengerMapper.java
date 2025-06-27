package com.cbo.mapper;

import java.util.List;
import java.util.Map;

import com.cbo.messenger.model.*;


public interface MessengerMapper {
	public int insertMessage(ChatMessageDTO dto)throws Exception;
	public List<MessageListDTO> selectMessage(int room_id) throws Exception;
}
