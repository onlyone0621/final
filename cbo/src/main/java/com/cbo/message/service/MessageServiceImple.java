package com.cbo.message.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.cbo.messenger.service.ChatMessageServiceImple;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbo.constant.MessageConst;
import com.cbo.mapper.MessageMapper;
import com.cbo.member.model.OrganDTO;
import com.cbo.message.model.MessageDTO;

@Service
public class MessageServiceImple implements MessageService {

    private final ChatMessageServiceImple chatMessageServiceImple;
	private final MessageMapper mapper;
	
	public MessageServiceImple(MessageMapper mapper, ChatMessageServiceImple chatMessageServiceImple) {
		this.mapper = mapper;
		this.chatMessageServiceImple = chatMessageServiceImple;
	}

	@Override
	public List<MessageDTO> getUnreadMessages(int memberId, int curPage) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("memberId", memberId);
		
		int start = (curPage - 1) * MessageConst.ROWS + 1;
		int end = curPage * MessageConst.ROWS;
		map.put("start", start);
		map.put("end", end);
		
		return mapper.selectUnreadMessages(map);
	}

	@Override
	public List<MessageDTO> getReceivedMessages(int memberId, int curPage) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("memberId", memberId);
		
		int start = (curPage - 1) * MessageConst.ROWS + 1;
		int end = curPage * MessageConst.ROWS;
		map.put("start", start);
		map.put("end", end);

		return mapper.selectReceivedMessages(map);
	}

	@Override
	public List<MessageDTO> getSentMessages(int memberId, int curPage) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("memberId", memberId);
		
		int start = (curPage - 1) * MessageConst.ROWS + 1;
		int end = curPage * MessageConst.ROWS;
		map.put("start", start);
		map.put("end", end);
		
		return mapper.selectSentMessages(map);
	}

	@Override
	public Map<String, List<OrganDTO>> getMembers() throws Exception {
		// TODO Auto-generated method stub
		List<OrganDTO> memberList = mapper.selectMembers();
		Map<String, List<OrganDTO>> membersByDept = memberList.stream()
				.collect(Collectors.groupingBy(OrganDTO :: getDept_name,
						LinkedHashMap :: new,
						Collectors.toList()));
		
		return membersByDept;
	}

	@Override
	@Transactional
	public boolean sendMessages(MessageDTO dto, List<Integer> receiverIds) throws Exception {
		class NotInsertedException extends RuntimeException {
			NotInsertedException(String message) {
				super(message);
			}
		}
		
		if (dto == null || receiverIds == null || receiverIds.size() == 0) return false;
		
		for (int id : receiverIds) {
			dto.setReceiver_id(id);
			if (mapper.insertMessages(dto) != 1) 
				throw new NotInsertedException("Message not inserted");
		}
		return true;
	}

	@Override
	public MessageDTO getMessageContent(int id) throws Exception {
		// Fetch content of message
		MessageDTO res = mapper.selectMessage(id);
		
		// Update its read status to 'read'
		if (res != null && res.getId() == id) {
			String status = MessageConst.READ;
			List<Integer> selectedIds = List.of(id);
			
			Map<String, Object> map = new HashMap<>();
			map.put("status", status);
			map.put("selectedIds", selectedIds);
			
			if (mapper.updateReadStatus(map) != 1) {
				System.out.println("read status update failed");
				res = null;
			}
		}
		
		return res;
	}

	@Override
	public int markAsRead(List<Integer> selectedIds) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("status", MessageConst.READ);
		map.put("selectedIds", selectedIds);
		return mapper.updateReadStatus(map);
	}

	@Override
	public int deleteMessages(List<Integer> selectedIds) throws Exception {
		// TODO Auto-generated method stub
		return mapper.deleteMessages(selectedIds);
	}
	
	

}
