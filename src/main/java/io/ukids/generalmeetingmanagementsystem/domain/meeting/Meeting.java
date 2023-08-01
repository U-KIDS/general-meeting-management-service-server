package io.ukids.generalmeetingmanagementsystem.domain.meeting;

import io.ukids.generalmeetingmanagementsystem.domain.basetime.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Meeting extends BaseTimeEntity {

    @Id
    @Column(name = "meeting_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Boolean activate;

    private LocalDateTime meetingDate;

    private LocalDateTime meetingStartAt;

    @Builder
    public Meeting(String name, LocalDateTime meetingDate) {
        this.name = name;
        this.meetingDate = meetingDate;
        this.activate = false;
    }

    public void start() {
        if (activate) {
            throw new IllegalStateException("이미 시작된 회의입니다.");
        }
        activate = true;
        meetingStartAt = LocalDateTime.now();
    }

    public void end() {
        if (!activate) {
            throw new IllegalStateException("이미 종료된 회의입니다.");
        }
        activate = false;
    }
}
