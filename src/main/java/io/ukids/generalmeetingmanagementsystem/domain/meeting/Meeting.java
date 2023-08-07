package io.ukids.generalmeetingmanagementsystem.domain.meeting;

import io.ukids.generalmeetingmanagementsystem.common.exception.BaseException;
import io.ukids.generalmeetingmanagementsystem.common.exception.ErrorCode;
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
            throw new BaseException(ErrorCode.MEETING_ALREADY_START);
        }
        activate = true;
        meetingStartAt = LocalDateTime.now();
    }

    public void end() {
        if (!activate) {
            throw new BaseException(ErrorCode.MEETING_ALREADY_END);
        }
        activate = false;
    }
}
