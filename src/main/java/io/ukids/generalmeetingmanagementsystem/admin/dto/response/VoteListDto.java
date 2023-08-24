package io.ukids.generalmeetingmanagementsystem.admin.dto.response;

import io.ukids.generalmeetingmanagementsystem.domain.vote.Vote;
import io.ukids.generalmeetingmanagementsystem.domain.vote.enums.VoteValue;
import lombok.Builder;

import java.util.List;

@Builder
public class VoteListDto {
    private Integer size;
    private List<VoteInfoDto> voteInfoDtos;

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
