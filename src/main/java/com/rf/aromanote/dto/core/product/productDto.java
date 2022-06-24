package com.rf.aromanote.dto.core.product;

import java.sql.Date;

import lombok.Data;

@Data
public class productDto {
    private int pdtSeq; // 제품순번
    private String brandPrdCd; // 브랜드제품코드
    private String CatCd1st; // 분류코드1차
    private String CatCd2nd; // 분류코드2차

    private String prdNm; // 상품명
    private String prdEngNm; // 상품영문명
    private String PrdNms; // 상품명머리글
    private String BasicDesc; // 기본설명
    private String prdVol; // 상품용량
    private int stdPrc; // 표준가격
    private String Mnufact; // 제조원
    private String PrdElement; // 전성분설
    private String DtlDescURL; // 상세설명URL
    private String mobileDtlDescUrl;// 모바일상세설명URL
    private String dispStatCd; // 노출상태코드
    private String hashTag; // 해시태그

    private int regId; // 등록아이디
    private Date regDate; // 등록일자
    private int updId; // 수정아이디
    private Date updDate; // 수정일자
    private String prdTpCd; // 모바일상세설명URL
    private String mnuFactNation; // 제조국가
    private int sortOrderBy; // 정렬순서
    private char newPrdFl; // 신규상품여부
    private char bstPrdFl; // 베스트상품여부
}
