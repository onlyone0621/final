package com.cbo.message.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cbo.mapper.MessageMapper;
import com.cbo.member.model.OrganDTO;
import com.cbo.message.model.MessageDTO;

@Service
public class MessageServiceImple implements MessageService {
	private final MessageMapper mapper;
	
	public MessageServiceImple(MessageMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<MessageDTO> getUnreadMessages(int memberId) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectUnreadMessages(memberId);
	}

	@Override
	public List<MessageDTO> getReceivedMessages(int memberId) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectReceivedMessages(memberId);
	}

	@Override
	public List<MessageDTO> getSentMessages(int memberId) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectSentMessages(memberId);
	}

	@Override
	public List<OrganDTO> getMembers() throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectMembers();
	}

	@Override
	public boolean sendMessages(MessageDTO dto, List<Integer> receiverIds) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MessageDTO getMessageContent(int id) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectMessage(id);
	}

	@Override
	public int markAsRead(List<Integer> selectedIds) throws Exception {
		// TODO Auto-generated method stub
		return mapper.updateReadStatus(selectedIds);
	}

	@Override
	public int deleteMessages(List<Integer> selectedIds) throws Exception {
		// TODO Auto-generated method stub
		return mapper.deleteMessages(selectedIds);
	}
	
	

}
