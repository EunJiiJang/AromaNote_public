package com.rf.aromanote.repository.popup;

import javax.persistence.EntityManager;

import com.rf.aromanote.dto.popup.req.popupInsertReq;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PopupRepository {
    private final EntityManager em;

    public void popupInsert(popupInsertReq req) {
        
    }


}
