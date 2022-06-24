package com.rf.aromanote.config.jwt;

import org.springframework.beans.factory.annotation.Value;

public interface JwtProperties {
    String HEADER_STRING = "Authorization";
    String BEARER_TYPE = "Bearer ";
    long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30; //30분
    long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24;//7일


}
