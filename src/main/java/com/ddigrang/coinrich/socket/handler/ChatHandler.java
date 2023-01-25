package com.ddigrang.coinrich.socket.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.List;

@Component
public class ChatHandler extends TextWebSocketHandler {

    private final static Logger LOG = Logger.getGlobal();

    private static List<WebSocketSession> list = new ArrayList<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("접속자 : "+list.size());
        String payload = message.getPayload();
        LOG.info("payload : " + payload);

        for(WebSocketSession sess: list) {
            sess.sendMessage(message);
        }
    }

    /* Client가 접속 시 호출되는 메서드 */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("접속자 : "+list.size());


        list.add(session);

        LOG.info(session + " 클라이언트 접속");
    }

    /* Client가 접속 해제 시 호출되는 메서드드 */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        LOG.info(session + " 클라이언트 접속 해제");
        list.remove(session);
        System.out.println("접속자 : "+list.size());
    }
}
