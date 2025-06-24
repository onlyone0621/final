package com.cbo.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbo.dept.model.DeptDTO;
import com.cbo.grade.model.GradeDTO;
import com.cbo.mapper.MemberMapper;
import com.cbo.member.model.MemberDTO;

@Service
public class MemberServiceImple implements MemberService {
	@Autowired
	private MemberMapper mapper;
	
	@Override
	public MemberDTO getMember(String user_id) throws Exception {
		MemberDTO dto = mapper.selectMember(user_id);
		return dto;
	}
	
	@Override
	public String getMemberId(String user_id) throws Exception {
		String userId = mapper.selectMemberId(user_id);
		return userId;
	}
	
	@Override
	public String getMemberPwd(String user_id) throws Exception {
		String userPwd = mapper.selectMemberPwd(user_id);
		return userPwd;
	}
	
	@Override
	public int memberJoin(MemberDTO dto) throws Exception {
		int result = mapper.insertMember(dto);
		return result;
	}
	
	@Override
	public List<DeptDTO> getDept() throws Exception {
		List<DeptDTO> lists = mapper.selectDept();
		return lists;
	}
	
	@Override
	public List<GradeDTO> getGrade() throws Exception {
		List<GradeDTO> lists = mapper.selectGrade();
		return lists;
	}
	
	@Override
	public String getMemberId2(String email, String name) throws Exception {
		String user_id = mapper.selectMemberId2(email, name);
		return user_id;
	}
	
	@Override
	public int setNewPwd(String user_id, String pwd) throws Exception {
		int result = mapper.updateMemberPwd(user_id, pwd);
		return result;
	}
	
	@Override
	public String getMemberPwd2(String email, String user_id) throws Exception {
		String pwd = mapper.selectMemberPwd2(email, user_id);
		return pwd;
	}
	
	@Override
	public int setMemberInfo(MemberDTO dto) throws Exception {
		int result = mapper.updateMemberInfo(dto);
		return result;
	}
	
}
