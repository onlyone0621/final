package com.cbo.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.cbo.member.model.MemberDTO;
import com.cbo.messenger.model.ChatMessageDTO;
import com.cbo.messenger.service.ChatMessageService;
import com.cbo.messenger.service.ChatMessageServiceImple;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class EchoHandler extends TextWebSocketHandler {
	private final Map<String, Set<WebSocketSession>> roomSessions =  new ConcurrentHashMap<>();;
	private final ChatMessageService service;
	
	@Autowired
	public EchoHandler(ChatMessageService service) {
		this.service = service;
	}
	
	//입장
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String room_id = getRoomId(session);
		MemberDTO dto = getMember(session);
		if (dto == null || room_id == null) {
            session.close(CloseStatus.BAD_DATA);
            return;
        }
		Set<WebSocketSession> sessions = roomSessions.get(dto.getId());

		if (sessions == null) {
		    sessions = ConcurrentHashMap.newKeySet();
		    roomSessions.put(room_id, sessions);
		}
		sessions.add(session);
	}

	//메세지 작성 및 전달
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String room_id_s = getRoomId(session);
		int room_id = Integer.parseInt(room_id_s);
        MemberDTO dto = getMember(session);
        String content = message.getPayload();

        if (dto == null || room_id_s == null || content == null || content == "") return;
        
        String totalMessage;
        if(content.equals(dto.getName()+"님이 퇴장하셨습니다.")) {
        	totalMessage = content;
        }else {
        	totalMessage = dto.getName() + " : " + content;
        	ChatMessageDTO cdto = new ChatMessageDTO(0, room_id, dto.getId(), null , content);
            service.addMessage(cdto);
        }
        broadcastToRoom(room_id_s, totalMessage);
	}
	
	//퇴장
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String room_id_s = getRoomId(session);
		int room_id = Integer.parseInt(room_id_s);
        MemberDTO dto = getMember(session);
        if (dto != null && room_id_s != null) {
            Set<WebSocketSession> sessions = roomSessions.get(room_id);
            if (sessions != null) {
                sessions.remove(dto.getId());
                if (sessions.isEmpty()) {
                    roomSessions.remove(room_id);
                }
            }
        }
	}
	
	
	private String getRoomId(WebSocketSession session) {
        String query = session.getUri().getQuery();
        if (query == null) return null;

        for (String param : query.split("&")) {
            String[] pair = param.split("=");
            if (pair.length == 2 && pair[0].equals("room_id")) {
                return pair[1];
            }
        }
        return null;
    }
	
	private MemberDTO getMember(WebSocketSession session) {
		MemberDTO dto = (MemberDTO)(session.getAttributes().get(com.cbo.constant.MemberConst.USER_KEY));
		return dto;
	}

	private void broadcastToRoom(String room_id, String message) throws Exception {
		Set<WebSocketSession> sessions = roomSessions.get(room_id);
		if (sessions != null) {
			for (WebSocketSession s : sessions) {
				if (s.isOpen()) {
					s.sendMessage(new TextMessage(message));
				}
			}
		}
	}
}