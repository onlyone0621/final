package com.cbo.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.cbo.member.model.MemberDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.net.Authenticator.RequestorType;
import java.net.http.HttpRequest;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class EchoHandler extends TextWebSocketHandler {
	private final Map<String, WebSocketSession> userSessions;
	
	
	public EchoHandler() {
		userSessions = new ConcurrentHashMap<>();
	}
	//입장
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		Map<String, Object> m = session.getAttributes();
		MemberDTO dto = (MemberDTO)(m.get(com.cbo.constant.MemberConst.USER_KEY));
		String user_id = dto.getUser_id();
		String name = dto.getName();
		userSessions.put(user_id, session);
		System.out.println(name+"님 입장");
	}

	//처리(메세지 작성 및 전달)
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		Map<String, Object> m = session.getAttributes();
		MemberDTO dto = (MemberDTO)(m.get(com.cbo.constant.MemberConst.USER_KEY));
		String nick = dto.getName();
		
		System.out.println(nick+"님으로부터 받은 메세지 :" + message.getPayload());
		
	}
	//퇴장
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println(session.getId()+"번 사람 연결 끊김");
		userSessions.remove(session);
	}
	
}