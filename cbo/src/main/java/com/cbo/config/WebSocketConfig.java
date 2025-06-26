package com.cbo.config;

import com.cbo.websocket.EchoHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new EchoHandler(), "/ws/chat")
        		.addInterceptors(new HttpSessionHandshakeInterceptor()) // HttpSession을 가져오기 위한 인터셉터
                .setAllowedOrigins("*"); // CORS 허용 도메인 설정
    }
}
