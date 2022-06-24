package com.rf.aromanote.domain.popup;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Data
@Table(name = "APOP01MT")
public class Popup {
    
    @Id @GeneratedValue
    private int popSeq;                 // 팝업번호

    @Column
    private String popType;             // 팝업종류

    @Column
    private String popNm;               // 팝업명

    @Column
    private String popCon;              // 팝업내용

    @Column
    private String popUrl;              // 팝업 URl

    @Column
    private char btnFl;                 // 비노출버튼 유무

    @Column
    private String conFilePath;         // 본문파일경로

    @Column
    private String conFileNm;           // 본문파일명

    @Column
    private int regId;                  // 등록아이디

    @Column
    private Date regDate;               // 등록일자

    @Column
    private int updId;                  // 수정아이디

    @Column
    private Date updDate;               // 수정일자

    @Column
    private String dispStatCd;          // 노출상태코드

    @Column
    private int sortOrderBy;            // 노출순서

    @Column
    private char dispFl;                // 노출여부  

}
