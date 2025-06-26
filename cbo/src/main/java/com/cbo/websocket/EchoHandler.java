package com.cbo.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.cbo.member.model.MemberDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
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
		if(userSessions.get(user_id) == null) {
			userSessions.put(user_id, session);
			for(WebSocketSession temp:userSessions.values()) {
				temp.sendMessage(new TextMessage(name + "님 입장"));
			}
		}
	}

	//처리(메세지 작성 및 전달)
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		Map<String, Object> m = session.getAttributes();
		MemberDTO dto = (MemberDTO)(m.get(com.cbo.constant.MemberConst.USER_KEY));
		String name = dto.getName();
		if((message.getPayload()).equals(name+"님 퇴장")) {
			for(WebSocketSession temp:userSessions.values()) {
				temp.sendMessage(new TextMessage(message.getPayload()));
			}
		}else {
			for(WebSocketSession temp:userSessions.values()) {
				temp.sendMessage(new TextMessage(name + " : " + message.getPayload()));
			}
		}
	}
	
	//퇴장
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		Map<String, Object> m = session.getAttributes();
		MemberDTO dto = (MemberDTO)(m.get(com.cbo.constant.MemberConst.USER_KEY));
		String user_id = dto.getUser_id();
		String name = dto.getName();
		userSessions.remove(user_id);
	}
}