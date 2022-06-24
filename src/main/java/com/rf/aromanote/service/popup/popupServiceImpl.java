package com.rf.aromanote.service.popup;

import com.rf.aromanote.dto.popup.req.popupInsertReq;
import com.rf.aromanote.repository.popup.PopupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class popupServiceImpl implements popupService{

    @Autowired
    private PopupRepository popupRepository;

    @Override
    public int popupInsert(popupInsertReq req) {
        popupRepository.popupInsert(req);
        
        return 0;
    }

   
    
}
