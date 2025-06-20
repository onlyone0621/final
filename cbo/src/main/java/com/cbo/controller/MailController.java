package com.cbo.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cbo.mail.model.MailDTO;
import com.cbo.mail.service.*;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class MailController {
	@Autowired
	private MailServiceImple service;
	
	@ResponseBody
	@GetMapping("/sendEmailCode")
	public String emailCheck(@RequestParam("email")String email) throws MessagingException, UnsupportedEncodingException {
        String authCode = "";
        try {
			authCode = service.sendSimpleMessage(email);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return authCode; // Response body에 값을 반환
    }

}
