package com.rf.aromanote.dto.admin.manager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ManagerSignupRequestDto {
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

    @NotNull
    private String userRole;
}
