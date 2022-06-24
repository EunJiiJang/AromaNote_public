package com.rf.aromanote.domain.common.login;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
@Table(name = "ANMB01MT")
public class Login {

    @Id @Column(name = "cstmSeq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "email")
    private String username;
    @Column(name = "cstmPw")
    private String password;
    @Column(name = "userRole")
    private String roles;
    // ENUM으로 안하고 ,로 해서 구분해서 ROLE을 입력 -> 그걸 파싱!!
    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }
}
