package com.cbo.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbo.dept.model.DeptDTO;
import com.cbo.grade.model.GradeDTO;
import com.cbo.mapper.AdminMapper;
import com.cbo.member.model.MemberDTO;

@Service
public class AdminServiceImple implements AdminService {

	@Autowired
	private AdminMapper mapper;

	// 상세 목록
	@Override
	public List<MemberDTO> selectMemberDetailList(int cp, int listSize) throws Exception {
	    int start = (cp - 1) * listSize + 1;
	    int end = cp * listSize;
	    Map<String, Integer> map = new HashMap<>();
	    map.put("start", start);
	    map.put("end", end);
	    return mapper.selectMemberDetailList(map);
	}

	// 회원 수(페이징)
    @Override
    public int memberTotalCount() throws Exception {
        return mapper.memberTotalCount();
    }

	// 삭제용 간단 목록
	@Override
	public List<MemberDTO> basicMemberList() {
		return mapper.basicMemberList();
	}

//회원 삭제
	@Override
	public int DeleteMembers(List<Integer> mid) {
		return mapper.DeleteMembers(mid);
	}

	// 회원 정보 조회(특정)
	@Override
	public MemberDTO getMember(String user_id) throws Exception {
		return mapper.selectMember(user_id);
	}

	// 부서 정보 조회
	@Override
	public List<DeptDTO> getDept() throws Exception {
		return mapper.selectDept();
	}

	// 집급 정보 조회
	@Override
	public List<GradeDTO> getGrade() throws Exception {
		return mapper.selectGrade();
	}

	// 회원 정보 수정
	@Override
	public int setMemberInfo(MemberDTO dto) throws Exception {
		return mapper.updateMemberInfo(dto);
	}

}
