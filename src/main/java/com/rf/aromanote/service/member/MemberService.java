package com.rf.aromanote.service.member;

import com.rf.aromanote.repository.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface MemberService {

    /**
     * <PRE>
     * 설명 : 회원가입시  이메일 중복체크
     * <PRE>
     * @author JangEunJi
     * @since 2022. 5. 26
     */
    boolean emailDupcheck(String email)throws Exception;

    /**
     * <PRE>
     * 설명 : 회원가입시  닉네임 중복체크
     * <PRE>
     * @author JangEunJi
     * @since 2022. 5. 26
     */
    boolean nickNmDupcheck(String nickNm)throws Exception;



}
