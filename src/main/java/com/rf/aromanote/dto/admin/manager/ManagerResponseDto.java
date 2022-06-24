package com.rf.aromanote.dto.admin.manager;

import com.rf.aromanote.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ManagerResponseDto {

    private int cstmSeq;

    private String email;

    private String cstmPw;
    
    private String cstmNm;
    
    private String nickNm;
    
    private String gender;
    
    private String birth;
    
    private int age;
    
    private int phone;

    private String regDate;

    public ManagerResponseDto(Member member){
        this.cstmSeq = member.getCstmSeq();
        this.email = member.getEmail();
        this.cstmPw = member.getCstmPw();
        this.cstmNm = member.getCstmNm();
        this.nickNm = member.getNickNm();
        this.gender = member.getGender();
        this.birth = member.getBirth();
        this.age = member.getAge();
        this.phone = member.getPhone();
        this.regDate = member.getRegDate();
    }
}
