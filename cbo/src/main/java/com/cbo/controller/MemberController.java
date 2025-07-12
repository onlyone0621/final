package com.cbo.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cbo.dept.model.DeptDTO;
import com.cbo.grade.model.GradeDTO;
import com.cbo.member.model.MemberDTO;
import com.cbo.member.service.MemberService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	@Autowired
	private MemberService service;
	
	public MemberController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("memberLogin")
	public String memberLoginForm(HttpServletRequest req, Model m) {
		String saveid = "";

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("saveid".equals(cookie.getName())) {
                    saveid = cookie.getValue();
                    break;
                }
            }
        }
        m.addAttribute("saveid", saveid);
		return "member/memberLogin";
	}
	
	@ResponseBody
	@GetMapping("login")
	public String memberLogin(@RequestParam("user_id")String user_id, @RequestParam("pwd") String pwd,@RequestParam(value="saveid", required = false) boolean saveid, HttpServletRequest req, HttpServletResponse res) {
		String getId = "";
		String getPwd = "";
		String result = "";
		String path = "";
		HttpSession session = req.getSession();
		MemberDTO dto = null;
		try {
			getId = service.getMemberId(user_id);
			getPwd = service.getMemberPwd(user_id);
			dto = service.getMember(user_id);
			if(getId == "" || getId==null) {
				result = getId;
			}else {
				if(getPwd.equals(pwd)) {
			        session.setAttribute(com.cbo.constant.MemberConst.USER_KEY, dto);
			        session.setAttribute("admin_id", dto.getId());
			        if(saveid==false) {
			        	Cookie ck = new Cookie("saveid", getId);
						ck.setMaxAge(0);
						res.addCookie(ck);
					}else {
						Cookie ck = new Cookie("saveid", getId);
						ck.setMaxAge(60*60*24*30);
						res.addCookie(ck);
					}
			        result = "success";
				}else {
					result = getPwd;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	@GetMapping("memberJoin")
	public ModelAndView memberJoinForm() {
		List<GradeDTO> gradeList = null;
		List<DeptDTO> deptList = null;
		try {
			deptList = service.getDept();
			gradeList = service.getGrade();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("deptList", deptList);
		mav.addObject("gradeList", gradeList);
		mav.setViewName("member/memberJoin");
		return mav;
	}
	
	@ResponseBody
	@GetMapping("memberIdCheck")
	public String checkMemberId(@RequestParam("user_id") String user_id) {
		String userId = null;
		try {
			userId = service.getMemberId(user_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userId;
	}
	
	@PostMapping("memberJoin")
	public ModelAndView memberJoin(MemberDTO dto, @RequestParam("adrNum") String adrNum) {
		int result = 0;
		dto.setAddress(dto.getAddress()+"("+adrNum+")");
		try {
			result = service.memberJoin(dto);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView();
		String msg = result > 0 ? "회원가입이 완료되었습니다." : "회원가입에 실패하였습니다.";
		mav.addObject("msg", msg);
		if(result == 0) {
			mav.addObject("gourl", "memberJoin");
			mav.setViewName("member/memberMsg");
		}else {
			mav.addObject("gourl", "memberLogin");
			mav.setViewName("member/memberMsg");
		}
		return mav;
	}
	
	@GetMapping("memberLogout")
	public String memberLogout(HttpServletRequest req, Model m) {
		HttpSession session = req.getSession();
		session.removeAttribute("udto");
		String saveid = "";

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("saveid".equals(cookie.getName())) {
                    saveid = cookie.getValue();
                    break;
                }
            }
        }
        m.addAttribute("saveid", saveid);
		return "member/memberLogin";
	}
	
	@PostMapping("memberIdFind")
	public ModelAndView memberIdFind(@RequestParam("name")String name, @RequestParam("email")String email) {
		String user_id = "";
		String msg = "";
		String gourl = "";
		try {
			user_id = service.getMemberId2(email, name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView();
		if(user_id=="" || user_id==null) {
			msg = "이름 또는 이메일을 잘못 입력하였습니다.";
			gourl = "memberIdFind";
			mav.addObject("msg", msg);
			mav.addObject("gourl", gourl);
			mav.setViewName("member/memberMsg");
		}else {
			msg = "회원님의 아이디는 "+user_id+"입니다.";
			mav.addObject("msg", msg);
			mav.setViewName("member/memberIdFind_ok");
		}
		return mav;
	}
	
	@PostMapping("memberPwdFind")
	public ModelAndView memberPwdFind(@RequestParam("user_id")String user_id, @RequestParam("email")String email) {
		String pwd = "";
		String msg = "";
		String gourl = "";
		try {
			pwd = service.getMemberPwd2(email, user_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView();
		if(pwd=="" || pwd==null) {
			msg = "아이디 또는 이메일을 잘못 입력하였습니다.";
			gourl = "memberPwdFind";
			mav.addObject("msg", msg);
			mav.addObject("gourl", gourl);
			mav.setViewName("member/memberMsg");
		}else {
			mav.addObject("user_id", user_id);
			mav.setViewName("member/memberSetNewPwd");
		}
		return mav;
	}
	
	@PostMapping("memberSetNewPwd")
	public ModelAndView memberSetNewPwd(@RequestParam("pwd")String pwd, @RequestParam("user_id")String user_id) {
		int result = 0;
		String msg = "";
		try {
			result = service.setNewPwd(user_id, pwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		msg = result > 0 ? "비밀번호가 변경되었습니다. 변경된 비밀번호로 로그인해주세요." : "비밀번호 변경에 실패하였습니다.";
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg);
		mav.addObject("gourl", "memberLogin");
		mav.setViewName("member/memberMsg");
		return mav;
	}
	
	@GetMapping("memberIdFind")
	public String memberIdFindForm() {
		return "member/memberIdFind";
	}
	
	@GetMapping("memberIdFind_ok")
	public String memberIdFind_okForm() {
		return "member/memberIdFind_ok";
	}
	
	@GetMapping("memberPwdFind")
	public String memberPwdFindForm() {
		return "member/memberPwdFind";
	}
	
	@GetMapping("memberSetNewPwd")
	public String memberSetNewPwdForm() {
		return "member/memberSetNewPwd";
	}
	
	@GetMapping("memberPwdUpdate")
	public String memberPwdUpdateForm() {
		return "member/memberPwdUpdate";
	}
	
	@GetMapping("memberInfoUpdate")
	public ModelAndView memberInfoUpdateForm() {
		List<GradeDTO> gradeList = null;
		List<DeptDTO> deptList = null;
		try {
			
			deptList = service.getDept();
			gradeList = service.getGrade();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("deptList", deptList);
		mav.addObject("gradeList", gradeList);
		mav.setViewName("member/memberInfoUpdate");
		return mav;
	}
	
	public void profileImageUpload(MultipartFile profileImage) {
		try {
	        String contentType = profileImage.getContentType();
	        if (contentType == null || !contentType.startsWith("image")) {
	            throw new IllegalArgumentException("이미지 파일만 업로드할 수 있습니다.");
	        }
	        File dir = new File("C:/upload/profileImage/");
	        if (!dir.exists()) {
	            dir.mkdirs();
	        }
	        File image = new File(dir, profileImage.getOriginalFilename());
	        FileCopyUtils.copy(profileImage.getBytes(), image);

	    } catch (IOException | IllegalArgumentException e) {
	        e.printStackTrace();
	    }
		
	}
	
	@PostMapping("memberInfoUpdate")
	public ModelAndView memberInfoUpdate(@RequestParam("adrNum")String adrNum, @RequestParam(value = "profileImage", required = false) MultipartFile profileImage, HttpServletRequest req, MemberDTO dto) {
		int result = 0;
		String msg = "";
		HttpSession session = req.getSession();
		MemberDTO udto = (MemberDTO)(session.getAttribute(com.cbo.constant.MemberConst.USER_KEY));
		dto.setAddress(dto.getAddress()+"("+adrNum+")");
		if(profileImage==null || profileImage.isEmpty()) {
			if(udto.getProfile_image() != null) {
				dto.setProfile_image(udto.getProfile_image());				
			}else {
				dto.setProfile_image("/profileImage/defaultProfileImage.jpg");
			}
		}else {
			dto.setProfile_image("/profileImage/"+profileImage.getOriginalFilename());
			profileImageUpload(profileImage);
		}
		try {
			result = service.setMemberInfo(dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		msg = result > 0 ? "정보가 수정되었습니다." : "정보 수정에 실패하였습니다.";
		session.setAttribute(com.cbo.constant.MemberConst.USER_KEY, dto);
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg);
		mav.addObject("gourl", "memberInfoUpdate");
		mav.setViewName("member/memberMsg");
		return mav;
	}
	
	@PostMapping("memberPwdUpdate")
	public ModelAndView memberPwdUpdate(HttpServletRequest req, @RequestParam("pwd")String pwd, @RequestParam("user_id")String user_id) {
		HttpSession session = req.getSession();
		int result = 0;
		String msg = "";
		try {
			result = service.setNewPwd(user_id, pwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		msg = result > 0 ? "비밀번호가 변경되었습니다. 변경된 비밀번호로 로그인 해주세요." : "비밀번호 변경에 실패하였습니다.";
		ModelAndView mav = new ModelAndView();
		if(result>0) {
			session.removeAttribute("udto");
			mav.addObject("msg", msg);
			mav.addObject("gourl", "memberLogin");
			mav.setViewName("member/memberMsg");
		}else {
			mav.addObject("msg", msg);
			mav.addObject("gourl", "memberPwdUpdate");
			mav.setViewName("member/memberMsg");
		}
		return mav;
	}
}
