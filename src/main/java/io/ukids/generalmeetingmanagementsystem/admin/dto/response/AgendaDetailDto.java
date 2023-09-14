package io.ukids.generalmeetingmanagementsystem.admin.dto.response;

import io.ukids.generalmeetingmanagementsystem.domain.agenda.enums.AgendaResult;
import io.ukids.generalmeetingmanagementsystem.domain.agenda.enums.AgendaStatus;
import io.ukids.generalmeetingmanagementsystem.domain.vote.enums.VoteValue;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AgendaDetailDto {

    private String title;
    private AgendaStatus agendaStatus;
    private String agendaNumber;
    private String agendaCreateBy;
    private AgendaResult result;
    private VotePreviewDto votePreviewDto;
    private List<String> imageUrls;

    @Data
    @Builder
    public static class VotePreviewDto {
        private Long agree;
        private Long disagree;
        private Long abstention;
    }

}
