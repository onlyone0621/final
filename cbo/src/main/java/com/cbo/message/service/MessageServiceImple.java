package com.cbo.message.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cbo.member.model.OrganDTO;
import com.cbo.message.model.MessageDTO;

@Service
public class MessageServiceImple implements MessageService {

	@Override
	public List<MessageDTO> getUnreadMessages(int memberId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MessageDTO> getReceivedMessages(int memberId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MessageDTO> getSentMessages(int memberId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrganDTO> getMembers() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int sendMessage(MessageDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MessageDTO getMessageContent(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
