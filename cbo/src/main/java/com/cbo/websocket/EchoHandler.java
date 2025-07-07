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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
		Set<WebSocketSession> sessions = roomSessions.get(room_id);

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
	    
	    String rawPayload = message.getPayload();
	    if (dto == null || room_id_s == null || rawPayload == null || rawPayload.trim().isEmpty()) return;

	    ObjectMapper mapper = new ObjectMapper();
	    JsonNode jsonNode = mapper.readTree(rawPayload);
	    
	    String type = jsonNode.get("type").asText();
	    String content = jsonNode.get("content").asText();

	    if (type == null || content == null) return;

	    ChatMessageDTO cdto = null;

	    if ("system".equals(type)) {
	        // 시스템 메시지 저장
	        cdto = new ChatMessageDTO(0, room_id, dto.getId(), null, content, "system");
	        service.addMessage(cdto);
	    } else {
	        // 일반 메시지
	        cdto = new ChatMessageDTO(0, room_id, dto.getId(), null, content, "user");
	        service.addMessage(cdto);
	    }
	 // 전송할 메시지를 객체 형태로 구성
	    ObjectNode sendMessage = mapper.createObjectNode();
	    sendMessage.put("type", type);
	    sendMessage.put("user", dto.getName()); // sender name
	    sendMessage.put("content", content);
	    sendMessage.put("room_id", room_id);

	    // JSON 문자열로 변환
	    String jsonMessage = mapper.writeValueAsString(sendMessage);
	    broadcastToRoom(room_id_s, jsonMessage);
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