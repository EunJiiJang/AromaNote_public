package com.rf.aromanote.service.core.login;

import com.rf.aromanote.domain.common.login.LoginLock;

public interface LoginService {
    /**
     * <PRE>
     * 설명 : 회원가입시 로그인 잠금 디폴트 정보 삽입
     * <PRE>
     * @author JangEunJi
     * @since 2022. 5. 21
     */
    void saveLoginLockInfo(String email);
}
