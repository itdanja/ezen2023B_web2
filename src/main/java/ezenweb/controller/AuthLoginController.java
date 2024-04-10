package ezenweb.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthLoginController implements AuthenticationSuccessHandler,
        AuthenticationFailureHandler {
    @Override // 로그인 성공했을때
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;utf-8");
        response.getWriter().print(true);
    }
    @Override // 로그인 실패했을때
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // 매개변수 : exception : 로그인 실패한 사유( 예외클래스 객체)
        System.out.println( "exception : " + exception.getMessage());
        response.setContentType("application/json;utf-8");
        response.getWriter().print(false);

    }

}