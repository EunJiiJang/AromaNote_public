package com.rf.aromanote.repository.member;

import com.rf.aromanote.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,String> {
    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByNickNm(String nickNm);
}
