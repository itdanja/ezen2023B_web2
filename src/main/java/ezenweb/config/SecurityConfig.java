package ezenweb.config;

import ezenweb.controller.로그인실패컨트롤;
import ezenweb.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.ResponseBody;

@Configuration
public class SecurityConfig { // 시큐리티를 커스텀 하는 곳

    @Autowired  MemberService memberService;
    @Autowired
    로그인실패컨트롤 로그인실패컨트롤;

    // 1.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http ) throws Exception{

        // 1. HTTP 요청에 따른 부여된 권한/상태 확인후 PATH/자원 허가 제한
        http.authorizeHttpRequests(
            HTTP허가관련매개변수// 매개변수
                    ->              // 람다식( 자바 화살표 함수 )
            HTTP허가관련매개변수
                    .requestMatchers( AntPathRequestMatcher.antMatcher("/board") ).authenticated() //  get HTTP : /board : 인증(로그인)된 회원이면 허가
                    .requestMatchers( AntPathRequestMatcher.antMatcher("/board/write") ).hasAnyRole("USER")  //  get HTTP : /board/write : 인증(로그인)된 회원이면서 ROLE이 USER 이면 허가
                    .requestMatchers( AntPathRequestMatcher.antMatcher("/chat") ).hasAnyRole("TEAM1","TEAM2") //  get HTTP : /chat : 인증(로그인)되고 ROLE이 TEAM1이거나 TEAM2 이면 허가
                    .requestMatchers( AntPathRequestMatcher.antMatcher("/**") ).permitAll()  //  get HTTP : 그외 PATH(/**)는 모두 허가
        );

        // 2. 로그인 폼 커스텀 ( 기존 controller 매핑함수 주석/삭제 처리 )    // http.formLogin( AbstractHttpConfigurer :: disable ); // 시큐리티가 제공하는 로그임 폼을 사용안함.
        http.formLogin( // AXIOS,AJAX 사용시 contentType : form
            로그인관련매개변수
                    ->
            로그인관련매개변수
                    .loginPage("/member/login")                        // 로그인을 할 view url 정의
                    .loginProcessingUrl("/member/login/post.do")      // 로그인을 처리할 url 정의 -----> controller[X] ----> loadUserByUsername
                    .usernameParameter("memail")                      // 로그인에 사용할 id 변수명      -----> loadUserByUsername( 매개변수 )
                    .passwordParameter("mpassword")                   // 로그인에 사용할 password 변수명 ----> X
                    //.defaultSuccessUrl("/")                         // 로그인 성공하면 반환될 url
                    //.failureForwardUrl("/member/login")             // 로그인 실패하면 반환될 url
                    // .successHandler( ( request:http요청객체, response:http응답객체, authentication:성공유저인증정보객체) -> { })
                    .successHandler( (request, response, authentication) -> {
                        System.out.println("authentication = " + authentication);
                        response.setContentType("application/json;utf-8");
                        response.getWriter().print("true");     // @ResponseBody 역할
                    })
                    // .failureHandler( (request:http요청객체, response:http응답객체, exception:실패정보객체) -> {} )
                    .failureHandler( (request, response, exception) -> {
                        System.out.println("exception = " + exception.getMessage() ); // 실패 예외 이유
                        response.setContentType("application/json;utf-8");
                        response.getWriter().print("false");
                    })
        );
        // 3. 로그아웃 커스텀 ( 기존 controller 매핑함수 주석/삭제 처리 )
        http.logout(
                로그아웃관련매개변수 ->
                로그아웃관련매개변수
                        .logoutRequestMatcher(  new AntPathRequestMatcher("/member/logout/get.do") ) // 로그아웃 처리 요청 url
                        .logoutSuccessUrl("/")  // 로그아웃 성공시 이동할 url
                        .invalidateHttpSession(true) // 세션 초기화
                );
        // 4. csrf( post,put 요청 금지 ) 공격 방지 : 특정(인증/허가) url만 post,put 가능하도록
        http.csrf( AbstractHttpConfigurer :: disable ); // csrf 사용안함. // 개발작업시

        // 5. 로그인 처리에 필요한 서비스를 등록
        http.userDetailsService( memberService );

        // 6. oauth2( 소셜 로그인 )
        http.oauth2Login( oAuth2LoginConfigurer -> {
            oAuth2LoginConfigurer
                    .loginPage("/member/login") // oauth2 로그인을 할 view url 정의
                    .userInfoEndpoint( userInfoEndpointConfig ->
                            userInfoEndpointConfig.userService(memberService));
        } );
                    // Endpoint : 종착점
                    // 세션 : 1. (톰캣)http서블릿세션 2, (JS)Session 3. (WS)WebSocketSession

        // 7. 예외(오류)페이지 404 핸들러
        http.exceptionHandling((exceptionConfig) ->
                exceptionConfig
                        .accessDeniedHandler(
                                (request, response, accessDeniedException) -> {
                                    response.sendRedirect("/error403");
                                }
                        ) );

        return http.build();
    } // m end
    // 2. 시큐리티가 패스워드 검증할때 사용할 암호화 객체
    @Bean // 해당 메소드를 스프링 컨테이너 등록
    public PasswordEncoder encoder(){  return new BCryptPasswordEncoder(); }

}












