package io.ukids.generalmeetingmanagementsystem.client.controller;

import io.ukids.generalmeetingmanagementsystem.auth.jwt.JwtFilter;
import io.ukids.generalmeetingmanagementsystem.auth.jwt.TokenProvider;
import io.ukids.generalmeetingmanagementsystem.domain.member.Member;
import io.ukids.generalmeetingmanagementsystem.domain.member.MemberRepository;
import io.ukids.generalmeetingmanagementsystem.domain.member.enums.Authority;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Rollback
public class MemberClientControllerTest {

    @LocalServerPort
    private int port;
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;
    @Autowired
    private TokenProvider tokenProvider;


    // 테스트용 유저 데이터
    private String studentNumber = "20202020";
    private String password = "testpw";
    private String name = "홍길동";
    private String college = "공과대학";
    private String major = "컴퓨터공학과";
    private Integer grade = 3;
    private String imageUrl = null;
    private Set<Authority> authorities;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        Authority authority = Authority.ROLE_USER;
        authorities.add(authority);

        Member member = new Member(studentNumber, password, name, college, major, grade, imageUrl, authorities);

        memberRepository.save(member);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(studentNumber, password);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER,"Bearer "+ token);
    }

    @AfterEach
    public void cleanUp() {
//        Member member = memberRepository.findByStudentNumber(studentNumber)
//                .orElseThrow();
//        memberRepository.delete(member);
        memberRepository.deleteAll();
    }

    @Test
    public void findMemberDetail(String token) throws Exception {
        // given
        String url = "http://localhost:"+port+"/api/client/detail";

        mockMvc.perform(get(url)
                        .header("Authorization", "Bearer" + token))
                .andExpect(status().isOk());

//                .contentType(MediaType.APPLICATION_JSON)
//                .content(new ObjectMapper().writeValueAsString())
    }
}
