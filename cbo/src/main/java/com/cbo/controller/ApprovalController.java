package com.cbo.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.cbo.approval.model.FormatDTO;
import com.cbo.approval.service.ApprovalService;
import com.cbo.constant.ApprovalConst;
import com.cbo.constant.MemberConst;
import com.cbo.member.model.MemberDTO;
import com.cbo.member.model.OrganDTO;

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
	public ModelAndView pendingApprovalDocs(@SessionAttribute(MemberConst.USER_KEY) MemberDTO userInfo) {
		List<DocViewDTO> res = null;
		try {
			res = approvalService.getPendingApprovalDocs(userInfo.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView("approval/pendingApprovalDocs");
		mav.addObject("pendingApprovalDocs", res);
		return mav;
	}
	
	@GetMapping("/pendingReferenceDocs")
	public ModelAndView pendingReferenceDocs(@SessionAttribute(MemberConst.USER_KEY) MemberDTO userInfo) {
		List<DocViewDTO> res = null;
		try {
			res = approvalService.getPendingReferenceDocs(userInfo.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView("approval/pendingReferenceDocs");
		mav.addObject("pendingReferenceDocs", res);
		return mav;
	}
	
	@GetMapping("/scheduledApprovalDocs")
	public ModelAndView scheduledApprovalDocs(@SessionAttribute(MemberConst.USER_KEY) MemberDTO userInfo) {
		List<DocViewDTO> res = null;
		try {
			res = approvalService.getScheduledApprovalDocs(userInfo.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView("approval/scheduledApprovalDocs");
		mav.addObject("scheduledApprovalDocs", res);
		return mav;
	}
	
	@GetMapping("/approvalDocs")
	public ModelAndView approvalDocs(@SessionAttribute(MemberConst.USER_KEY) MemberDTO userInfo) {
		List<DocViewDTO> res = null;
		try {
			res = approvalService.getApprovalDocs(userInfo.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView("approval/approvalDocs");
		mav.addObject("approvalDocs", res);
		return mav;
	}
	
	@GetMapping("/referenceDocs")
	public ModelAndView referenceDocs(@SessionAttribute(MemberConst.USER_KEY) MemberDTO userInfo) {
		List<DocViewDTO> res = null;
		try {
			res = approvalService.getReferenceDocs(userInfo.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView("approval/referenceDocs");
		mav.addObject("refernceDocs", res);
		return mav;
	}
	
	@GetMapping("/draftDocs")
	public ModelAndView draftDocs(@SessionAttribute(MemberConst.USER_KEY) MemberDTO userInfo) {
		List<DocViewDTO> res = null;
		try {
			res = approvalService.getDraftDocs(userInfo.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView("approval/draftDocs");
		mav.addObject("draftDocs", res);
		return mav;
	}
	
	@GetMapping("submitDraft")
	public ModelAndView submitDraftForm(@RequestParam int id) {
		Map<String, Object> format = null;
		List<OrganDTO> members = null;
		try {
			format = approvalService.getFormat(id);
			members = approvalService.getMembers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Process members grouping by dept
		Map<String, List<OrganDTO>> membersByDept = members.stream()
		 	.collect(Collectors.groupingBy(OrganDTO :: getDept_name, LinkedHashMap :: new, Collectors.toList()));
		
		ModelAndView mav = new ModelAndView("approval/submitDraft");
		mav.addObject("format", format);
		mav.addObject("membersByDept", membersByDept);
		return mav;
	}
	
	@PostMapping("submitDraft")
	public ModelAndView submitDraft(DocDTO dto,
			@RequestParam MultipartFile attatchment,
			@RequestParam List<Integer> approvers,
			@RequestParam List<Integer> reviewers,
			@SessionAttribute(MemberConst.USER_KEY) MemberDTO userInfo) {
		
		dto.setMember_id(userInfo.getId());
		
		return null;
	}
	
	@GetMapping("/docContent")
	public ModelAndView docContent(@RequestParam int id) {
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
		return mav;
	}
	
	@PostMapping("/approve")
	public ModelAndView approve(@SessionAttribute(MemberConst.USER_KEY) MemberDTO userInfo, @RequestParam int docId) {
		int res = 0;
		try {
			res = approvalService.approve(docId, userInfo.getId(), ApprovalConst.APPROVED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String msg = res > 0 ? "결재 성공" : "결재 실패";
		ModelAndView mav = new ModelAndView("approval/approvalMsg");
		mav.addObject("msg", msg);
		mav.addObject("dest", "approvalMain");
		return mav;
	}
	
	@PostMapping("/reject")
	public ModelAndView reject(@SessionAttribute(MemberConst.USER_KEY) MemberDTO userInfo, @RequestParam int docId) {
		int res = 0;
		try {
			res = approvalService.reject(docId, userInfo.getId(), ApprovalConst.REJECTED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String msg = res > 0 ? "반려 성공" : "반려 실패";
		ModelAndView mav = new ModelAndView("approval/approvalMsg");
		mav.addObject("msg", msg);
		mav.addObject("dest", "approvalMain");
		return mav;
	}
	
}
