package ezenweb.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;


@Component // 스프링 컨테이너에 빈 등록
public class ChattingController extends TextWebSocketHandler {
    // 0. 서버소켓과 연동된 클라이언트소켓들을 저장하는 리스트
    private static List<WebSocketSession> 접속명단 = new ArrayList<>();
    // 1. 클라이언트소켓과 연동 성공했을때. 이후 행동/메소드 정의
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // * 접속 연동 성공시 클라이언트소켓의세션정보를 접속명단 리스트에 저장

        접속명단.add( session );
        System.out.println("접속명단 = " + 접속명단 );
    }
    // 2. 클라이언소켓과 세션 오류가 발생했을때 이후 행동/메소드 정의
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("session = " + session + ", exception = " + exception);
    }
    // 3. 클라언트소켓과 연동이 끊겼을때. 이후 행동/메소드 정의
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // * 접속 연동이 끊겼을때 접속명단 에서도 제거
        접속명단.remove( session );
    }
    // 4. 클라이언트소켓으로부터 메시지를 받았을때. 이후 행동/메소드 정의
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // * 서버가 클라이언트로부터 받은 메시지를 접속명단에 있는 모든 세션들에게 전달
        for( WebSocketSession 세션 : 접속명단 ){
            세션.sendMessage( message );
        }

    }
}