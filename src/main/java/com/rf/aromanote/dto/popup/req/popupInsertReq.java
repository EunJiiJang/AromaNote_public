package com.rf.aromanote.dto.popup.req;

import lombok.Data;

@Data
public class popupInsertReq {
    private String popType;
    private String popNm;
    private String popCon;
    private int regId;
    private char dispFl;
    private int sortOrderBy;
}
