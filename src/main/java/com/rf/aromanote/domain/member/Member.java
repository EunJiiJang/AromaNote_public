package com.rf.aromanote.domain.member;

import com.rf.aromanote.dto.admin.manager.ManagerRequestDto;
import com.rf.aromanote.dto.admin.manager.ManagerSignupRequestDto;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.*;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Data
@Table(name = "ANMB01MT")
public class Member {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int cstmSeq;


    private String email;

    @Column
    private String cstmPw;

    @Column
    private String cstmNm;

    @Column
    private String nickNm;

    @Column
    private String gender;

    @Column
    private String birth;

    @Column
    private int age;

    @Column
    private int phone;

    @Column
    private String serviceAgreementFl;

    @Column
    private String useAgreementFl;

    @Column
    private String consignmentAgreementFl;

    @Column
    private String userRole;

    @Column
    private String regDate;

    public  Member(ManagerSignupRequestDto admSignupRequestDto){
        this.email = admSignupRequestDto.getEmail();
        this.cstmPw = admSignupRequestDto.getCstmPw();
        this.cstmNm = admSignupRequestDto.getCstmNm();
        this.nickNm = admSignupRequestDto.getNickNm();
        this.gender = admSignupRequestDto.getGender();
        this.birth = admSignupRequestDto.getBirth();
        this.age = admSignupRequestDto.getAge();
        this.phone = admSignupRequestDto.getPhone();
        this.userRole = admSignupRequestDto.getUserRole();

    }
    @Builder
    public Member(int cstmSeq,String cstmPw,String cstmNm,String nickNm,String gender,String birth,int age,int phone,String email,String serviceAgreementFl,String useAgreementFl,String consignmentAgreementFl,String userRole){
        this.cstmSeq = cstmSeq;
        this.cstmPw = cstmPw;
        this.cstmNm = cstmNm;
        this.nickNm = nickNm;
        this.gender = gender;
        this.birth = birth;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.serviceAgreementFl = serviceAgreementFl;
        this.useAgreementFl = useAgreementFl;
        this.consignmentAgreementFl = consignmentAgreementFl;
        this.userRole = userRole;
    }

    public Member(ManagerRequestDto managerRequestDto) {
        this.email = email;
        this.cstmPw = cstmPw;
        this.cstmNm = cstmNm;
        this.nickNm = nickNm;
        this.gender = gender;
        this.birth = birth;
        this.age = age;
        this.phone = phone;
    }

    public List<String> getRoleList(){
        if(this.userRole.length() > 0){
            return Arrays.asList(this.userRole.split(","));
        }
        return new ArrayList<>();
    }


}
