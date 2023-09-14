package io.ukids.generalmeetingmanagementsystem.admin.dto.response;

import io.ukids.generalmeetingmanagementsystem.domain.agenda.enums.AgendaStatus;
import io.ukids.generalmeetingmanagementsystem.domain.vote.Vote;
import io.ukids.generalmeetingmanagementsystem.domain.vote.enums.VoteValue;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class VoteListDto {
    private String agendaName;
    private AgendaStatus agendaStatus;
    private Integer size;
    private List<VoteInfoDto> votes;

    @Builder
    public VoteListDto(String agendaName, AgendaStatus agendaStatus, Integer size, List<VoteInfoDto> votes) {
        this.agendaName = agendaName;
        this.agendaStatus = agendaStatus;
        this.size = size;
        this.votes = votes;
    }

    @Getter
    @NoArgsConstructor
    public static class VoteInfoDto {
        private String name;
        private VoteValue voteValue;
        private String studentNumber;
        private String major;

        public VoteInfoDto(Vote vote) {
            this.name = vote.getMember().getName();
            this.voteValue = vote.getVoteValue();
            this.studentNumber = vote.getMember().getStudentNumber();
            this.major = vote.getMember().getMajor();
        }
    }
}
