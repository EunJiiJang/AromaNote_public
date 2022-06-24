package com.rf.aromanote.service.sample;

import com.rf.aromanote.repository.sample.SampleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class SampleService {
    private final SampleRepository sampleRepository;

    
}
