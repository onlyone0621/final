package com.cbo.approval.service;

import java.util.List;
import java.util.Map;

import com.cbo.approval.model.DocViewDTO;
import com.cbo.approval.model.FormatDTO;

public interface ApprovalService {
	List<FormatDTO> getFormats() throws Exception;
	List<Map<String, Object>> selectMembers() throws Exception;
	List<DocViewDTO> getApprovalDocs(int id) throws Exception;
	List<DocViewDTO> getReferenceDocs(int id) throws Exception;
	List<DocViewDTO> getDraftDocs(int id) throws Exception;
	List<DocViewDTO> getPendingApprovalDocs(int id) throws Exception;
	List<DocViewDTO> getPendingReferenceDocs(int id) throws Exception;
	List<DocViewDTO> getScheduledApprovalDocs(int id) throws Exception;
}
