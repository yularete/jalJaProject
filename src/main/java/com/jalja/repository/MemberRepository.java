package com.jalja.repository;

import com.jalja.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email); //이메일로 회원을 검사할 수 있게 쿼리 메소드 작성
}
