package com.cbo.approval.service;

import java.util.List;

import com.cbo.approval.model.FormatDTO;
import com.cbo.member.model.OrganDTO;

public interface ApprovalService {
	List<FormatDTO> getFormats() throws Exception;
	List<OrganDTO> selectMembers() throws Exception;
}
