package io.ukids.generalmeetingmanagementsystem.admin.dto.response;

import io.ukids.generalmeetingmanagementsystem.domain.agenda.enums.AgendaStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AgendaDetailDto {

    private String title;
    private AgendaStatus agendaStatus;
    private VotePreviewDto votePreviewDto;

    @Data
    @Builder
    public static class VotePreviewDto {
        private Long agree;
        private Long disagree;
        private Long abstention;
    }

}
