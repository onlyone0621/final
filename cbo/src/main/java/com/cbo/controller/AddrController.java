package com.cbo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cbo.addr.model.AddrDTO;
import com.cbo.addr.service.AddrService;

@Controller
public class AddrController {
 
	@Autowired
	private AddrService service;
	
    
    @GetMapping("/addrModify")
    public String updateAddr() {
    	
    	
    	return "addr/addrModify";
    }
    
    @GetMapping("/addrMain")
    public ModelAndView addrList(@CookieValue(value = "saveid", required = false)String saveid) {
    	
    	List<AddrDTO> addrLists = null;
    	List<AddrDTO> personalLists = null;
    	List<AddrDTO> deptLists = null;
    	String dept = null;
    	String id = null;
    	try {
    		id = service.findUserId(saveid); // 접속해있는 유저 고유 아이디 찾기
			addrLists = service.addrList(); //전체 주소록 눌렀을때 연락처 보여주기
			personalLists = service.personalList(id); //개인 주소록 그룹 보여주기
			deptLists = service.deptList(saveid); //부서 주소록 그룹들 보여주기
			dept = service.userDept(saveid); // 부서 주소록 부서 이름 나타낼때 쓰는거
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	
    	ModelAndView mav = new ModelAndView();
    	mav.addObject("addrLists",addrLists);
    	mav.addObject("personalLists",personalLists);
    	mav.addObject("deptLists",deptLists);
    	mav.addObject("dept",dept);
    	mav.setViewName("addr/addrMain");
    	
    	return mav;
    	
    }
    
    @GetMapping("/goInsertPage")
    public String goInsertPage() {
    	
    	
    	return "/addr/insertPersonalAllAddr";
    }
    
    
	/*
	 * @PostMapping("/insertAddr")
	 * 
	 * @ResponseBody public int insertPersonalAllAddr() {
	 * 
	 * 
	 * 
	 * }
	 * 
	 * public int insertPersonalgroupAddr() {
	 * 
	 * 
	 * }
	 */
    @PostMapping("/addr/addGroup")
    @ResponseBody
    public int addPersonalAddr(@RequestBody Map<String, String> param,@CookieValue(value = "saveid", required = false)String saveid) {
    	
    	int result = 0;
    	String id = "";
    	String name = param.get("groupName");
		try {
			id = service.findUserId(saveid);
			result = service.addPersonalAddr(name,id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	return result;
    	
    }
    
    @GetMapping("/groupAddrList/{groupName}")
    public ModelAndView groupAddrList(@PathVariable("groupName")String name,
    		@CookieValue(value = "saveid", required = false)String saveid) {
    	
    	List<AddrDTO> lists = null;
    	List<AddrDTO> addrLists = null;
    	List<AddrDTO> personalLists = null;
    	List<AddrDTO> deptLists = null;
    	String dept = null;
    	try {
    		String id = service.findUserId(saveid);
			lists = service.groupAddrList(name);
			personalLists = service.personalList(id);
			deptLists = service.deptList(saveid);
			dept = service.userDept(saveid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	ModelAndView mav = new ModelAndView();
    	if(!(name.equals(""))&&name!=null) {
        	mav.addObject("groupName",name);
        	}
    	mav.addObject("groupAddrLists",lists);
    	mav.addObject("addrLists",addrLists);
    	mav.addObject("personalLists",personalLists);
    	mav.addObject("deptLists",deptLists);
    	mav.addObject("dept",dept);
    	
    	mav.setViewName("/addr/groupAddrList");
    	
    	return mav;
    }
    
    
}
