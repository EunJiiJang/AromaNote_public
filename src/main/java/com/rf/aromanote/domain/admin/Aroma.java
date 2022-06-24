package com.rf.aromanote.domain.admin;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "ARMR01MT")
public class Aroma implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk선언컬럼
    @Id
    private int mrSeq;

    @Column
    private String mrCd;

    @Column
    private String mrNm;

    @Column
    private String mrNmEn;

    @Column
    private String mrCon;

    @Column
    private String hashTag;

    @Column
    private String fileNm;

    @Column
    private String filePath;

    @Column
    private int sortOrderBy;

    @Column
    private String useFl;

    @Builder
    public Aroma(int mrSeq, String mrCd, String mrNm, String mrNmEn, String mrCon, String hashTag) {
        this.mrSeq = mrSeq;
        this.mrCd = mrCd;
        this.mrNm = mrNm;
        this.mrNmEn = mrNmEn;
        this.mrCon = mrCon;
        this.hashTag = hashTag;
    }
}
