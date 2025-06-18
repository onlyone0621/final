package com.cbo.mapper;

import com.cbo.member.model.MemberDTO;

public interface MemberMapper {
	public String selectMemberId(String user_id) throws Exception;
	public int insertMember(MemberDTO dto) throws Exception;
}
