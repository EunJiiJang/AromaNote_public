package com.rf.aromanote.config.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwt = resolveToken(request);
        if (StringUtils.hasText(jwt) && jwtProvider.isValidToken(jwt)) {
            // 토큰으로 인증 정보를 추출
            Authentication authentication = jwtProvider.getAuthentication(jwt);
            // SecurityContext에 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {

        }
        chain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) throws UnsupportedEncodingException {
        //String bearerToken = request.getHeader(JwtProperties.HEADER_STRING);
        Cookie[] cookies = request.getCookies();
        String bearerToken = "";
        if (cookies != null){
            for (int i = 0; i < cookies.length; i++) {
                if (HttpHeaders.AUTHORIZATION.equals(cookies[i].getName())) {
                    bearerToken = cookies[i].getValue();
                    bearerToken = URLDecoder.decode(bearerToken,"UTF-8");
                    break;
                }
            }
        }
        System.out.println("jwt 헤더체크: " + bearerToken);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(JwtProperties.BEARER_TYPE)) {
            bearerToken= bearerToken.replace(JwtProperties.BEARER_TYPE,"");
            return bearerToken;
        }
        return null;
    }

}
