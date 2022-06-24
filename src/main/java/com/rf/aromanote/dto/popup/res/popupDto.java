package com.rf.aromanote.dto.popup.res;

import java.sql.Date;

import lombok.Data;

@Data
public class popupDto {
    private int popSeq;                 // 팝업번호
    private String popType;             // 팝업종류
    private String popNm;               // 팝업명
    private String popCon;              // 팝업내용
    private String popUrl;              // 팝업 URl
    private char btnFl;                 // 비노출버튼 유무
    private String conFilePath;         // 본문파일경로
    private String conFileNm;           // 본문파일명
    private int regId;                  // 등록아이디
    private Date regDate;               // 등록일자
    private int updId;                  // 수정아이디
    private Date updDate;               // 수정일자
    private String dispStatCd;          // 노출상태코드
    private int sortOrderBy;            // 노출순서
    private char dispFl;                // 노출여부
}
