package com.cbo.approval.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cbo.approval.model.ApprovalLineDTO;
import com.cbo.approval.model.DocDTO;
import com.cbo.approval.model.DocViewDTO;
import com.cbo.approval.model.FormatDTO;
import com.cbo.mapper.ApprovalMapper;
import com.cbo.member.model.OrganDTO;

@Service
public class ApprovalServiceImple implements ApprovalService {
	
	private final ApprovalMapper mapper;
	
	public ApprovalServiceImple(ApprovalMapper mapper) {
		this.mapper = mapper;
	}

	
	@Override
	public List<Map<String, Object>> getFormatNames() throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectFormatNames();
	}

	@Override
	public List<DocViewDTO> getApprovalDocs(int id) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectApprovalDocs(id);
	}


	@Override
	public List<DocViewDTO> getReferenceDocs(int id) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectReferenceDocs(id);
	}


	@Override
	public List<DocViewDTO> getDraftDocs(int id) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectDraftDocs(id);
	}


	@Override
	public List<DocViewDTO> getPendingApprovalDocs(int id) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectPendingApprovalDocs(id);
	}


	@Override
	public List<DocViewDTO> getPendingReferenceDocs(int id) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectPendingReferenceDocs(id);
	}


	@Override
	public List<DocViewDTO> getScheduledApprovalDocs(int id) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectScheduledApprovalDocs(id);
	}


	@Override
	public Map<String, Object> getDocContent(int id) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectDoc(id);
	}


	@Override
	public List<ApprovalLineDTO> getApprovers(int id) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectApprovers(id);
	}


	@Override
	public List<ApprovalLineDTO> getReviewers(int id) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectReviewers(id);
	}
	
	
	@Override
	public Map<String, Object> getFormat(int id) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectFormat(id);
	}


	@Override
	public List<OrganDTO> getMembers() throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectMembers();
	}


	@Override
	public int approve(int docId, int memberId, String status) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("doc_id", docId);
		map.put("member_id", memberId);
		map.put("status", status);
		return mapper.updateStatusToApproved(map);
	}


	@Override
	public int reject(int docId, int memberId, String status) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("doc_id", docId);
		map.put("member_id", memberId);
		map.put("status", status);
		return mapper.updateStatusToRejected(map);
	}

}
