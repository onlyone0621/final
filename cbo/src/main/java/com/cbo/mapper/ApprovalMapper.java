package com.cbo.mapper;

import java.util.List;
import java.util.Map;

import com.cbo.approval.model.ApprovalLineDTO;
import com.cbo.approval.model.DocDTO;
import com.cbo.approval.model.DocViewDTO;
import com.cbo.approval.model.FormatDTO;
import com.cbo.member.model.OrganDTO;

public interface ApprovalMapper {
	List<Map<String, Object>> selectFormatNames() throws Exception;
	
	// Doc list
	List<DocViewDTO> selectApprovalDocs(int id) throws Exception;
	List<DocViewDTO> selectReferenceDocs(int id) throws Exception;
	List<DocViewDTO> selectDraftDocs(int id) throws Exception;
	
	// Todo doc list
	List<DocViewDTO> selectPendingApprovalDocs(int id) throws Exception;
	List<DocViewDTO> selectPendingReferenceDocs(int id) throws Exception;
	List<DocViewDTO> selectScheduledApprovalDocs(int id) throws Exception;
	
	// Get doc content and its approval lines
	Map<String, Object> selectDoc(int id) throws Exception;
	List<ApprovalLineDTO> selectApprovers(int id) throws Exception;
	List<ApprovalLineDTO> selectReviewers(int id) throws Exception;
	
	// Submit draft
	Map<String, Object> selectFormat(int id) throws Exception;
	List<OrganDTO> selectMembers() throws Exception;
	int selectDocId() throws Exception;
	int insertDoc(DocDTO dto) throws Exception;
	int insertDrafterOrReviewers(Map<String, Object> map) throws Exception;
	int insertApprovers(Map<String, Object> map) throws Exception;
	
	int updateStatusToApproved(Map<String, Object> map) throws Exception;
	int updateStatusToRejected(Map<String, Object> map) throws Exception;
}
