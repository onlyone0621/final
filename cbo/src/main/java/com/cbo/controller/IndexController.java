package com.cbo.controller;

import java.net.http.HttpRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class IndexController {

	
	@RequestMapping("/")
	public String index(HttpServletRequest req, Model m) {
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
	
	@RequestMapping("main")
	public String mainPage() {
		return "index";
	}

}
