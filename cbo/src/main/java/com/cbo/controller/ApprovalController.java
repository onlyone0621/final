package com.cbo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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
	List<FormatDTO> formats(){
		try {
			return approvalService.getFormats();
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
		return null;
	}
	
	@GetMapping("/pendingReferenceDocs")
	public ModelAndView pendingReferenceDocs() {
		return null;
	}
	
	@GetMapping("/scheduledApprovalDocs")
	public ModelAndView scheduledApprovalDocs() {
		return null;
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
	public ModelAndView submitDraftForm() {
		return null;
	}
	
	@PostMapping("submitDraft")
	public ModelAndView submitDraft() {
		return null;
	}
	
	@GetMapping("/docContent")
	public ModelAndView docContent() {
		return null;
	}
	
	@PostMapping("/approve")
	public ModelAndView approve() {
		return null;
	}
	
	@PostMapping("/reject")
	public ModelAndView reject() {
		return null;
	}
}
