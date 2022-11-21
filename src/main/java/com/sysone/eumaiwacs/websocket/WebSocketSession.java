package com.sysone.eumaiwacs.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class WebSocketSession {

    private Set<org.springframework.web.socket.WebSocketSession> sessions = new HashSet<>();

    public void sendMessage(String message) {
        TextMessage textMessage = new TextMessage(message);

        for (org.springframework.web.socket.WebSocketSession session : sessions) {
            try {
                session.sendMessage(textMessage);
            } catch (IOException ex) {
            }
        }
    }

    public void addSession(org.springframework.web.socket.WebSocketSession session) {
        sessions.add(session);
    }

    public void removeSession(org.springframework.web.socket.WebSocketSession session) {
        sessions.remove(session);
    }
}
