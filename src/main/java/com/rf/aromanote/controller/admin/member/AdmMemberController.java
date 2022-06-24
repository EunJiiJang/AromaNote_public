package com.rf.aromanote.controller.admin.member;

import com.rf.aromanote.dto.core.response.ResponseDto;
import com.rf.aromanote.exception.BadRequestException;
import com.rf.aromanote.exception.ErrorCode;
import com.rf.aromanote.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
//@RequestMapping("/member")
@RequestMapping("/membertest")
public class AdmMemberController {

    private final MemberService memberService;
    /**
     *
     * <PRE>
     * 설명 : 운영자/멤버 email 중복체크
     * <PRE>
     * @author JangEunJi
     * @since 2022. 05. 26
     */
    @GetMapping(value = "/emailDupcheck")
    public ResponseEntity<Object>  emailDupcheck(@RequestParam String email) throws Exception {
        String isDupl = "unique";
        if(memberService.emailDupcheck(email)){
            isDupl = "dupl";
        }
        return new ResponseEntity<>(
                new ResponseDto("사용 가능한 메일입니다.", isDupl),
                HttpStatus.OK
        );
    }

    /**
     *
     * <PRE>
     * 설명 : 운영자/멤버 닉네임 중복체크
     * <PRE>
     * @author JangEunJi
     * @since 2022. 05. 26
     */
    @GetMapping(value = "/nickNmDupcheck")
    public ResponseEntity<Object> nickNmDupcheck(@RequestParam String nickNm) throws Exception {

        if (memberService.nickNmDupcheck(nickNm)) {
            throw new BadRequestException(ErrorCode.ALREADY_NICKNAME_ERROR);
        }
        return new ResponseEntity<>(
                new ResponseDto("사용가능한 닉네임입니다.", ""),
                HttpStatus.OK
        );
    }
}
