package com.rf.aromanote.service.admin.manager;

import com.rf.aromanote.domain.admin.manager.ManagerSpecification;
import com.rf.aromanote.domain.member.Member;
import com.rf.aromanote.dto.admin.manager.ManagerRequestDto;
import com.rf.aromanote.dto.admin.manager.ManagerSignupRequestDto;
import com.rf.aromanote.dto.admin.manager.ManagerResponseDto;
import com.rf.aromanote.repository.admin.manager.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ManagerServiceimpl implements ManagerService{
    private final ManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveManagerSingUp(ManagerSignupRequestDto managerSignupRequestDto) throws Exception {
        Member member = new Member(managerSignupRequestDto);
        member.setUserRole("ROLE_ADMIN");
        member.setCstmPw(passwordEncoder.encode(managerSignupRequestDto.getCstmPw()));
        managerRepository.save(member);

    }

    @Override
    public Page<ManagerResponseDto> getManagerList(Pageable pageable,String option,String keyword) throws Exception {
        Specification<Member> spec = null;
        if (keyword != null && option != null){
            if ("cstmNm".equals(option)){
                spec = ManagerSpecification.likeCstmNm(keyword);
            }else if("email".equals(option)){
                spec = ManagerSpecification.likeEmail(keyword);
            }else if("nickNm".equals(option)){
                spec = ManagerSpecification.likeNickNm(keyword);
            }
        }


        if(spec == null){
            Page<Member> managerLsit = managerRepository.findAllByUserRole("ROLE_ADMIN",pageable);
            return managerLsit.map(ManagerResponseDto::new);
        }else {
            spec = spec.and(ManagerSpecification.likeUserRole("ROLE_ADMIN"));
            Page<Member> managerLsit = managerRepository.findAll(spec,pageable);
            return managerLsit.map(ManagerResponseDto::new);
        }

    }

    @Override
    public Member getManagerInfo(int cstmSeq) {
        return managerRepository.findByCstmSeq(cstmSeq);
    }

    @Override
    public void updateManagerInfo(int cstmSeq, ManagerRequestDto managerRequestDto) {
        Member member = managerRepository.findByCstmSeq(cstmSeq);
        member.setEmail(managerRequestDto.getEmail());
        member.setCstmPw(passwordEncoder.encode(managerRequestDto.getCstmPw()));
        member.setCstmNm(managerRequestDto.getCstmNm());
        member.setNickNm(managerRequestDto.getNickNm());
        member.setGender(managerRequestDto.getGender());
        member.setBirth(managerRequestDto.getBirth());
        member.setAge(managerRequestDto.getAge());
        member.setPhone(managerRequestDto.getPhone());
        managerRepository.save(member);

    }

    @Override
    public void deleteManager(int cstmSeq) {
        managerRepository.deleteByCstmSeq(cstmSeq);
    
    }

    @Override
    public void deleteManagerList(List<Integer> checkedManagerList) {
        for(int cstmSeq :checkedManagerList){
            managerRepository.deleteByCstmSeq(cstmSeq);
        }
    }

}
