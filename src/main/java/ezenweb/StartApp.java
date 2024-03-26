package ezenweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class StartApp {
    public static void main(String[] args) {
        SpringApplication.run( StartApp.class );
    }
}
// 인텔리제이 무료버전 기준 ( 스프링 프로젝트 만드는 방법 )
// 1. 인텔리제이 그레이들 프로젝트 생성
// 2. https://start.spring.io/
// 3. 버전에 맞춰 필요한 라이브러리 가져와서 build.gradle 파일에 넣어준다.
    // 개발하면서 그때그때 필요한 외부 라이브러리들을 항상 가져와서 최신화

// 스프링 웹 실행하는 방법
// 1. 스프링 실행 클래스는 모든 MVC 클래스 파일 들 보다 상위 또는 동등한 위치에 존재
// 2. @SpringBootApplication 어노테이션 주입
// 3. SpringApplication.run( StartApp.class );