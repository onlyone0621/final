package com.cbo.messenger.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbo.mapper.MessengerMapper;
import com.cbo.messenger.model.ChatMessageDTO;
import com.cbo.messenger.model.MessageListDTO;
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

}
