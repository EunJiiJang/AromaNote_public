package com.rf.aromanote.service.manager;

import com.rf.aromanote.domain.common.login.Login;
import com.rf.aromanote.domain.common.login.LoginLock;
import com.rf.aromanote.domain.member.Member;
import com.rf.aromanote.dto.admin.manager.ManagerRequestDto;
import com.rf.aromanote.dto.admin.manager.ManagerSignupRequestDto;
import com.rf.aromanote.repository.member.MemberRepository;
import com.rf.aromanote.service.admin.manager.ManagerServiceimpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ManagerServiceimplTest {
//    @AfterEach
//    public void cleanUp(){
//        memberRepository.deleteAll();
//    }
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ManagerServiceimpl managerServiceimpl;

    String email = "tester1@naver.com";
    String cstmPw = "tester11";
    String cstmNm = "tester1";
    String nickNm = "tester1";
    String gender = "F";
    String birth  = "2022-04-16";
    int age = 25;
    int phone = 01000000000;

    Member member;

    @Test
    void 관리자계정_회원가입() throws Exception {
        //given
        ManagerSignupRequestDto managerSignupRequestDto = new ManagerSignupRequestDto(email,cstmPw,cstmNm,nickNm,gender,birth,age,phone,null);
        //when
        managerServiceimpl.saveManagerSingUp(managerSignupRequestDto);
        //then
    }

    @Test
    void 관리자계정_수정() throws Exception {
        //given
        int cstmSeq = 2;
        ManagerRequestDto managerRequestDto = new ManagerRequestDto("update1@naver.com",cstmPw,cstmNm,nickNm,gender,birth,age,phone);
        //when
        managerServiceimpl.updateManagerInfo(cstmSeq,managerRequestDto);
        //then
    }
}
