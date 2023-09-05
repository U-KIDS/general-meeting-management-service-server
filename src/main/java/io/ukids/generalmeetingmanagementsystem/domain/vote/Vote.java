package io.ukids.generalmeetingmanagementsystem.domain.vote;

import io.ukids.generalmeetingmanagementsystem.domain.agenda.Agenda;
import io.ukids.generalmeetingmanagementsystem.domain.basetime.BaseTimeEntity;
import io.ukids.generalmeetingmanagementsystem.domain.member.Member;
import io.ukids.generalmeetingmanagementsystem.domain.vote.enums.VoteValue;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vote extends BaseTimeEntity {

    @Id
    @Column(name = "vote_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private VoteValue voteValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agenda_id")
    private Agenda agenda;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Vote(VoteValue voteValue, Agenda agenda, Member member) {
        this.voteValue = voteValue;
        this.agenda = agenda;
        this.member = member;
    }
}
