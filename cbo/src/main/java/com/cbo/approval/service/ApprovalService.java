package com.cbo.approval.service;

import java.util.List;

import com.cbo.approval.model.DocViewDTO;
import com.cbo.approval.model.FormatDTO;
import com.cbo.member.model.OrganDTO;

public interface ApprovalService {
	List<FormatDTO> getFormats() throws Exception;
	List<OrganDTO> selectMembers() throws Exception;
	List<DocViewDTO> getApprovalDocs(int id) throws Exception;
	List<DocViewDTO> getReferenceDocs(int id) throws Exception;
	List<DocViewDTO> getDraftDocs(int id) throws Exception;
}
