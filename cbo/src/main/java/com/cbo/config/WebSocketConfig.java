package com.cbo.config;

import com.cbo.messenger.service.ChatMessageService;
import com.cbo.websocket.EchoHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	@Autowired
	private ChatMessageService service;
	
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new EchoHandler(service), "/ws/chat")
        		.addInterceptors(new HttpSessionHandshakeInterceptor()) // HttpSession을 가져오기 위한 인터셉터
                .setAllowedOrigins("*");
    }
}
