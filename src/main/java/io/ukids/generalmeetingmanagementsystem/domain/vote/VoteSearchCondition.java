package io.ukids.generalmeetingmanagementsystem.domain.vote;

import io.ukids.generalmeetingmanagementsystem.domain.vote.enums.VoteValue;
import lombok.Builder;
import lombok.Getter;

@Getter
public class VoteSearchCondition {
    private VoteValue voteValue;
    private String name;

    @Builder
    public VoteSearchCondition(VoteValue voteValue, String name) {
        this.voteValue = voteValue;
        this.name = name;
    }
}
