//package io.ukids.generalmeetingmanagementsystem.client.controller;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.ukids.generalmeetingmanagementsystem.auth.controller.dto.request.LoginDto;
//import io.ukids.generalmeetingmanagementsystem.common.AbstractControllerTest;
//import io.ukids.generalmeetingmanagementsystem.domain.meeting.Meeting;
//import io.ukids.generalmeetingmanagementsystem.domain.meeting.MeetingRepository;
//import io.ukids.generalmeetingmanagementsystem.domain.member.Member;
//import io.ukids.generalmeetingmanagementsystem.domain.member.MemberRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MvcResult;
//
//import java.util.List;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//public class MemberClientControllerTest extends AbstractControllerTest {
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Autowired
//    MeetingRepository meetingRepository;
//
//    @BeforeEach
//    public void setUp() {
//        List<Member> members = this.createTestMembers();
//        members.forEach(m -> memberRepository.save(m));
//
//        List<Meeting> meetings = this.createTestMeetings();
//        meetings.stream()
//                .limit(2)   // 2개의 회의만 start
//                .peek(Meeting::start)
//                .forEach(m -> meetingRepository.save(m));
//    }
//
//    @Test
//    @DisplayName("회원회의정보확인")
//    public void findMemberDetail() throws Exception {
//
//        LoginDto loginDto = LoginDto.builder()
//                .studentNumber("20194059")
//                .password("password")
//                .build();
//
//        MvcResult result = this.mockMvc.perform(post("/auth/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(loginDto)))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andReturn();
//
//        String responseBody = result.getResponse().getContentAsString();
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode responseJson = objectMapper.readTree(responseBody);
//
//        JsonNode tokenNode = responseJson.path("data").path("token");
//        String token = tokenNode.asText();
//
//        System.out.println("Token: " + token);
//
//        this.mockMvc.perform(get("/api/client/detail")
//                        .header("Authorization", "Bearer " + token))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//}
