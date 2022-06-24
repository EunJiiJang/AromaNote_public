package com.rf.aromanote.domain.admin.manager;

import com.rf.aromanote.domain.member.Member;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;


public class ManagerSpecification {

    public static Specification<Member> likeCstmNm(final String keyword) {
        return new Specification<Member>() {
            public Predicate toPredicate(Root<Member> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("cstmNm"), "%" + keyword + "%");
            }
        };
    }

    public static Specification<Member> likeEmail(final String keyword) {
        return new Specification<Member>() {
            public Predicate toPredicate(Root<Member> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("email"), "%" + keyword + "%");
            }
        };
    }

    public static Specification<Member> likeNickNm(final String keyword) {
        return new Specification<Member>() {
            public Predicate toPredicate(Root<Member> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("nickNm"), "%" + keyword + "%");
            }
        };
    }
    public static Specification<Member> likeUserRole(final String keyword) {
        return new Specification<Member>() {
            public Predicate toPredicate(Root<Member> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("userRole"), "%" + keyword + "%");
            }
        };
    }

}
