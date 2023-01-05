package com.jalja.domain;

import com.jalja.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class MemberTest {

    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext //EntityManager(영속성 관리)를 빈으로 주입
    EntityManager em;

    @Test
    @DisplayName("Auditing 테스트")
    @WithMockUser(username= "genos", roles = "USER") //genos 라는 사용자가 로그인 한 상태라 가정하고 테스트 진행 가능
    public void auditingTest(){
        Member newMember = new Member();
        memberRepository.save(newMember);

        em.flush();
        em.clear();

        Member member = memberRepository.findById(newMember.getId()).orElseThrow(EntityNotFoundException::new);

        System.out.println(" register time " + member.getRegDate());
        System.out.println(" update time " + member.getModDate());
        System.out.println(" create time " + member.getCreatedBy());
        System.out.println(" modify time " + member.getModifiedBy());

    }
}