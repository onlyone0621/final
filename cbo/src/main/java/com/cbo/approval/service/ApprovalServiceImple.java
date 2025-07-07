package com.cbo.approval.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbo.approval.model.ApprovalLineDTO;
import com.cbo.approval.model.DocDTO;
import com.cbo.approval.model.DocViewDTO;
import com.cbo.approval.model.FormatDTO;
import com.cbo.constant.ApprovalConst;
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
	public Map<String, List<OrganDTO>> getMembers() throws Exception {
		// TODO Auto-generated method stub
		List<OrganDTO> members = mapper.selectMembers();
		
		// Process members list grouping by dept 
		return members.stream()
				.collect(Collectors.groupingBy(OrganDTO :: getDept_name,
						LinkedHashMap :: new,
						Collectors.toList()));
	}

	@Override
	@Transactional
	public boolean submitDraft(DocDTO dto, List<Integer> approversId, List<Integer> reviewersId) throws Exception {
		class NotInsertedException extends RuntimeException {
			NotInsertedException(String message) {
				super(message);
			}
		}

		// Get id to be inserted in doc table
		int docId = mapper.selectDocId();
		dto.setId(docId);
		
		// Insert doc
		if (mapper.insertDoc(dto) != 1) {
			throw new NotInsertedException("Doc was not inserted");
		}
		
		// Create list of approvers adding drafter, approvers, and reviewers
		List<ApprovalLineDTO> entries = new ArrayList<>();
		
		entries.add(new ApprovalLineDTO(docId, dto.getMember_id(), null, null, null, null, ApprovalConst.DRAFT, null));
		if (approversId != null && approversId.size() > 0) {
			approversId.forEach(id -> entries.add(new ApprovalLineDTO(docId, id, null, null, null, null, ApprovalConst.PENDING, null)));
		}
			
		if (reviewersId != null && reviewersId.size() > 0) {
			reviewersId.forEach(id -> entries.add(new ApprovalLineDTO(docId, id, null, null, null, null, ApprovalConst.REFERENCE, null)));
		}
			
		
		// Insert approval lines
		for (ApprovalLineDTO entry : entries) {
			if (mapper.insertApprovalLines(entry) != 1) {
				throw new NotInsertedException("Approver was not inserted for member ID: " + entry.getMember_id());
			}
		}
		
		return true;
	}


	@Override
	public int approve(int docId, int memberId) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("doc_id", docId);
		map.put("member_id", memberId);
		map.put("status", ApprovalConst.APPROVED);
		return mapper.updateStatus(map);
	}


	@Override
	public int reject(int docId, int memberId) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("doc_id", docId);
		map.put("member_id", memberId);
		map.put("status", ApprovalConst.REJECTED);
		return mapper.updateStatus(map);
	}

}
