package com.jatin.Chat.Server.handler;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

public class ChatHandler extends TextWebSocketHandler {

    private final List<WebSocketSession> WSsessions = new ArrayList<>();
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        WSsessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        for (WebSocketSession sessions : WSsessions){
            sessions.sendMessage(message);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        WSsessions.remove(session);
    }
}
