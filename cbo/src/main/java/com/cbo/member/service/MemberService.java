package com.cbo.member.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cbo.dept.model.DeptDTO;
import com.cbo.grade.model.GradeDTO;
import com.cbo.member.model.MemberDTO;

public interface MemberService {
	public MemberDTO getMember(String user_id) throws Exception;
	public String getMemberId(String user_id) throws Exception;
	public String getMemberPwd(String user_id) throws Exception;	
	public int memberJoin(MemberDTO dto) throws Exception;
	public List<DeptDTO> getDept() throws Exception;
	public List<GradeDTO> getGrade() throws Exception;
	public String getMemberId2(String email, String name) throws Exception;
	public String getMemberPwd2(String email, String user_id) throws Exception;
	public int setNewPwd(String user_id, String pwd) throws Exception;
	public int setMemberInfo(MemberDTO dto) throws Exception;
}
