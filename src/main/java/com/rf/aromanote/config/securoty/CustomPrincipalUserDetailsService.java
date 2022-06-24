package com.rf.aromanote.config.securoty;

import com.rf.aromanote.domain.common.login.Login;
import com.rf.aromanote.repository.core.login.LoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
// DB에 연동되게끔 하는 곳
@Service
@RequiredArgsConstructor
public class CustomPrincipalUserDetailsService implements UserDetailsService {
    private final LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.debug("CustomUserDetailsService : 진입");
        log.debug("CustomUserDetailsService csmtId :"+username);
        Login login =loginRepository.findByUsername(username);
        log.debug("CustomUserDetailsService csmtInfo :"+ login);

        return new PrincipalDetails(login);
    }
}
