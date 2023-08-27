package io.ukids.generalmeetingmanagementsystem.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.ukids.generalmeetingmanagementsystem.domain.meeting.Meeting;
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

import java.time.LocalDateTime;
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

    public List<Meeting> createTestMeeting() {

        List<Meeting> meetings = new ArrayList<>();

        Meeting meeting1 = Meeting.builder()
                .name("제 1회 테스트 회의")
                .meetingDate(LocalDateTime.of(2023, 9, 15, 12, 00,00,0000))
                .sponsor("스폰서")
                .build();

        Meeting meeting2 = Meeting.builder()
                .name("제 2회 테스트 회의")
                .meetingDate(LocalDateTime.of(2023, 9, 15, 12, 00,00,0000))
                .sponsor("스폰서")
                .build();

        Meeting meeting3 = Meeting.builder()
                .name("제 3회 테스트 회의")
                .meetingDate(LocalDateTime.of(2023, 9, 15, 12, 00,00,0000))
                .sponsor("스폰서")
                .build();

        meetings.add(meeting1);
        meetings.add(meeting2);
        meetings.add(meeting3);

        return meetings;
    }
}
