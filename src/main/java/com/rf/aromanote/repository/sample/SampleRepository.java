package com.rf.aromanote.repository.sample;

import com.rf.aromanote.domain.sample.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<Sample, Long> {

}
