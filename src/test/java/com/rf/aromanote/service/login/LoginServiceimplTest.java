package com.rf.aromanote.service.login;

import com.rf.aromanote.domain.common.login.Login;
import com.rf.aromanote.domain.common.login.LoginLock;
import com.rf.aromanote.domain.member.Member;
import com.rf.aromanote.repository.core.login.LoginLockRepository;
import com.rf.aromanote.repository.core.login.LoginRepository;
import com.rf.aromanote.service.core.login.LoginServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class LoginServiceimplTest {
    @AfterEach
    public void cleanUp(){
        loginLockRepository.deleteAll();
    }
    @Autowired
    private LoginLockRepository loginLockRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private LoginServiceImpl loginService;

    @Test
    void 최초_회원가입후_로그인잠금_기본정보삽입(){
        //given
        Login login = new Login();
        LoginLock loginLock = new LoginLock();

        String username = "testId";
        login = loginRepository.findByUsername(username);

        int cstmSeq = login.getId();
        loginLock.setCstmSeq(cstmSeq);

        //when
        loginLockRepository.save(loginLock);
        //then
        assertEquals(loginLockRepository.findByCstmSeq(cstmSeq) != null,true);
    }

}
