package com.jalja.Service;

import com.jalja.domain.Member;
import com.jalja.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional//로직을 처리하다가 에러가 발생하면 변경된 데이터를 로직을 수행하기 전으로 콜백 시켜줌
@RequiredArgsConstructor //빈(자바객체)에 생성자가 1개이며, 생성자의 파라미터 타입이 빈으로 등록이 가능하다면 @Autowired 없이 의존성 주입이 가능
public class MemberService implements UserDetailsService { //Ms가 uds 구현한다

    private final MemberRepository memberRepository; //final, @NonNull이 붙은 필드에 생성자를 생성해주는 requiredArgsConstructor 어노테이션 !

    public void validateDuplicateMember(Member member){
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    @Override // UserDetailsService의 loadUserByUsername() 메소드 오버라이딩
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            Member member = memberRepository.findByEmail(email);

            if(member == null){
                throw new UsernameNotFoundException(email);
            }
            return User.builder() //UserDetail을 구현하고 있는 User 객체 반환
                    .username(member.getEmail()) //User 객체를 생성하기 위해 생성자로 값들을 파라미터로 넘겨줌
                    .password(member.getPassword())
                    .roles(member.getRole().toString())
                    .build();
    }
}
