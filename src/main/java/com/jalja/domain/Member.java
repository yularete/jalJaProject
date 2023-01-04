package com.jalja.domain;

import com.jalja.constant.Role;
import com.jalja.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name="member")
@Getter @Setter
@ToString //변수 값들을 리턴해주는 toString 메소드를 자동 생성해줌
public class Member extends BaseEntity{

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO) //기본키를 데이터베이스가 Auto로 할당해쥼
    private Long id;

    private String name;

    @Column(unique = true) //동일한 값 방지
    private String email;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING) //enum 타입을 속성으로 지정. String으로 저장해 enum의 순서가 바뀌는 걸 방지
    private Role role;

    //Member entity 생성하는 메소드
    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getAddress());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.USER);
        return member;
    }
}
