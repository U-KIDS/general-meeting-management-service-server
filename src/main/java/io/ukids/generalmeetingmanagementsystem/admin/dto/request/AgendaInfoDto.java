package io.ukids.generalmeetingmanagementsystem.admin.dto.request;

import io.ukids.generalmeetingmanagementsystem.domain.agenda.Agenda;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AgendaInfoDto {
    private String title;
    private String agendaNumber;
    private String agendaCreateBy;

    public AgendaInfoDto(Agenda agenda) {
        this.title = agenda.getTitle();
        this.agendaNumber = agenda.getAgendaNumber();
        this.agendaCreateBy = agenda.getAgendaCreateBy();
    }
}
