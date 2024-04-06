package ezenweb.config;

import ezenweb.controller.ChattingController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.config.annotation.*;


@Configuration // 스프링 컨테이너에 빈 등록
@EnableWebSocket // 웹소켓 연결
public class WebSocketConfig implements WebSocketConfigurer {
    @Autowired private ChattingController chattingController;
    @Override // 여기에 사용할 서버소켓들의 매핑주소와 접근제한 설정
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler( chattingController , "/chat" );
    }

}