package io.ukids.generalmeetingmanagementsystem.admin.service.member;

import io.ukids.generalmeetingmanagementsystem.domain.member.Member;
import io.ukids.generalmeetingmanagementsystem.domain.member.MemberRepository;
import io.ukids.generalmeetingmanagementsystem.domain.member.enums.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberInitService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.password}")
    private String id;

    @Value("${admin.password}")
    private String password;

    public void init() {
        Member adminMember = Member.builder()
                .studentNumber(id)
                .password(passwordEncoder.encode(password))
                .college("순천향대학교")
                .major("총대의원회")
                .grade(1)
                .name("paran admin")
                .imageUrl("image")
                .authorities(Collections.singleton(Authority.ROLE_ADMIN))
                .build();

        memberRepository.save(adminMember);
    }

}
