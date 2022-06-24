package com.rf.aromanote.service.core.login;

import com.rf.aromanote.domain.common.login.Login;
import com.rf.aromanote.domain.common.login.LoginLock;
import com.rf.aromanote.domain.member.Member;
import com.rf.aromanote.repository.core.login.LoginLockRepository;
import com.rf.aromanote.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final LoginLockRepository loginLockRepository;
    private final MemberRepository memberRepository;

    @Override
    public void saveLoginLockInfo(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(null);
        LoginLock loginLock = new LoginLock();
        loginLock.setCstmSeq(member.getCstmSeq());
        loginLockRepository.save(loginLock);
    }
}
