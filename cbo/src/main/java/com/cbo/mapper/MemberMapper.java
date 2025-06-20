package com.cbo.mapper;

import java.util.List;

import com.cbo.dept.model.DeptDTO;
import com.cbo.grade.model.GradeDTO;
import com.cbo.member.model.MemberDTO;

public interface MemberMapper {
	public String selectMemberId(String user_id) throws Exception;
	public int insertMember(MemberDTO dto) throws Exception;
	public List<DeptDTO> selectDept() throws Exception;
	public List<GradeDTO> selectGrade() throws Exception;
	public String selectMemberId2(String email) throws Exception;
	public int updateMemberPwd(String user_id) throws Exception;
}
