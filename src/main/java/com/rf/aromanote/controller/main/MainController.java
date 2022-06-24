package com.rf.aromanote.controller.main;

import com.rf.aromanote.domain.common.login.LoginLog;
import com.rf.aromanote.repository.core.login.LoginLogRepository;
import io.swagger.models.Model;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
public class MainController {
    private final LoginLogRepository loginLogRepository;

    public MainController(LoginLogRepository loginLogRepository) {
        this.loginLogRepository = loginLogRepository;
    }

    @GetMapping(value = {"main","/"})
    public String home(Model model){
        log.info("도착 home controller");
        return "main/main";
    }
    @GetMapping("/member/test")
    public String dispUser(Model model){
        log.info("home controller");
        return "/member/member";
    }

    @GetMapping("/admin/test")
    public String dispAdmin(Model model){
        return "/admin/main/adminMain";
    }

    @GetMapping("/testA")
    public String test(Model model){
        LoginLog loginLog = new LoginLog();
        loginLog.setCstmSeq(1);
        loginLog.setIp("1234");
        loginLogRepository.save(loginLog);
        return "admin/popUp/admin_popup";
        
    }




}
