package com.cbo.admin.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cbo.dept.model.DeptDTO;
import com.cbo.grade.model.GradeDTO;
import com.cbo.member.model.MemberDTO;

public interface AdminService {
    

	// 상세 목록
	public  List<MemberDTO> selectMemberDetailList(int cp, int listSize) throws Exception;  // cp, listSize 로 정의
	   
    // 회원 수
    public  int memberTotalCount() throws Exception;                       
    // 삭제용 간단 목록
    public List<MemberDTO> basicMemberList();                   
    // 삭제
    public int DeleteMembers(List<Integer> mid);               

    // 사용자 정보 조회
    public MemberDTO getMember(String user_id) throws Exception;  
    // 부서 목록
    public List<DeptDTO> getDept() throws Exception;       
 // 직급 목록
    public List<GradeDTO> getGrade() throws Exception;      
    // 회원 정보 수정
    public int setMemberInfo(MemberDTO dto) throws Exception;   


}
