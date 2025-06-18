package com.cbo.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbo.mapper.MemberMapper;
import com.cbo.member.model.MemberDTO;

@Service
public class MemberServiceImple implements MemberService {
	@Autowired
	private MemberMapper mapper;
	
	@Override
	public String getMemberId(String user_id) throws Exception {
		String userId = mapper.selectMemberId(user_id);
		return userId;
	}
	
	@Override
	public int memberJoin(MemberDTO dto) throws Exception {
		int result = mapper.insertMember(dto);
		return result;
	}
}
