package com.cbo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ApprovalController {
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
