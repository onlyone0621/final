package com.cbo.mapper;

import java.util.List;

import com.cbo.member.model.OrganDTO;

public interface ApprovalMapper {
	List<OrganDTO> selectMembers() throws Exception;
}
