package io.ukids.generalmeetingmanagementsystem.auth.controller;

import io.ukids.generalmeetingmanagementsystem.auth.controller.dto.request.LoginDto;
import io.ukids.generalmeetingmanagementsystem.auth.controller.dto.request.SignupDto;
import io.ukids.generalmeetingmanagementsystem.common.AbstractControllerTest;
import io.ukids.generalmeetingmanagementsystem.domain.member.Member;
import io.ukids.generalmeetingmanagementsystem.domain.member.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

class AuthControllerTest extends AbstractControllerTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        List<Member> members = this.createTestMembers();
        members.forEach(m -> memberRepository.save(m));
    }

    @Test
    @DisplayName("로그인")
    public void login() throws Exception {

        LoginDto loginDto = LoginDto.builder()
                .studentNumber("20194059")
                .password("password")
                .build();

        this.mockMvc.perform(post("/auth/login/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto)))
                .andDo(print());
    }

    @Test
    @DisplayName("회원가입")
    public void signup() throws Exception{
        SignupDto signupDto = SignupDto.builder()
                .studentNumber("20231111")
                .password("비밀번호")
                .name("이름")
                .major("학과")
                .college("단과대학")
                .grade(3)
                .imageUrl("url")
                .build();

        this.mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signupDto)))
                .andDo(print());
    }

}