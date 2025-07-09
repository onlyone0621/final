package com.cbo.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cbo.approval.model.ApprovalLineDTO;
import com.cbo.approval.model.DocDTO;
import com.cbo.approval.model.DocViewDTO;
import com.cbo.approval.service.ApprovalService;
import com.cbo.constant.ApprovalConst;
import com.cbo.constant.MemberConst;
import com.cbo.member.model.MemberDTO;
import com.cbo.member.model.OrganDTO;
import com.cbo.pagination.Pagination;

@Controller
public class ApprovalController {

	private final ApprovalService approvalService;
	
	public ApprovalController(ApprovalService approvalService) {
		this.approvalService = approvalService;
	}
	
	@ModelAttribute("formats")
	List<Map<String, Object>> formatNames(){
		try {
			return approvalService.getFormatNames();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/approvalMain")
	public String approvalMain() {
		return "approval/approvalMain";
	}
	
	@GetMapping("/pendingApprovalDocs")
	public ModelAndView pendingApprovalDocs(@RequestParam(defaultValue = "1") int curPage,
			@SessionAttribute(MemberConst.USER_KEY) MemberDTO userInfo) {
		List<DocViewDTO> res = null;
		try {
			res = approvalService.getPendingApprovalDocs(userInfo.getId(), curPage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int maxRows = res != null && res.size() > 0 ? res.get(0).getMax_rows() : 1;
		String pageBar = Pagination.makePaging("pendingApprovalDocs", maxRows, ApprovalConst.ROWS, ApprovalConst.PAGES, curPage);
		
		ModelAndView mav = new ModelAndView("approval/pendingApprovalDocs");
		mav.addObject("pendingApprovalDocs", res);
		mav.addObject("pageBar", pageBar);
		return mav;
	}
	
	@GetMapping("/pendingReferenceDocs")
	public ModelAndView pendingReferenceDocs(@RequestParam(defaultValue = "1") int curPage,
			@SessionAttribute(MemberConst.USER_KEY) MemberDTO userInfo) {
		List<DocViewDTO> res = null;
		try {
			res = approvalService.getPendingReferenceDocs(userInfo.getId(), curPage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int maxRows = res != null && res.size() > 0 ? res.get(0).getMax_rows() : 1;
		String pageBar = Pagination.makePaging("pendingReferenceDocs", maxRows, ApprovalConst.ROWS, ApprovalConst.PAGES, curPage);
		
		ModelAndView mav = new ModelAndView("approval/pendingReferenceDocs");
		mav.addObject("pendingReferenceDocs", res);
		mav.addObject("pageBar", pageBar);
		return mav;
	}
	
	@GetMapping("/scheduledApprovalDocs")
	public ModelAndView scheduledApprovalDocs(@RequestParam(defaultValue = "1") int curPage,
			@SessionAttribute(MemberConst.USER_KEY) MemberDTO userInfo) {
		List<DocViewDTO> res = null;
		try {
			res = approvalService.getScheduledApprovalDocs(userInfo.getId(), curPage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int maxRows = res != null && res.size() > 0 ? res.get(0).getMax_rows() : 1;
		String pageBar = Pagination.makePaging("scheduledApprovalDocs", maxRows, ApprovalConst.ROWS, ApprovalConst.PAGES, curPage);
		
		ModelAndView mav = new ModelAndView("approval/scheduledApprovalDocs");
		mav.addObject("scheduledApprovalDocs", res);
		mav.addObject("pageBar", pageBar);
		return mav;
	}
	
	@GetMapping("/approvalDocs")
	public ModelAndView approvalDocs(@RequestParam(defaultValue = "1") int curPage,
			@SessionAttribute(MemberConst.USER_KEY) MemberDTO userInfo) {
		List<DocViewDTO> res = null;
		try {
			res = approvalService.getApprovalDocs(userInfo.getId(), curPage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int maxRows = res != null && res.size() > 0 ? res.get(0).getMax_rows() : 1;
		String pageBar = Pagination.makePaging("approvalDocs", maxRows, ApprovalConst.ROWS, ApprovalConst.PAGES, curPage);
		
		ModelAndView mav = new ModelAndView("approval/approvalDocs");
		mav.addObject("approvalDocs", res);
		mav.addObject("pageBar", pageBar);
		return mav;
	}
	
	@GetMapping("/referenceDocs")
	public ModelAndView referenceDocs(@RequestParam(defaultValue = "1") int curPage,
			@SessionAttribute(MemberConst.USER_KEY) MemberDTO userInfo) {
		List<DocViewDTO> res = null;
		try {
			res = approvalService.getReferenceDocs(userInfo.getId(), curPage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int maxRows = res != null && res.size() > 0 ? res.get(0).getMax_rows() : 1;
		String pageBar = Pagination.makePaging("referenceDocs", maxRows, ApprovalConst.ROWS, ApprovalConst.PAGES, curPage);
		
		ModelAndView mav = new ModelAndView("approval/referenceDocs");
		mav.addObject("referenceDocs", res);
		mav.addObject("pageBar", pageBar);
		return mav;
	}
	
	@GetMapping("/draftDocs")
	public ModelAndView draftDocs(@RequestParam(defaultValue = "1") int curPage,
			@SessionAttribute(MemberConst.USER_KEY) MemberDTO userInfo) {
		List<DocViewDTO> res = null;
		try {
			res = approvalService.getDraftDocs(userInfo.getId(), curPage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int maxRows = res != null && res.size() > 0 ? res.get(0).getMax_rows() : 1;
		String pageBar = Pagination.makePaging("draftDocs", maxRows, ApprovalConst.ROWS, ApprovalConst.PAGES, curPage);
		
		ModelAndView mav = new ModelAndView("approval/draftDocs");
		mav.addObject("draftDocs", res);
		mav.addObject("pageBar", pageBar);
		return mav;
	}
	
	@GetMapping("submitDraft")
	public ModelAndView submitDraftForm(@RequestParam int id,
			@SessionAttribute(MemberConst.USER_KEY) MemberDTO userInfo) {
		Map<String, Object> format = null;
		Map<String, List<OrganDTO>> membersByDept = null;
		try {
			format = approvalService.getFormat(id);
			membersByDept = approvalService.getMembers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (membersByDept != null) {
			membersByDept.forEach((dept, list) ->
				list.removeIf(dto -> dto.getMember_id() == userInfo.getId())
			);
		}
		
		ModelAndView mav = new ModelAndView("approval/submitDraft");
		mav.addObject("format", format);
		mav.addObject("membersByDept", membersByDept);
		return mav;
	}
	
	@PostMapping("submitDraft")
	public ModelAndView submitDraft(DocDTO dto,
			@RequestParam MultipartFile attatchment,
			@RequestParam(required = false) List<Integer> approverIds,
			@RequestParam(required = false) List<Integer> reviewerIds,
			@SessionAttribute(MemberConst.USER_KEY) MemberDTO userInfo) {
		
		dto.setMember_id(userInfo.getId());
		
		// Save attatchment file
		if (!attatchment.isEmpty()) {
			File dir = new File(ApprovalConst.SAVE_PATH);
			if (!dir.exists()) {
	            dir.mkdirs();
	        }
	        File file = new File(dir, attatchment.getOriginalFilename());
	        try {
				FileCopyUtils.copy(attatchment.getBytes(), file);
				dto.setFile_name(attatchment.getOriginalFilename());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		boolean res = false;
		try {
			res = approvalService.submitDraft(dto, approverIds, reviewerIds);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String msg = res ? "결재 요청 성공" : "결재 요청 실패";
		
		ModelAndView mav = new ModelAndView("approval/approvalMsg");
		mav.addObject("msg", msg);
		mav.addObject("dest", "pendingApprovalDocs");
		return mav;
	}
	
	@GetMapping("/docContent")
	public ModelAndView docContent(@RequestParam int id,
			@RequestParam(defaultValue = "0") int approvalEnabled) {
		Map<String, Object> docContent = null;
		List<ApprovalLineDTO> approvers = null;
		List<ApprovalLineDTO> reviewers = null;
		
		try {
			docContent = approvalService.getDocContent(id);
			approvers = approvalService.getApprovers(id);
			reviewers = approvalService.getReviewers(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView("approval/docContent");
		mav.addObject("docContent", docContent);
		mav.addObject("approvers", approvers);
		mav.addObject("reviewers", reviewers);
		mav.addObject("approvalEnabled", approvalEnabled);
		return mav;
	}
	
	@PostMapping("/approve")
	public ModelAndView approve(@SessionAttribute(MemberConst.USER_KEY) MemberDTO userInfo,
			@RequestParam int docId) {
		int res = 0;
		try {
			res = approvalService.approve(docId, userInfo.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String msg = res > 0 ? "결재 성공" : "결재 실패";
		ModelAndView mav = new ModelAndView("approval/approvalMsg");
		mav.addObject("msg", msg);
		mav.addObject("dest", "pendingApprovalDocs");
		return mav;
	}
	
	@PostMapping("/reject")
	public ModelAndView reject(@SessionAttribute(MemberConst.USER_KEY) MemberDTO userInfo,
			@RequestParam int docId) {
		int res = 0;
		try {
			res = approvalService.reject(docId, userInfo.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String msg = res > 0 ? "반려 성공" : "반려 실패";
		ModelAndView mav = new ModelAndView("approval/approvalMsg");
		mav.addObject("msg", msg);
		mav.addObject("dest", "pendingApprovalDocs");
		return mav;
	}
	
	@GetMapping("/approvalAttatchmentDownload")
	public ModelAndView fileDown(String fileName) {
		File f = new File(ApprovalConst.SAVE_PATH + fileName);
		
		ModelAndView mav = new ModelAndView("downloadView");
		mav.addObject("fileDown", f);
		return mav;
	}
}
