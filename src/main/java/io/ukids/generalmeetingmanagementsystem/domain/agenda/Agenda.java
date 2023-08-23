package io.ukids.generalmeetingmanagementsystem.domain.agenda;

import io.ukids.generalmeetingmanagementsystem.common.exception.BaseException;
import io.ukids.generalmeetingmanagementsystem.common.exception.ErrorCode;
import io.ukids.generalmeetingmanagementsystem.domain.agenda.enums.AgendaResult;
import io.ukids.generalmeetingmanagementsystem.domain.agenda.enums.AgendaStatus;
import io.ukids.generalmeetingmanagementsystem.domain.basetime.BaseTimeEntity;
import io.ukids.generalmeetingmanagementsystem.domain.meeting.Meeting;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Agenda extends BaseTimeEntity {

    @Id
    @Column(name = "agenda_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private AgendaStatus status;

    private AgendaResult result;

    private LocalDateTime voteStartAt;

    private LocalDateTime voteEndAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_pk")
    private Meeting meeting;

    @Builder
    public Agenda(String title, String description, Meeting meeting) {
        this.title = title;
        this.description = description;
        this.meeting = meeting;
        this.status = AgendaStatus.NOT_STARTED;
    }

    public void start() {
        if (!status.equals(AgendaStatus.NOT_STARTED)) {
            throw new BaseException(ErrorCode.AGENDA_ALREADY_STARTED);
        }
        status = AgendaStatus.IN_PROGRESS;
        voteStartAt = LocalDateTime.now();
    }

    public void end() {
        if (!status.equals(AgendaStatus.COMPLETE)) {
            throw new BaseException(ErrorCode.AGENDA_ALREADY_ENDED);
        }
        status = AgendaStatus.COMPLETE;
    }

    public void validateMeeting(Meeting meeting) {
        if (!this.meeting.equals(meeting)) {
            throw new BaseException(ErrorCode.AGENDA_NOT_MATCHES_TO_MEETING);
        }
    }
}
