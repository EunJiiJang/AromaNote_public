package com.rf.aromanote.repository.sample;


import com.rf.aromanote.domain.sample.Sample;
import com.rf.aromanote.repository.sample.SampleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")//테스트용 프로퍼티 사용을 위해 필요하다
@DataJpaTest
public class SampleRepositoryTest {
    @Autowired
    SampleRepository sampleRepository;

    @AfterEach
    public void cleanUp(){
        sampleRepository.deleteAll();
    }
    @Test
    public void 게시글저장후_불러오기(){
        //given
        sampleRepository.save(Sample.builder()
                        .id("test")
                        .link("test.com")
                        .type("img")
                        .imageUrlStandard("testimg.url")
                        .captionText("인스타")
                        .captionCreatedTime("test타임")
                        .likesCount(100L)
                        .commentsCount(14L)
                        .build());
        //when
        List<Sample>sampleList = sampleRepository.findAll();

        //then
        Sample sample = sampleList.get(0);
        assertThat(sample.getId()).isEqualTo("1");



    }
}
