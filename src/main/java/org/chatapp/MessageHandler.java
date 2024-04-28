package org.chatapp;

import lombok.NonNull;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

public class MessageHandler extends TextWebSocketHandler {
    @Override
    public void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage message)
            throws IOException {
        System.out.println("Message received: " + message.getPayload());
        session.sendMessage(message);
    }

}
