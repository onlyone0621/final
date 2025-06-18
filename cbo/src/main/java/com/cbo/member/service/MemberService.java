package com.cbo.member.service;

import org.springframework.stereotype.Service;
import com.cbo.member.model.MemberDTO;

public interface MemberService {
	public String getMemberId(String user_id) throws Exception;
	public int memberJoin(MemberDTO dto) throws Exception;
}
