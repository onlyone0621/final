package com.cbo.approval.service;

import java.util.List;
import java.util.Map;

import com.cbo.approval.model.ApprovalLineDTO;
import com.cbo.approval.model.DocDTO;
import com.cbo.approval.model.DocViewDTO;
import com.cbo.approval.model.FormatDTO;
import com.cbo.member.model.OrganDTO;

public interface ApprovalService {
	List<Map<String, Object>> getFormatNames() throws Exception;
	
	// Doc list
	List<DocViewDTO> getApprovalDocs(int id) throws Exception;
	List<DocViewDTO> getReferenceDocs(int id) throws Exception;
	List<DocViewDTO> getDraftDocs(int id) throws Exception;
	
	// Todo doc list
	List<DocViewDTO> getPendingApprovalDocs(int id) throws Exception;
	List<DocViewDTO> getPendingReferenceDocs(int id) throws Exception;
	List<DocViewDTO> getScheduledApprovalDocs(int id) throws Exception;
	
	// Get doc's content and approval lines
	Map<String, Object> getDocContent(int id) throws Exception;
	List<ApprovalLineDTO> getApprovers(int id) throws Exception;
	List<ApprovalLineDTO> getReviewers(int id) throws Exception;
	
	// Submit draft
	Map<String, Object> getFormat(int id) throws Exception;
	Map<String, List<OrganDTO>> getMembers() throws Exception;
	boolean submitDraft(DocDTO dto, List<Integer> approversId, List<Integer> reviewersId) throws Exception;
	
	// Approve or Reject
	int approve(int docId, int memberId, String status) throws Exception;
	int reject(int docId, int memberId, String status) throws Exception;
}
