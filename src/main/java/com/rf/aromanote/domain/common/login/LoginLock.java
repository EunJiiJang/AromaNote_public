package com.rf.aromanote.domain.common.login;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "ANCTLG01MT")
public class LoginLock {
    @Id
    @Column
    private int failSeq;
    private int failCount;
    private String failDate;
    private String loginLockFl;
    private String loginUnLockDate;
    private String finalLoginDate;
    private int cstmSeq;
}
