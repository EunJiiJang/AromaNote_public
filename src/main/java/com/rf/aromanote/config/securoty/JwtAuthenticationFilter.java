package com.rf.aromanote.config.securoty;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rf.aromanote.dto.core.login.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    // Authentication 객체 만들어서 리턴 => 의존 : AuthenticationManager
    // 인증 요청시에 실행되는 함수 => /login
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        final UsernamePasswordAuthenticationToken authRequest;
        final LoginResponseDto loginRequestDto = new LoginResponseDto();

        log.debug("JwtAuthenticationFilter : 진입");
        // request에 있는 username과 password를 파싱해서 자바 Object로 받기
        ObjectMapper om = new ObjectMapper();
        loginRequestDto.setUsername(request.getParameter("username"));
        loginRequestDto.setPassword(request.getParameter("password"));
        log.debug("JwtAuthenticationFilter : 진입1번"+request.getParameter("username"));
//            loginRequestDto = om.readValue(request.getInputStream(), LoginDto.class);
//            System.out.println("loginDto : " + loginRequestDto);
        authRequest = new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),loginRequestDto.getPassword());
        log.debug("JwtAuthenticationFilter : 토큰생성완료");
        setDetails(request,authRequest);
        log.debug("여기 도착1");
        return this.getAuthenticationManager().authenticate(authRequest);
    }

}
