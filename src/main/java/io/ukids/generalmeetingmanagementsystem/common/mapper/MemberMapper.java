package io.ukids.generalmeetingmanagementsystem.common.mapper;

import io.ukids.generalmeetingmanagementsystem.auth.controller.dto.request.SignupDto;
import io.ukids.generalmeetingmanagementsystem.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberMapper {

    private final PasswordEncoder passwordEncoder;

    public Member map(SignupDto signupDto) {
        return Member.builder()
                .studentNumber(signupDto.getStudentNumber())
                .password(signupDto.getPassword())
                .name(signupDto.getName())
                .college(signupDto.getCollege())
                .major(signupDto.getMajor())
                .grade(signupDto.getGrade())
                .imageUrl(signupDto.getImageUrl())
                .build();
    }
}
