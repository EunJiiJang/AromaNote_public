package com.rf.aromanote.service.admin.manager;

import com.rf.aromanote.domain.member.Member;
import com.rf.aromanote.dto.admin.manager.ManagerRequestDto;
import com.rf.aromanote.dto.admin.manager.ManagerSignupRequestDto;
import com.rf.aromanote.dto.admin.manager.ManagerResponseDto;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

public interface ManagerService {
    /**
     * <PRE>
     * 설명 : 관리자 회원가입
     * <PRE>
     * @author JangEunJi
     * @since 2022. 05. 30
     */
    void saveManagerSingUp(ManagerSignupRequestDto managerSignupRequestDto)throws Exception;

    /**
     * <PRE>
     * 설명 : 관리자 리스트
     * <PRE>
     * @author JangEunJi
     * @since 2022. 05. 30
     */
    Page<ManagerResponseDto> getManagerList(Pageable pageable,String option,String keyword)throws Exception;

    /**
     * <PRE>
     * 설명 : 관리자 상세 정보
     * <PRE>
     * @author JangEunJi
     * @since 2022. 06. 01
     * @return
     */
    Member getManagerInfo(int cstmSeq);

    /**
     * <PRE>
     * 설명 : 관리자 상세 수정
     * <PRE>
     * @author JangEunJi
     * @since 2022. 06. 02
     * @return
     */
    void updateManagerInfo(int cstmSeq, ManagerRequestDto managerRequestDto );

    /**
     * <PRE>
     * 설명 : 관리자 계정 삭제
     * <PRE>
     * @author JangEunJi
     * @since 2022. 06. 20
     * @return
     */
    void deleteManager(int cstmSeq);

    /**
     * <PRE>
     * 설명 : 관리자 계정 삭제
     * <PRE>
     * @author JangEunJi
     * @since 2022. 06. 20
     * @return
     */
    void deleteManagerList(List<Integer> checkedManagerList);


}
