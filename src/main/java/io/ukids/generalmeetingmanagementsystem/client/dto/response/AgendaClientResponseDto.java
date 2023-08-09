package io.ukids.generalmeetingmanagementsystem.client.dto.response;

import io.ukids.generalmeetingmanagementsystem.domain.agenda.Agenda;
import io.ukids.generalmeetingmanagementsystem.domain.agenda.enums.AgendaStatus;
import io.ukids.generalmeetingmanagementsystem.domain.vote.enums.VoteValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class AgendaClientResponseDto {
    private Long id;
    private String title;
    private String description;
    private String status;

    public AgendaClientResponseDto(Agenda agenda) {
        this.id = agenda.getId();
        this.title = agenda.getTitle();
        this.description = agenda.getDescription();
        this.status = agenda.getStatus().name();
    }
}
