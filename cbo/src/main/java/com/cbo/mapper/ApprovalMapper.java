package com.cbo.mapper;

import java.util.List;

import com.cbo.approval.model.DocViewDTO;
import com.cbo.approval.model.FormatDTO;
import com.cbo.member.model.OrganDTO;

public interface ApprovalMapper {
	List<FormatDTO> selectFormats() throws Exception;
	List<OrganDTO> selectMembers() throws Exception;
	List<DocViewDTO> selectApprovalDocs(int id) throws Exception;
	List<DocViewDTO> selectReferenceDocs(int id) throws Exception;
	List<DocViewDTO> selectDraftDocs(int id) throws Exception;
	List<DocViewDTO> selectPendingApprovalDocs(int id) throws Exception;
	List<DocViewDTO> selectPendingReferenceDocs(int id) throws Exception;
	List<DocViewDTO> selectScheduledApprovalDocs(int id) throws Exception;
}
