package com.rf.aromanote.repository.core.login;

import com.rf.aromanote.domain.common.login.LoginLock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginLockRepository extends JpaRepository<LoginLock,Long> {
    LoginLock findByCstmSeq(int cstmSeq);
}
