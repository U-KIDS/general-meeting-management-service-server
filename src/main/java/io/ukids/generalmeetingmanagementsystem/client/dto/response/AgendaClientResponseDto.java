package io.ukids.generalmeetingmanagementsystem.client.dto.response;

import io.ukids.generalmeetingmanagementsystem.domain.agenda.Agenda;
import io.ukids.generalmeetingmanagementsystem.domain.agenda.enums.AgendaStatus;
import io.ukids.generalmeetingmanagementsystem.domain.vote.enums.VoteValue;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
@Builder
public class AgendaClientResponseDto {
    private Long AgendaId;
    private String title;
    private String description;
    private String status;

    public AgendaClientResponseDto(Agenda agenda) {
        this.AgendaId = agenda.getId();
        this.title = agenda.getTitle();
        this.description = agenda.getDescription();
        this.status = agenda.getStatus().name();
    }
}
