package com.cbo.controller;

import java.util.List;
import com.cbo.addr.service.AddrServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cbo.addr.model.AddrDTO;
import com.cbo.addr.service.AddrService;

@Controller
public class AddrController {
 
	@Autowired
	private AddrService service;
	
    
    @RequestMapping("/updateAddr")
    public String updateAddr() {
    	
    	
    	return "addr/addrModify";
    }
    
    @GetMapping("/addrMain")
    public ModelAndView addrList() {
    	
    	List<AddrDTO> lists = null;
    	
    	try {
			lists = service.addrList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ModelAndView mav = new ModelAndView();
    	mav.addObject("lists",lists);
    	mav.setViewName("addr/addrMain");
    	
    	return mav;
    	
    }

}
