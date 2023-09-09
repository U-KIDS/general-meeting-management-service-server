package io.ukids.generalmeetingmanagementsystem.client.dto.response;

import io.ukids.generalmeetingmanagementsystem.domain.agenda.Agenda;
import io.ukids.generalmeetingmanagementsystem.domain.agenda.enums.AgendaStatus;
import io.ukids.generalmeetingmanagementsystem.domain.vote.enums.VoteValue;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
@Builder
public class AgendaClientResponseDto {

    private String meetingTitle;
    private LocalDateTime meetingDate;
    private List<AgendaInfoDto> agendas;

    @Getter
    @NoArgsConstructor
    public static class AgendaInfoDto {
        private Long AgendaId;
        private String title;
        private String status;

        public AgendaInfoDto(Agenda agenda) {
            this.AgendaId = agenda.getId();
            this.title = agenda.getTitle();
            this.status = agenda.getStatus().name();
        }
    }
}
