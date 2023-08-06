package io.ukids.generalmeetingmanagementsystem.domain.member;

import io.ukids.generalmeetingmanagementsystem.domain.member.enums.Authority;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberQueryRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberQueryRepository memberQueryRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() {
        this.getMemberList().forEach(m -> memberRepository.save(m));
    }

    @Test
    public void findDynamicQueryMembers() {

        MemberSearchCondition condition = MemberSearchCondition.builder()
                .college("공과대학")
                .major("컴퓨터공학과")
                .build();
        PageRequest pageRequest = PageRequest.of(0, 5);

        List<Member> pagedMembers = memberQueryRepository.findDynamicQueryMembers(condition, pageRequest);

        System.out.println("=============");
        pagedMembers.forEach(m -> System.out.println(m.getStudentNumber()));
        System.out.println("=============");
    }

    private List<Member> getMemberList() {
        List<Member> members = new ArrayList<>();

        Member m1 = Member.builder()
                .studentNumber("20194001")
                .password(passwordEncoder.encode("password"))
                .name("김태완")
                .college("SW중심대학")
                .major("컴퓨터소프트웨어공학과")
                .grade(3)
                .authorities(Collections.singleton(Authority.ROLE_USER))
                .build();
        members.add(m1);

        Member m2 = Member.builder()
                .studentNumber("20194002")
                .password(passwordEncoder.encode("password"))
                .name("김태완")
                .college("SW중심대학")
                .major("컴퓨터소프트웨어공학과")
                .grade(3)
                .authorities(Collections.singleton(Authority.ROLE_USER))
                .build();
        members.add(m2);

        Member m3 = Member.builder()
                .studentNumber("20194003")
                .password(passwordEncoder.encode("password"))
                .name("김태완")
                .college("공과대학")
                .major("전자공학과")
                .grade(3)
                .authorities(Collections.singleton(Authority.ROLE_USER))
                .build();
        members.add(m3);

        Member m4 = Member.builder()
                .studentNumber("20194004")
                .password(passwordEncoder.encode("password"))
                .name("김태완")
                .college("공과대학")
                .major("컴퓨터공학과")
                .grade(3)
                .authorities(Collections.singleton(Authority.ROLE_USER))
                .build();
        members.add(m4);

        Member m5 = Member.builder()
                .studentNumber("20194005")
                .password(passwordEncoder.encode("password"))
                .name("김태완")
                .college("공과대학")
                .major("컴퓨터공학과")
                .grade(3)
                .authorities(Collections.singleton(Authority.ROLE_USER))
                .build();
        members.add(m5);

        return members;
    }

}