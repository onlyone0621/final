package com.cbo.message.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cbo.constant.MessageConst;
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
		return mapper.selectUnreadMessages(map);
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
		return mapper.selectUnreadMessages(map);
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
