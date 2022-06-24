package com.rf.aromanote.dto.admin.manager;

import com.rf.aromanote.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ManagerRequestDto {
    private String email;

    private String cstmPw;

    private String cstmNm;

    private String nickNm;

    private String gender;

    private String birth;

    private int age;

    private int phone;

}
