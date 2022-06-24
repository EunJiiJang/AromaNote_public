package com.rf.aromanote.controller.admin.main;

import io.swagger.models.Model;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdmMainController {

    @RequestMapping(value = "/main",method = RequestMethod.GET)
    public String adminMain(Model model, Principal principal, HttpServletRequest req, HttpServletResponse res){
        log.debug("main page 접근");
        return "admin/main/adminMain";
    }
}
