package com.cbo.mail.service;

import com.cbo.mail.model.MailDTO;

import jakarta.mail.internet.MimeMessage;

public interface MailService {
	public String createNumber();
	public MimeMessage createMail(String mail, String number) throws Exception;
	public String sendSimpleMessage(String sendEmail) throws Exception;
}
