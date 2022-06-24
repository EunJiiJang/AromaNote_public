package com.rf.aromanote.repository.core.login;
import com.rf.aromanote.domain.common.login.Login;
import com.rf.aromanote.domain.common.login.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LoginLogRepository extends JpaRepository<LoginLog,Long> {


}
