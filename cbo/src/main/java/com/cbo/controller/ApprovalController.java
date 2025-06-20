package com.cbo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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
		return null;
	}
	
	@GetMapping("/referenceDocs")
	public ModelAndView referenceDocs() {
		return null;
	}
	
	@GetMapping("/draftDocs")
	public ModelAndView draftDocs() {
		return null;
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
