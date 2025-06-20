package com.cbo.approval.service;

import java.util.List;

import org.springframework.stereotype.Service;

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
	public List<FormatDTO> getFormats() throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectFormats();
	}

	@Override
	public List<OrganDTO> selectMembers() throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectMembers();
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

}
