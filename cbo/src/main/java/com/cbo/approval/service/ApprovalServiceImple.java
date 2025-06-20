package com.cbo.approval.service;

import java.util.List;

import com.cbo.approval.model.FormatDTO;
import com.cbo.mapper.ApprovalMapper;
import com.cbo.member.model.OrganDTO;

public class ApprovalServiceImple implements ApprovalService {
	
	private final ApprovalMapper mapper;
	
	public ApprovalServiceImple(ApprovalMapper mapper) {
		this.mapper = mapper;
	}

	
	@Override
	public List<FormatDTO> getFormats() throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectFormats();
	}

	@Override
	public List<OrganDTO> selectMembers() throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectMembers();
	}

}
