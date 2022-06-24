package com.rf.aromanote.config.securoty.handlers;

import com.rf.aromanote.config.jwt.JwtProperties;
import com.rf.aromanote.config.jwt.JwtProvider;
import com.rf.aromanote.domain.common.login.Login;
import com.rf.aromanote.domain.common.login.LoginLog;
import com.rf.aromanote.repository.core.login.LoginLogRepository;
import com.rf.aromanote.repository.core.login.LoginRepository;
import com.rf.aromanote.util.WebUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtProvider jwtProvider;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private RequestCache requestCache = new HttpSessionRequestCache();
    private final String DEFAULT_LOGIN_SUCCESS_URL = "/main";

    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private LoginLogRepository loginLogRepository;




    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtProvider.generateJwtToken(authentication);
        final String tokenVal = URLEncoder.encode(JwtProperties.BEARER_TYPE+token,"UTF-8");
        response.setHeader(HttpHeaders.AUTHORIZATION, tokenVal);
        Cookie cookie = new Cookie(HttpHeaders.AUTHORIZATION,tokenVal);
        //cookie.setSecure(true);https 일때만통신
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(Long.valueOf(JwtProperties.ACCESS_TOKEN_EXPIRE_TIME).intValue());
        response.addCookie(cookie);

        String username = authentication.getName();
        log.debug("username!! : "+username);
        Login Info =loginRepository.findByUsername(username);

        int cstmSeq = Info.getId();
        String role = Info.getRoles();
        String ip = WebUtil.getClientIp(request);

        LoginLog loginLog = new LoginLog();
        loginLog.setCstmSeq(cstmSeq);
        loginLog.setIp(ip);
        loginLogRepository.save(loginLog);

        if(role.equals("ROLE_ADMIN")){
            redirectStrategy.sendRedirect(request,response,"/admin/main");
            log.debug("어드민");
        }else {
            redirectStrategy.sendRedirect(request,response,"/main");
            log.debug("멤버");
        }

    }
}
