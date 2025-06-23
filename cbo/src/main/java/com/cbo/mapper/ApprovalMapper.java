package com.cbo.mapper;

import java.util.List;
import java.util.Map;

import com.cbo.approval.model.ApprovalLineDTO;
import com.cbo.approval.model.DocDTO;
import com.cbo.approval.model.DocViewDTO;
import com.cbo.approval.model.FormatDTO;

public interface ApprovalMapper {
	List<FormatDTO> selectFormats() throws Exception;
	List<Map<String, Object>> selectMembers() throws Exception;
	List<DocViewDTO> selectApprovalDocs(int id) throws Exception;
	List<DocViewDTO> selectReferenceDocs(int id) throws Exception;
	List<DocViewDTO> selectDraftDocs(int id) throws Exception;
	List<DocViewDTO> selectPendingApprovalDocs(int id) throws Exception;
	List<DocViewDTO> selectPendingReferenceDocs(int id) throws Exception;
	List<DocViewDTO> selectScheduledApprovalDocs(int id) throws Exception;
	DocDTO selectDoc(int id) throws Exception;
	List<ApprovalLineDTO> selectApprovers(int id) throws Exception;
	List<ApprovalLineDTO> selectReviewers(int id) throws Exception;
}
