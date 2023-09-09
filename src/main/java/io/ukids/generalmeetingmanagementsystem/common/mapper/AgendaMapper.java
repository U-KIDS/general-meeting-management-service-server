package io.ukids.generalmeetingmanagementsystem.common.mapper;

import io.ukids.generalmeetingmanagementsystem.admin.dto.request.AgendaInfoDto;
import io.ukids.generalmeetingmanagementsystem.admin.dto.response.MeetingDetailDto;
import io.ukids.generalmeetingmanagementsystem.domain.agenda.Agenda;
import io.ukids.generalmeetingmanagementsystem.domain.meeting.Meeting;
import org.springframework.stereotype.Component;

@Component
public class AgendaMapper {

    public Agenda map(AgendaInfoDto agendaInfoDto, Meeting meeting) {
        return Agenda.builder()
                .title(agendaInfoDto.getTitle())
                .agendaNumber(agendaInfoDto.getAgendaNumber())
                .agendaCreateBy(agendaInfoDto.getAgendaCreateBy())
                .meeting(meeting)
                .build();
    }
}
