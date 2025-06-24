package com.cbo.mapper;

import java.util.List;

import com.cbo.dept.model.DeptDTO;
import com.cbo.grade.model.GradeDTO;
import com.cbo.member.model.MemberDTO;

public interface MemberMapper {
	public MemberDTO selectMember(String user_id) throws Exception;
	public String selectMemberId(String user_id) throws Exception;
	public String selectMemberPwd(String user_id) throws Exception;
	public int insertMember(MemberDTO dto) throws Exception;
	public List<DeptDTO> selectDept() throws Exception;
	public List<GradeDTO> selectGrade() throws Exception;
	public String selectMemberId2(String email, String name) throws Exception;
	public String selectMemberPwd2(String email, String user_id) throws Exception;
	public int updateMemberPwd(String user_id, String pwd) throws Exception;
	public int updateMemberInfo(MemberDTO dto)throws Exception;
}
