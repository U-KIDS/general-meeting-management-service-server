package io.ukids.generalmeetingmanagementsystem.common.mapper;

import io.ukids.generalmeetingmanagementsystem.admin.dto.response.MeetingDetailDto;
import io.ukids.generalmeetingmanagementsystem.domain.agenda.Agenda;
import org.springframework.stereotype.Component;

@Component
public class AgendaMapper {

    public MeetingDetailDto.AgendaInfoDto map(Agenda agenda) {
        return MeetingDetailDto.AgendaInfoDto.builder()
                .title(agenda.getTitle())
                .agendaStatus(agenda.getStatus())
                .build();
    }
}
