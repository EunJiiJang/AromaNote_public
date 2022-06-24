package com.rf.aromanote.service.member;

import com.rf.aromanote.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceimpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public boolean emailDupcheck(String email){
        return memberRepository.existsByEmail(email);
    }

    @Override
    public boolean nickNmDupcheck(String nickNm){
        return memberRepository.existsByNickNm(nickNm);
    }


}
