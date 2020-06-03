package com.jatin.Chat.Server.configuration;

import com.jatin.Chat.Server.handler.ChatHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    public final static String chatEndPoint = "/chat";
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(getWebSocketHandler(),chatEndPoint)
                .setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler getWebSocketHandler(){
        return new ChatHandler();
    }
}
