package com.rf.aromanote.domain.common.login;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Table(name = "ANCT01MT")
public class LoginLog {
    @Id
    @Column
    private int loginSeq;
    private String ip;
    private int cstmSeq;



}
