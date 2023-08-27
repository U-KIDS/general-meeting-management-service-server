package io.ukids.generalmeetingmanagementsystem.common.mapper;

import io.ukids.generalmeetingmanagementsystem.admin.dto.request.AgendaInfoDto;
import io.ukids.generalmeetingmanagementsystem.admin.dto.response.MeetingDetailDto;
import io.ukids.generalmeetingmanagementsystem.domain.agenda.Agenda;
import io.ukids.generalmeetingmanagementsystem.domain.meeting.Meeting;
import org.springframework.stereotype.Component;

@Component
public class AgendaMapper {

    public MeetingDetailDto.AgendaInfoDto map(Agenda agenda) {
        return MeetingDetailDto.AgendaInfoDto.builder()
                .agendaId(agenda.getId())
                .title(agenda.getTitle())
                .agendaStatus(agenda.getStatus())
                .build();
    }

    public Agenda map(AgendaInfoDto agendaInfoDto, Meeting meeting) {
        return Agenda.builder()
                .title(agendaInfoDto.getTitle())
                .meeting(meeting)
                .build();
    }
}
