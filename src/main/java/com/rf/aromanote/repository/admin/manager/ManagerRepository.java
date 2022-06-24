package com.rf.aromanote.repository.admin.manager;

import com.rf.aromanote.domain.member.Member;
import com.rf.aromanote.dto.admin.manager.ManagerResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Member,Long>, JpaSpecificationExecutor<Member> {
    Page<Member> findAllByUserRole(String userRole, Pageable pageable);

    @Override
    Page<Member> findAll( Specification<Member> spec,Pageable pageable);

    Member findByCstmSeq(int cstmSeq);
    
    @Transactional
    void deleteByCstmSeq(int cstmSeq);
    
    

}
