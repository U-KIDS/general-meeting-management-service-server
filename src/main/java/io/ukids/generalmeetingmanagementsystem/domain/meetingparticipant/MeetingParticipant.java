package io.ukids.generalmeetingmanagementsystem.domain.meetingparticipant;

import io.ukids.generalmeetingmanagementsystem.domain.basetime.BaseTimeEntity;
import io.ukids.generalmeetingmanagementsystem.domain.meeting.Meeting;
import io.ukids.generalmeetingmanagementsystem.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MeetingParticipant extends BaseTimeEntity {

    @Id
    @Column(name = "meeting_participant_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_pk")
    private Meeting meeting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_pk")
    private Member member;

    @Builder
    public MeetingParticipant(Meeting meeting, Member member) {
        this.meeting = meeting;
        this.member = member;
    }
}
