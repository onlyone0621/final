package com.cbo.mapper;

import java.util.List;
import java.util.Map;

import com.cbo.member.model.OrganDTO;
import com.cbo.message.model.MessageDTO;

public interface MessageMapper {
	List<MessageDTO> selectUnreadMessages(Map<String, Object> map) throws Exception;
	List<MessageDTO> selectReceivedMessages(Map<String, Object> map) throws Exception;
	List<MessageDTO> selectSentMessages(Map<String, Object> map) throws Exception;
	
	List<OrganDTO> selectMembers() throws Exception;
	int insertMessages(MessageDTO dto) throws Exception;
	
	MessageDTO selectMessage(int id) throws Exception;
	
	int updateReadStatus(List<Integer> selectedIds) throws Exception;
	int deleteMessages(List<Integer> selectedIds) throws Exception;
	
}
