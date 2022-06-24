package com.rf.aromanote.controller.core.login;

import com.rf.aromanote.config.jwt.JwtProperties;
import com.rf.aromanote.config.securoty.PrincipalDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private final String DEFAULT_LOGIN_SUCCESS_URL = "/main";

    /**
     *
     * <PRE>
     * 설명 : 관리자 로그인페이지
     * <PRE>
     * @author JangEunJi
     * @since 2022. 04. 29
     */
    @GetMapping(value = "/adminLogin")
    public ModelAndView adminLoginForm(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/login/adminLogin");
        return mv;
    }


    /**
     *
     * <PRE>
     * 설명 : 유저 로그인페이지(개발중)
     * <PRE>
     * @author JangEunJi
     * @since 2022. 04. 29
     */
    @GetMapping(value = "/memberLogin")
    public ModelAndView memberLoginForm(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        String uri = request.getHeader("Referer");
        if (!uri.contains("/loginView")) {
            request.getSession().setAttribute("prevPage",
                    request.getHeader("Referer"));
        }
        mv.setViewName("admin/login/adminLogin");
        return mv;
    }

    /**
     *
     * <PRE>
     * 설명 : 로그인 이후 보내질 페이지 체크
     * <PRE>
     * @author JangEunJi
     * @since 2022. 04. 29
     */
    @RequestMapping(value = "/loginChk",method = RequestMethod.GET)
    public String loginChk(HttpServletRequest req,HttpServletResponse res,Authentication authentication) throws IOException {

        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        String role = principal.getMemberForLogin().getRoleList().get(0).toString();
        log.debug("프로세스체크");
        String url = "/main";
        if(role.equals("ROLE_ADMIN")){
            url = "/admin/main";
        }else if(role.equals("ROLE_MEMBER")){
            url = "/main";
        }
        return url;
    }

    /**
     *
     * <PRE>
     * 설명 : 관리자 로그아웃
     * <PRE>
     * @author JangEunJi
     * @since 2022. 04. 11
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.debug("로그아웃!!");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        String role = principal.getMemberForLogin().getRoleList().get(0).toString();
        if(authentication != null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }
        String url = "/main";
        if(role.equals("ROLE_ADMIN")){
            url = "/login/adminLogin";
        }else if(role.equals("ROLE_MEMBER")){
            url = "/main";
        }

        return "redirect:"+url;
    }


    //회원가입기능 개발전 로그인을 위한 패스워드 암호화
    @PostMapping("/pwd")
    public String pwdChk(@RequestBody Map<String,String> member){
        log.debug("pwdTest"+member);
        return new BCryptPasswordEncoder().encode(member.get("cstmPw"));
    }



}
