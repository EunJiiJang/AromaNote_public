package com.rf.aromanote.config.securoty.handlers;

import com.rf.aromanote.common.ApiResponse;
import com.rf.aromanote.common.ApiResponseType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        ApiResponse.error(response, ApiResponseType.UNAUTHORIZED_RESPONSE);
    }

}
