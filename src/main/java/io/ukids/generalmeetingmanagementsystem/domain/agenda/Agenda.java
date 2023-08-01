package io.ukids.generalmeetingmanagementsystem.domain.agenda;

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

    private Boolean activate;

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
        this.activate = false;
    }

    public void start() {
        if (activate) {
            throw new IllegalStateException("이미 활성화된 안건입니다.");
        }
        activate = true;
        voteStartAt = LocalDateTime.now();
    }

    public void end() {
        if (!activate) {
            throw new IllegalStateException("이미 종료된 안건입니다.");
        }
        activate = false;
    }
}
