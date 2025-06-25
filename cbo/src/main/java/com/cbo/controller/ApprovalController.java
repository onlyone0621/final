package com.cbo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cbo.approval.model.ApprovalLineDTO;
import com.cbo.approval.model.DocDTO;
import com.cbo.approval.model.DocViewDTO;
import com.cbo.approval.model.FormatDTO;
import com.cbo.approval.service.ApprovalService;

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
	public ModelAndView pendingApprovalDocs() {
		List<DocViewDTO> res = null;
		try {
			res = approvalService.getPendingApprovalDocs(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView("approval/pendingApprovalDocs");
		mav.addObject("pendingApprovalDocs", res);
		return mav;
	}
	
	@GetMapping("/pendingReferenceDocs")
	public ModelAndView pendingReferenceDocs() {
		List<DocViewDTO> res = null;
		try {
			res = approvalService.getPendingReferenceDocs(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView("approval/pendingReferenceDocs");
		mav.addObject("pendingReferenceDocs", res);
		return mav;
	}
	
	@GetMapping("/scheduledApprovalDocs")
	public ModelAndView scheduledApprovalDocs() {
		List<DocViewDTO> res = null;
		try {
			res = approvalService.getScheduledApprovalDocs(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView("approval/scheduledApprovalDocs");
		mav.addObject("scheduledApprovalDocs", res);
		return mav;
	}
	
	@GetMapping("/approvalDocs")
	public ModelAndView approvalDocs() {
		List<DocViewDTO> res = null;
		try {
			res = approvalService.getApprovalDocs(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView("approval/approvalDocs");
		mav.addObject("approvalDocs", res);
		return mav;
	}
	
	@GetMapping("/referenceDocs")
	public ModelAndView referenceDocs() {
		List<DocViewDTO> res = null;
		try {
			res = approvalService.getReferenceDocs(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView("approval/referenceDocs");
		mav.addObject("refernceDocs", res);
		return mav;
	}
	
	@GetMapping("/draftDocs")
	public ModelAndView draftDocs() {
		List<DocViewDTO> res = null;
		try {
			res = approvalService.getDraftDocs(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView("approval/draftDocs");
		mav.addObject("draftDocs", res);
		return mav;
	}
	
	@GetMapping("submitDraft")
	public ModelAndView submitDraftForm(int id) {
		Map<String, Object> format = null;
		try {
			format = approvalService.getFormat(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView("approval/submitDraft");
		mav.addObject("format", format);
		return mav;
	}
	
	@PostMapping("submitDraft")
	public ModelAndView submitDraft() {
		return null;
	}
	
	@GetMapping("/docContent")
	public ModelAndView docContent(int id) {
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
	public ModelAndView approve() {
		return null;
	}
	
	@PostMapping("/reject")
	public ModelAndView reject() {
		return null;
	}
	
	@GetMapping("/ckeditor")
	public String editorTest(Model model) {
		return "approval/CKEditorTest";
	}
	
	@PostMapping("/ckeditor")
	public String insertTemplate(FormatDTO dto, Model model) {
		int res = 0;
		try {
			res = approvalService.insertTemplate(dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String msg = res > 0 ? "양식 등록 성공" : "양식 등록 실패";
		String dest = "/";
		model.addAttribute("msg", msg);
		model.addAttribute("dest", dest);
		return "approval/approvalMsg";
	}
}
