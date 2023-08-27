package io.ukids.generalmeetingmanagementsystem.client.controller;

import io.ukids.generalmeetingmanagementsystem.common.AbstractControllerTest;
import io.ukids.generalmeetingmanagementsystem.common.exception.BaseException;
import io.ukids.generalmeetingmanagementsystem.domain.agenda.Agenda;
import io.ukids.generalmeetingmanagementsystem.domain.agenda.AgendaRepository;
import io.ukids.generalmeetingmanagementsystem.domain.meeting.Meeting;
import io.ukids.generalmeetingmanagementsystem.domain.meeting.MeetingRepository;
import jdk.jshell.Snippet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static io.ukids.generalmeetingmanagementsystem.common.exception.ErrorCode.MEETING_NOT_FOUND;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AgendaClientControllerTest extends AbstractControllerTest {

    @Autowired
    MeetingRepository meetingRepository;
    @Autowired
    AgendaRepository agendaRepository;

    Long meetingId = 1L;

    @BeforeEach
    public void setUp() {
        List<Meeting> meetings = this.createTestMeetings();
        meetings.forEach(m -> meetingRepository.save(m));

        List<Agenda> agendas = this.createTestAgendas(meetingRepository.findById(meetingId)
                .orElseThrow(() -> new BaseException(MEETING_NOT_FOUND)));
        agendas.forEach(a -> agendaRepository.save(a));
    }

    @Test
    @DisplayName("모든안건찾기")
    public void findAllAgenda() throws Exception {
        this.mockMvc.perform(get("/api/client/" + meetingId))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
