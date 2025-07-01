package com.cbo.message.service;

import java.util.List;

import com.cbo.member.model.OrganDTO;
import com.cbo.message.model.MessageDTO;

public interface MessageService {
	List<MessageDTO> getUnreadMessages(int memberId, int curPage) throws Exception;
	List<MessageDTO> getReceivedMessages(int memberId, int curPage) throws Exception;
	List<MessageDTO> getSentMessages(int memberId, int curPage) throws Exception;
	
	List<OrganDTO> getMembers() throws Exception;
	boolean sendMessages(MessageDTO dto, List<Integer> selectedIds) throws Exception;
	
	MessageDTO getMessageContent(int id) throws Exception;
	
	int markAsRead(List<Integer> selectedIds) throws Exception;
	int deleteMessages(List<Integer> selectedIds) throws Exception;
}
