package com.rf.aromanote.repository.core.login;
import com.rf.aromanote.domain.common.login.Login;
import org.springframework.data.jpa.repository.JpaRepository;



public interface LoginRepository extends JpaRepository<Login,Long> {
    Login findByUsername(String username);

}
