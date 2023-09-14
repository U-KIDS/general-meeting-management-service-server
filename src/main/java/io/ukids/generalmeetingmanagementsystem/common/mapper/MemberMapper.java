package io.ukids.generalmeetingmanagementsystem.common.mapper;

import io.ukids.generalmeetingmanagementsystem.admin.dto.response.MemberDetailDto;
import io.ukids.generalmeetingmanagementsystem.admin.dto.response.MemberListDto;
import io.ukids.generalmeetingmanagementsystem.auth.controller.dto.request.SignupDto;
import io.ukids.generalmeetingmanagementsystem.domain.member.Member;
import io.ukids.generalmeetingmanagementsystem.domain.member.enums.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class MemberMapper {

    private final PasswordEncoder passwordEncoder;

    public Member map(SignupDto signupDto, String imageUrl, Authority authority) {
        return Member.builder()
                .studentNumber(signupDto.getStudentNumber())
                .password(passwordEncoder.encode(signupDto.getPassword()))
                .name(signupDto.getName())
                .college(signupDto.getCollege())
                .major(signupDto.getMajor())
                .grade(signupDto.getGrade())
                .imageUrl(imageUrl)
                .authorities(Collections.singleton(authority))
                .build();
    }

    public MemberListDto map(Member member) {
        return MemberListDto.builder()
                .studentNumber(member.getStudentNumber())
                .name(member.getName())
                .college(member.getCollege())
                .major(member.getMajor())
                .activate(member.getActivate())
                .grade(member.getGrade())
                .build();
    }
}
