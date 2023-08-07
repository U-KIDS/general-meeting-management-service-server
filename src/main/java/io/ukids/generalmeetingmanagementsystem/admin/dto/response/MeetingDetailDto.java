package io.ukids.generalmeetingmanagementsystem.admin.dto.response;

import io.ukids.generalmeetingmanagementsystem.domain.agenda.Agenda;
import lombok.Data;

import java.util.List;

@Data
public class MeetingDetailDto {
    private String meetingName;
    private List<Agenda> agendas;
}
