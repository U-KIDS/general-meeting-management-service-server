package io.ukids.generalmeetingmanagementsystem.auth.service;

import io.ukids.generalmeetingmanagementsystem.auth.controller.dto.request.LoginDto;
import io.ukids.generalmeetingmanagementsystem.auth.controller.dto.response.TokenDto;
import io.ukids.generalmeetingmanagementsystem.auth.jwt.TokenProvider;
import io.ukids.generalmeetingmanagementsystem.auth.controller.dto.request.SignupDto;
import io.ukids.generalmeetingmanagementsystem.common.dto.CreateDto;
import io.ukids.generalmeetingmanagementsystem.common.exception.BaseException;
import io.ukids.generalmeetingmanagementsystem.common.exception.ErrorCode;
import io.ukids.generalmeetingmanagementsystem.common.mapper.MemberMapper;
import io.ukids.generalmeetingmanagementsystem.common.util.S3Uploader;
import io.ukids.generalmeetingmanagementsystem.domain.member.Member;
import io.ukids.generalmeetingmanagementsystem.domain.member.MemberRepository;
import io.ukids.generalmeetingmanagementsystem.domain.member.enums.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final S3Uploader s3Uploader;
    private final MemberMapper memberMapper;

    public TokenDto login(LoginDto loginDto) {

        if (!memberRepository.existsByStudentNumber(loginDto.getStudentNumber())) {
            throw new BaseException(ErrorCode.MEMBER_NOT_FOUND);
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getStudentNumber(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);

        return new TokenDto(token);
    }

    @Transactional
    public CreateDto signup(SignupDto signupDto, MultipartFile image) {

        if (memberRepository.existsByStudentNumber(signupDto.getStudentNumber())) {
            throw new BaseException(ErrorCode.MEMBER_ALREADY_EXISTS);
        }

        String imageUrl = s3Uploader.upload(image);
        Member member = memberMapper.map(signupDto, imageUrl, Authority.ROLE_USER);
        Long id = memberRepository.save(member).getId();
        return new CreateDto(id);
    }

}
