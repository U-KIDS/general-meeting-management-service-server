package io.ukids.generalmeetingmanagementsystem.auth.controller;

import io.ukids.generalmeetingmanagementsystem.auth.dto.request.UserLoginDto;
import io.ukids.generalmeetingmanagementsystem.common.ControllerTest;
import io.ukids.generalmeetingmanagementsystem.domain.member.Member;
import io.ukids.generalmeetingmanagementsystem.domain.member.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Transactional
@Rollback
class AuthControllerTest extends ControllerTest {

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
    public void userLogin() throws Exception {

        UserLoginDto userLoginDto = UserLoginDto.builder()
                .studentNumber("20194059")
                .name("김태완")
                .build();

        this.mockMvc.perform(post("/auth/login/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userLoginDto)))
                .andDo(print());
    }

}