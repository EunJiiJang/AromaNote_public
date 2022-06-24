package com.rf.aromanote.controller.admin;

import javax.servlet.http.HttpServletRequest;


import com.rf.aromanote.dto.popup.req.popupInsertReq;
import com.rf.aromanote.service.popup.popupService;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 샘플컨트롤러 ex)인스타 테이블에 crud를 통한 구조학습
@Slf4j  //로깅
@RequiredArgsConstructor    //롬복의존성
@Controller
@RequestMapping("/popup")
public class popupController {


    private final popupService popupService;

    /**
     *
     * <PRE>
     * 설명 : 팝업 리스트
     * <PRE>
     * @author gahee
     * @since 2022. 04. 11
     */

    @GetMapping("/list")
    public String popupList(Model model, Authentication auth, HttpServletRequest request){
        model.addAttribute("data", "이거이비당");
        return "/admin/popUp/list";
    }

    /**
     *
     * <PRE>
     * 설명 : 팝업 인서트
     * <PRE>
     * @author gahee
     * @since 2022. 05. 18
     */

    @GetMapping("/insert")
    public String popupInsertView(Authentication auth, HttpServletRequest request){
        return "/admin/popUp/insert";
    }

    @PostMapping("/insert")
    public String popupInsert(popupInsertReq req,Authentication auth, RedirectAttributes rttr){
        req.setRegId(1);
        String msg = popupService.popupInsert(req) > 0 ? "등록 성공" : "등록 실패";
        rttr.addFlashAttribute("msg", msg);

        return "";
    }

    /**
     *
     * <PRE>
     * 설명 : 팝업 디테일
     * <PRE>
     * @author gahee
     * @since 2022. 05. 18
     */

    @GetMapping("/detail")
    public String popupDetail(Model model, Authentication auth, HttpServletRequest request){
        model.addAttribute("data", "이거이비당");
        return "/admin/popUp/detail";
    }
}
