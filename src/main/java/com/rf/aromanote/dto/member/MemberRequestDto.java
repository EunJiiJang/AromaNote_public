package com.rf.aromanote.dto.member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberRequestDto {

    @NotNull
    private String email;

    @NotNull
    private String cstmPw;

    @NotNull
    private String cstmNm;

    @NotNull
    private String nickNm;

    @NotNull
    private String gender;

    @NotNull
    private String birth;

    @NotNull
    private int age;

    @NotNull
    private int phone;

    private String serviceAgreementFl;

    private String useAgreementFl;

    private String consignmentAgreementFl;

    @NotNull
    private String userRole;
}
