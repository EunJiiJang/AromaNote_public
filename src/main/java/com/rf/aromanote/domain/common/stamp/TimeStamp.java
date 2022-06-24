package com.rf.aromanote.domain.common.stamp;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class TimeStamp {
    //작성일자
    @CreatedDate
    private LocalDateTime regDate;

    //수정일자
    @CreatedDate
    private LocalDateTime updDate;
}
