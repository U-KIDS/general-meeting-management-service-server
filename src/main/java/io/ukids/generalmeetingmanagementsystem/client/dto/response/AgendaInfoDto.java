package io.ukids.generalmeetingmanagementsystem.client.dto.response;

import io.ukids.generalmeetingmanagementsystem.domain.agenda.Agenda;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AgendaInfoDto {
    private String title;
    private String agendaNumber;
    private String agendaCreateBy;

    @Builder
    public AgendaInfoDto(Agenda agenda) {
        this.title = agenda.getTitle();
        this.agendaNumber = getAgendaNumber();
        this.agendaCreateBy = agenda.getAgendaCreateBy();
    }
}
