package appsprototyping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
@Slf4j
public class MyWebSocketHandler extends TextWebSocketHandler {
    private ConcurrentHashMap<WebSocketSession, Object> welcomePending = new ConcurrentHashMap<>();

    @Scheduled(fixedRate=10*1000)
    public void sendWelcomes() {
        Enumeration<WebSocketSession> keys = welcomePending.keys();
        ArrayList<WebSocketSession> items = new ArrayList<>(welcomePending.size());
        while(keys.hasMoreElements()) {
            items.add(keys.nextElement());
        }

        for (WebSocketSession each : items) {
            welcomePending.remove(each);
            try {
                sendWelcomeMessage(each);
            }
            catch (IOException e) {
                log.error("Failed to send welcome message", e);
            }
        }
    }

    private void sendWelcomeMessage(WebSocketSession each) throws IOException {
        if (each.isOpen()) {
            each.sendMessage(new TextMessage("Welcome!"));
        }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        welcomePending.remove(session);
        session.sendMessage(new TextMessage(message.getPayload() + " World"));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        welcomePending.put(session, String.class);
        log.info("Connection established {}", session.getRemoteAddress());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("Connection closed {}", session.getRemoteAddress());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("Transport error {}", session.getRemoteAddress());
    }
}
