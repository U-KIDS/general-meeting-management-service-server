package io.ukids.generalmeetingmanagementsystem.auth.service;

import io.ukids.generalmeetingmanagementsystem.auth.dto.request.AdminLoginDto;
import io.ukids.generalmeetingmanagementsystem.auth.dto.request.UserLoginDto;
import io.ukids.generalmeetingmanagementsystem.auth.jwt.TokenProvider;
import io.ukids.generalmeetingmanagementsystem.domain.member.Member;
import io.ukids.generalmeetingmanagementsystem.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public String login(UserLoginDto userLoginDto) {

        validateExist(userLoginDto.getStudentNumber());
        validateName(userLoginDto);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userLoginDto.getStudentNumber(), userLoginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return tokenProvider.createToken(authentication);
    }

    public String login(AdminLoginDto adminLoginDto) {

        validateExist(adminLoginDto.getStudentNumber());

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(adminLoginDto.getStudentNumber(), adminLoginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return tokenProvider.createToken(authentication);
    }

    private void validateExist(String studentNumber) {
        if (!memberRepository.existsByStudentNumber(studentNumber)) {
            throw new RuntimeException("가입되지 않은 유저입니다.");
        }
    }

    private void validateName(UserLoginDto userLoginDto) {
        Member member = memberRepository.findByStudentNumber(userLoginDto.getStudentNumber())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        member.validate(userLoginDto.getName());
    }

}
