package com.cbo.controller;

import java.io.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cbo.admin.service.AdminService;
import com.cbo.dept.model.DeptDTO;
import com.cbo.grade.model.GradeDTO;
import com.cbo.member.model.MemberDTO;
import com.cbo.page.PageModule;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

	@Autowired
	private AdminService service;
	
	

	// 세션 아이디 가져옴
	// 관리자 체크
	private boolean isAdmin(HttpSession session) {
	    MemberDTO udto = (MemberDTO) session.getAttribute(com.cbo.constant.MemberConst.USER_KEY);
	    return udto != null && udto.getId() == 1;
	}

	
	@ControllerAdvice
	public class GlobalModelAdvice {
	    @ModelAttribute("adminId")
	    public Integer addAdminId(HttpSession session) {
	        MemberDTO udto = (MemberDTO) session.getAttribute(com.cbo.constant.MemberConst.USER_KEY);
	        return (udto != null) ? udto.getId() : null;
	    }
	}

	//a멤버 목록

	@GetMapping("/admin/memberAllList")
	public ModelAndView memberList(@RequestParam(value = "cp", defaultValue = "1") int cp, HttpSession session) {
	    ModelAndView mav = new ModelAndView();

	    if (!isAdmin(session)) {
	        mav.addObject("msg", "관리자만 접근 가능합니다.");
	        mav.addObject("goUrl", "/main");
	        mav.setViewName("admin/adminMsg");
	        return mav;
	    }

	    try {
	        int listSize = 10;
	        int pageSize = 5;
	        int totalCnt = service.memberTotalCount();
	        List<MemberDTO> members = service.selectMemberDetailList(cp, listSize);
	        String pageStr = PageModule.makePaging("/admin/memberAllList", totalCnt, listSize, pageSize, cp);

	        mav.addObject("members", members);
	        mav.addObject("pageStr", pageStr);
	        mav.addObject("cp", cp);
	        mav.setViewName("admin/adminMemberList");
	    } catch (Exception e) {
	        e.printStackTrace();
	        mav.addObject("msg", "회원 목록 조회 중 오류가 발생했습니다.");
	        mav.addObject("goUrl", "/main");
	        mav.setViewName("admin/adminMsg");
	    }
	    return mav;
	}

    
    // 수정 폼
	@GetMapping("/admin/memberUpdate/{user_id}")
	public ModelAndView adminMemberUpdateForm(@PathVariable String user_id, HttpSession session) {
	    ModelAndView mav = new ModelAndView();

	    if (!isAdmin(session)) {
	        mav.addObject("msg", "관리자만 접근 가능합니다.");
	        mav.addObject("goUrl", "/main");
	        mav.setViewName("admin/adminMsg");
	        return mav;
	    }

	    try {
	        MemberDTO member = service.getMember(user_id);
	        List<DeptDTO> deptList = service.getDept();
	        List<GradeDTO> gradeList = service.getGrade();

	        mav.addObject("member", member);
	        mav.addObject("deptList", deptList);
	        mav.addObject("gradeList", gradeList);
	        mav.setViewName("admin/adminMemberUpdate");
	    } catch (Exception e) {
	        e.printStackTrace();
	        mav.addObject("msg", "회원 정보 조회 중 오류 발생");
	        mav.addObject("goUrl", "/admin/memberAllList");
	        mav.setViewName("admin/adminMsg");
	    }
	    return mav;
	}

    //a멤버 수정 기능
	@PostMapping("/adminMemberUpdate")
	public ModelAndView adminMemberUpdateSubmit(MemberDTO dto,
	                                            @RequestParam("profileImage") MultipartFile file,
	                                            HttpSession session) {
	    ModelAndView mav = new ModelAndView();

	    if (!isAdmin(session)) {
	        mav.addObject("msg", "관리자만 접근 가능합니다.");
	        mav.addObject("goUrl", "/main");
	        mav.setViewName("admin/adminMsg");
	        return mav;
	    }

	    try {
	        if (!file.isEmpty()) {
	            String uploadDir = "C:/upload/profileImage/";
	            File dir = new File(uploadDir);
	            if (!dir.exists()) {
	                dir.mkdirs();
	            }

	            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
	            file.transferTo(new File(uploadDir + fileName));
	            dto.setProfile_image(fileName);
	        }

	        int result = service.setMemberInfo(dto);
	        String msg = result > 0 ? "회원 정보가 수정되었습니다." : "회원 정보 수정에 실패했습니다.";
	        mav.addObject("msg", msg);
	        mav.addObject("goUrl", "/admin/memberAllList");
	        mav.setViewName("admin/adminMsg");
	    } catch (Exception e) {
	        e.printStackTrace();
	        mav.addObject("msg", "회원 정보 수정 중 오류 발생");
	        mav.addObject("goUrl", "/admin/memberAllList");
	        mav.setViewName("admin/adminMsg");
	    }

	    return mav;
	}
	
	// 멤버 삭제 페이지 이동
	@GetMapping("/admin/memberDeleteList")
	public ModelAndView memberDeleteList(HttpSession session) {
	    ModelAndView mav = new ModelAndView();

	    if (!isAdmin(session)) {
	        mav.addObject("msg", "관리자만 접근 가능합니다.");
	        mav.addObject("goUrl", "/main");
	        mav.setViewName("admin/adminMsg");
	        return mav;
	    }

	    try {
	        List<MemberDTO> members = service.basicMemberList();
	        mav.addObject("members", members);
	        mav.setViewName("admin/adminMemberDelete");
	    } catch (Exception e) {
	        e.printStackTrace();
	        mav.addObject("msg", "회원 삭제 목록 조회 중 오류 발생");
	        mav.addObject("goUrl", "/admin/memberAllList");
	        mav.setViewName("admin/adminMsg");
	    }

	    return mav;
	}
	
	// 멤버 삭제 기능
	@PostMapping("/admin/memberDeleteList")
	public ModelAndView deleteSubmit(@RequestParam("mid") List<Integer> mid, HttpSession session) {
	    ModelAndView mav = new ModelAndView();

	    if (!isAdmin(session)) {
	        mav.addObject("msg", "관리자만 접근 가능합니다.");
	        mav.addObject("goUrl", "/main");
	        mav.setViewName("admin/adminMsg");
	        return mav;
	    }

	    try {
	        int result = service.DeleteMembers(mid);
	        String msg = result > 0 ? result + "명의 회원이 삭제되었습니다." : "회원 삭제에 실패했습니다.";
	        mav.addObject("msg", msg);
	        mav.addObject("goUrl", "/admin/memberDeleteList");
	        mav.setViewName("admin/adminMsg");
	    } catch (Exception e) {
	        e.printStackTrace();
	        mav.addObject("msg", "회원 삭제 중 오류 발생");
	        mav.addObject("goUrl", "/admin/memberDeleteList");
	        mav.setViewName("admin/adminMsg");
	    }

	    return mav;
	}
	
	
}










