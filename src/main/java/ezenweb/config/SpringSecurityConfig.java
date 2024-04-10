package ezenweb.config;

import ezenweb.controller.AuthLoginController;
import ezenweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SpringSecurityConfig {

    @Autowired
    MemberService memberService;

    @Autowired
    private AuthLoginController authLoginController;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(authorizeRequest ->
                        authorizeRequest
                            .requestMatchers(   AntPathRequestMatcher.antMatcher("/board" )     ).authenticated()
                            .requestMatchers(   AntPathRequestMatcher.antMatcher("/board/write" )     ).hasAnyRole("USER")
                            .requestMatchers(   AntPathRequestMatcher.antMatcher("/chat" )     ).hasAnyRole("TEAM1")
                            .requestMatchers(   AntPathRequestMatcher.antMatcher("/**")     ).permitAll()
                );

        http.exceptionHandling((exceptionConfig) ->
                        exceptionConfig
                                .accessDeniedHandler(
                                        (request, response, accessDeniedException) -> {
                                            response.sendRedirect("/error403");
                                        }
                                ) );

        //http.formLogin( AbstractHttpConfigurer ::disable );
        http.csrf(AbstractHttpConfigurer::disable);

        http.formLogin((formLogin) ->
                        formLogin
                                .loginPage("/member/login")
                                .usernameParameter("memail")
                                .passwordParameter("mpassword")
                                .loginProcessingUrl("/member/login/post.do")
                                //.defaultSuccessUrl("/", true)
                                .successHandler( authLoginController ) // 로그인 성공했을때 해당 클래스 매핑
                                .failureHandler( authLoginController ) // 로그인 실패했을때 해당 클래스 매핑
                );
        http.oauth2Login( (oAuth2LoginConfigurer) -> {
                oAuth2LoginConfigurer.loginPage("/member/login")
                        .userInfoEndpoint( userInfoEndpointConfig -> { userInfoEndpointConfig.userService( memberService ); });
                });

        http.logout((logout) -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout/get.do"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true));

        http.userDetailsService( memberService );
        return http.build();
    }
    @Bean
    public PasswordEncoder encoder() {   return new BCryptPasswordEncoder();  }

}