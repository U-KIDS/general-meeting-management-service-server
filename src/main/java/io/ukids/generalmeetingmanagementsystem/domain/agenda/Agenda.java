package io.ukids.generalmeetingmanagementsystem.domain.agenda;

import io.ukids.generalmeetingmanagementsystem.admin.dto.request.AgendaInfoDto;
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

    private String agendaNumber;

    private String agendaCreateBy;

    private AgendaStatus status;

    private AgendaResult result;

    private LocalDateTime voteStartAt;

    private LocalDateTime voteEndAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    @Builder
    public Agenda(String title, String description, String agendaNumber, String agendaCreateBy, Meeting meeting) {
        this.title = title;
        this.description = description;
        this.agendaNumber = agendaNumber;
        this.agendaCreateBy = agendaCreateBy;
        this.meeting = meeting;
        this.status = AgendaStatus.NOT_STARTED;
    }

    public void start() {
        if (status.equals(AgendaStatus.IN_PROGRESS)) {
            throw new BaseException(ErrorCode.AGENDA_ALREADY_STARTED);
        }
        status = AgendaStatus.IN_PROGRESS;
        voteStartAt = LocalDateTime.now();
    }

    public void end() {
        if (status.equals(AgendaStatus.COMPLETE)) {
            throw new BaseException(ErrorCode.AGENDA_ALREADY_ENDED);
        }
        status = AgendaStatus.COMPLETE;
        voteEndAt = LocalDateTime.now();
    }

    public void resolve(AgendaResult agendaResult) {
        result = agendaResult;
    }

    public void update(AgendaInfoDto agendaInfoDto) {
        this.title = agendaInfoDto.getTitle();
        this.agendaNumber = agendaInfoDto.getAgendaNumber();
        this.agendaCreateBy = agendaInfoDto.getAgendaCreateBy();
    }
}
