package com.rf.aromanote.domain.sample;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter//필요시 setter 생성, 디폴트=getter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "")
public class Sample implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)//pk선언컬럼
    @Id
    private String id;

    @Column
    private String link;

    @Column
    private String type;

    @Column
    private String imageUrlStandard;

    @Column
    private String captionText;

    @Column
    private String captionCreatedTime;

    @Column
    private Long likesCount;

    @Column
    private Long commentsCount;

    @Builder
    public Sample(String id,String link,String type,String imageUrlStandard,String captionText,String captionCreatedTime,Long likesCount,Long commentsCount){
        this.id = id;
        this.link = link;
        this.type = type;
        this.imageUrlStandard = imageUrlStandard;
        this.captionText = captionText;
        this.captionCreatedTime = captionCreatedTime;
        this.likesCount = likesCount;
        this.commentsCount = commentsCount;

    }
}
