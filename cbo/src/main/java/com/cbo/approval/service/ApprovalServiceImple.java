package com.cbo.approval.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.cbo.messenger.service.ChatMessageServiceImple;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
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

    private final ChatMessageServiceImple chatMessageServiceImple;
	private final CacheManager cacheManager;
	private final ApprovalMapper mapper;
	
	public ApprovalServiceImple(ApprovalMapper mapper,
			ChatMessageServiceImple chatMessageServiceImple,
			CacheManager cacheManager) {
		this.mapper = mapper;
		this.chatMessageServiceImple = chatMessageServiceImple;
		this.cacheManager = cacheManager;
	}

	@Override
	@Cacheable("formats")
	public List<Map<String, Object>> getFormatNames() throws Exception {
		List<Map<String, Object>> formats = mapper.selectFormatNames();
		
		Cache cache = cacheManager.getCache("formatById");
		
		if (cache != null) {
			for (Map<String, Object> row : formats) {
				BigDecimal idDecimal = (BigDecimal)row.get("id");
				Integer id = idDecimal.intValueExact();
				if (id != null) {
					cache.put(id, row);
				}
			}
		}
		
		return formats;
	}

	@Override
	public List<DocViewDTO> getApprovalDocs(int id, int curPage) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		
		int start = (curPage - 1) * ApprovalConst.ROWS + 1;
		int end = curPage * ApprovalConst.ROWS;
		map.put("start", start);
		map.put("end", end);
		
		return mapper.selectApprovalDocs(map);
	}


	@Override
	public List<DocViewDTO> getReferenceDocs(int id, int curPage) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		
		int start = (curPage - 1) * ApprovalConst.ROWS + 1;
		int end = curPage * ApprovalConst.ROWS;
		map.put("start", start);
		map.put("end", end);
		return mapper.selectReferenceDocs(map);
	}


	@Override
	public List<DocViewDTO> getDraftDocs(int id, int curPage) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		
		int start = (curPage - 1) * ApprovalConst.ROWS + 1;
		int end = curPage * ApprovalConst.ROWS;
		map.put("start", start);
		map.put("end", end);
		return mapper.selectDraftDocs(map);
	}


	@Override
	public List<DocViewDTO> getPendingApprovalDocs(int id, int curPage) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		
		int start = (curPage - 1) * ApprovalConst.ROWS + 1;
		int end = curPage * ApprovalConst.ROWS;
		map.put("start", start);
		map.put("end", end);
		return mapper.selectPendingApprovalDocs(map);
	}


	@Override
	public List<DocViewDTO> getPendingReferenceDocs(int id, int curPage) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		
		int start = (curPage - 1) * ApprovalConst.ROWS + 1;
		int end = curPage * ApprovalConst.ROWS;
		map.put("start", start);
		map.put("end", end);
		return mapper.selectPendingReferenceDocs(map);
	}


	@Override
	public List<DocViewDTO> getScheduledApprovalDocs(int id, int curPage) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		
		int start = (curPage - 1) * ApprovalConst.ROWS + 1;
		int end = curPage * ApprovalConst.ROWS;
		map.put("start", start);
		map.put("end", end);
		return mapper.selectScheduledApprovalDocs(map);
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
	@Cacheable(value = "formatById", key = "#id")
	public Map<String, Object> getFormat(int id) throws Exception {
		System.out.println("Fetched from db");
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
