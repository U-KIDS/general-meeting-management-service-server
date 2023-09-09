package io.ukids.generalmeetingmanagementsystem.domain.meeting;

import io.ukids.generalmeetingmanagementsystem.admin.dto.response.MeetingInfoDto;
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

    private String sponsor;

    private Boolean activate;

    private LocalDateTime meetingDate;

    @Builder
    public Meeting(String name, LocalDateTime meetingDate, String sponsor) {
        this.name = name;
        this.meetingDate = meetingDate;
        this.sponsor = sponsor;
        this.activate = false;
    }

    public void activate() {
        if (activate) {
            throw new BaseException(ErrorCode.MEETING_ALREADY_START);
        }
        activate = true;
    }

    public void deactivate() {
        if (!activate) {
            throw new BaseException(ErrorCode.MEETING_ALREADY_END);
        }
        activate = false;
    }

    public void update(MeetingInfoDto meetingInfoDto) {
        this.name = meetingInfoDto.getName();
        this.meetingDate = meetingInfoDto.getMeetingDate();
        this.sponsor = meetingInfoDto.getSponsor();
    }
}
