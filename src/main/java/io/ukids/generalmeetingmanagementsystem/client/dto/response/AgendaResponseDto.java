package io.ukids.generalmeetingmanagementsystem.client.dto.response;

import io.ukids.generalmeetingmanagementsystem.domain.agenda.Agenda;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class AgendaResponseDto {
    private Long id;
    private String title;
    private String description;
    private Boolean activate;

    public AgendaResponseDto(Agenda agenda) {
        this.id = agenda.getId();
        this.title = agenda.getTitle();
        this.description = agenda.getDescription();
        this.activate = agenda.getActivate();
    }
}
