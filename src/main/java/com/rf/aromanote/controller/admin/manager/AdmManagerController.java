package com.rf.aromanote.controller.admin.manager;

import com.rf.aromanote.domain.member.Member;
import com.rf.aromanote.dto.admin.manager.ManagerRequestDto;
import com.rf.aromanote.dto.admin.manager.ManagerSignupRequestDto;
import com.rf.aromanote.dto.admin.manager.ManagerResponseDto;
import com.rf.aromanote.dto.core.response.ResponseDto;
import com.rf.aromanote.service.admin.manager.ManagerService;
import com.rf.aromanote.service.core.login.LoginService;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/manager")
public class AdmManagerController {
    private final ManagerService managerService;
    private final LoginService loginService;

    /**
     *
     * <PRE>
     * 설명 : 운영자 목록 페이지
     * <PRE>
     * @author JangEunJi
     * @since 2022. 05. 25
     */
    @GetMapping(value = "/list")
    public String managerListPage() throws Exception {

        return "/admin/manager/managerList";
    }

    /**
     *
     * <PRE>
     * 설명 : 운영자 목록 페이지
     * <PRE>
     * @author JangEunJi
     * @since 2022. 05. 25
     */
    @PostMapping(value = "/list")
    public ResponseEntity<Object> managerList(Pageable pageable,@RequestParam String option,@RequestParam String keyword) throws Exception {
        Page<ManagerResponseDto> managerResponseDtos = managerService.getManagerList(pageable,option,keyword);
        return new ResponseEntity<>(
                new ResponseDto("관리자 리스트 조회 성공", managerResponseDtos),
                HttpStatus.OK
        );
    }

    /**
     *
     * <PRE>
     * 설명 : 운영자 등록 페이지
     * <PRE>
     * @author JangEunJi
     * @since 2022. 05. 25
     */
    @GetMapping(value = "/insert")
    public String managerInsertPage() throws Exception {

        return "/admin/manager/managerInsert";
    }

    /**
     *
     * <PRE>
     * 설명 : 운영자 회원가입
     * <PRE>
     * @author JangEunJi
     * @since 2022. 05. 25
     */
    @PostMapping(value = "/insert")
    public ResponseEntity<Object> managerInsert(@RequestBody ManagerSignupRequestDto managerSignupRequestDto) throws Exception {
        managerService.saveManagerSingUp(managerSignupRequestDto);
        loginService.saveLoginLockInfo(managerSignupRequestDto.getEmail());
        return new ResponseEntity<>(
                new ResponseDto("회원가입이 되었습니다.", ""),
                HttpStatus.CREATED
        );
    }

    /**
     *
     * <PRE>
     * 설명 : 운영자 상세 조회
     * <PRE>
     * @author JangEunJi
     * @since 2022. 06. 01
     */
    @GetMapping(value = "/detail/{cstmSeq}")
    public ResponseEntity<Object> managerDetail(@PathVariable int cstmSeq) throws Exception {
        Member member = managerService.getManagerInfo(cstmSeq);
        ManagerResponseDto managerResponseDto = new ManagerResponseDto(member);
        return new ResponseEntity<>(
                new ResponseDto("운영자 상세 정보 조회 성공", managerResponseDto),
                HttpStatus.CREATED
        );
    }

    /**
     *
     * <PRE>
     * 설명 : 운영자 상세 페이지 수정
     * <PRE>
     * @author JangEunJi
     * @since 2022. 06. 04
     */
    @PostMapping(value = "/update/{cstmSeq}")
    public ResponseEntity<Object> managerUpdate(@PathVariable int cstmSeq, @RequestBody ManagerRequestDto managerRequestDto) throws Exception {
        managerService.updateManagerInfo(cstmSeq,managerRequestDto);
        return new ResponseEntity<>(
                new ResponseDto("성공적으로 수정되었습니다.", ""),
                HttpStatus.OK
        );
    }

     /**
     *
     * <PRE>
     * 설명 : 운영자 계정 삭제
     * <PRE>
     * @author JangEunJi
     * @since 2022. 06. 04
     */
    @PostMapping(value = "/delete/{cstmSeq}")
    public ResponseEntity<Object> managerDelete(@PathVariable int cstmSeq) throws Exception {
        managerService.deleteManager(cstmSeq);
        return new ResponseEntity<>(
                new ResponseDto("성공적으로 삭제되었습니다.", ""),
                HttpStatus.OK
        );
    }

     /**
     *
     * <PRE>
     * 설명 : 운영자 계정 삭제
     * <PRE>
     * @author JangEunJi
     * @since 2022. 06. 04
     */
    @PostMapping(value = "/delete/list")
    public ResponseEntity<Object> managerListDelete(@RequestParam(value="checkedManagerList[]") List<Integer> checkedManagerList) throws Exception {
        managerService.deleteManagerList(checkedManagerList);
        return new ResponseEntity<>(
                new ResponseDto("성공적으로 삭제되었습니다.", ""),
                HttpStatus.OK
        );
    }
}
