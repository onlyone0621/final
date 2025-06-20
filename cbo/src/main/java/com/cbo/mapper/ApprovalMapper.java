package com.cbo.mapper;

import java.util.List;

import com.cbo.approval.model.FormatDTO;
import com.cbo.member.model.OrganDTO;

public interface ApprovalMapper {
	List<FormatDTO> selectFormats() throws Exception;
	List<OrganDTO> selectMembers() throws Exception;
}
