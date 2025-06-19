package com.cbo.approval.service;

import java.util.List;

import com.cbo.member.model.OrganDTO;

public interface ApprovalService {
	List<OrganDTO> selectMembers() throws Exception;
}
