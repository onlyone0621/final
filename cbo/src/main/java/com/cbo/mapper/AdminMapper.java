package com.cbo.mapper;

import java.util.List;
import java.util.Map;

import com.cbo.dept.model.DeptDTO;
import com.cbo.grade.model.GradeDTO;
import com.cbo.member.model.MemberDTO;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {

    // 상세 목록 (10개씩 페이징)
	  public List<MemberDTO> selectMemberDetailList(Map<String, Integer> map) throws Exception;
	    

    // 전체 회원 수 조회 (페이징 계산용)
	  public int memberTotalCount() throws Exception;

    // 삭제용 간단 목록
    public List<MemberDTO> basicMemberList();

    // 회원 삭제
    public int DeleteMembers(@Param("mid") List<Integer> mid);
    
    //회원 정보 조회(수정용)
    public MemberDTO selectMember(String user_id) throws Exception;
    //회원 부서
    public List<DeptDTO> selectDept() throws Exception;
    //회원 직급
    public List<GradeDTO> selectGrade() throws Exception;
    //회원 수정
    public int updateMemberInfo(MemberDTO dto) throws Exception;

   

}
