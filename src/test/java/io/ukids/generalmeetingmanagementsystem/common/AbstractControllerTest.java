package io.ukids.generalmeetingmanagementsystem.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.ukids.generalmeetingmanagementsystem.domain.member.Member;
import io.ukids.generalmeetingmanagementsystem.domain.member.enums.Authority;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@Transactional
@Rollback
public class AbstractControllerTest {

    @Autowired
    public MockMvc mockMvc;
    @Autowired
    public ObjectMapper objectMapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    public List<Member> createTestMembers() {

        List<Member> members = new ArrayList<>();

        Member member1 = Member.builder()
                .studentNumber("20194059")
                .password(passwordEncoder.encode("password"))
                .name("김태완")
                .college("SW중심대학")
                .major("컴퓨터소프트웨어공학과")
                .grade(3)
                .authorities(Collections.singleton(Authority.ROLE_USER))
                .build();
        members.add(member1);

        return members;

    }
}
