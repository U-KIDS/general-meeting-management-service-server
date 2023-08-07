package io.ukids.generalmeetingmanagementsystem.admin.dto.response;

import io.ukids.generalmeetingmanagementsystem.domain.meeting.Meeting;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MeetingListDto {

    private LocalDateTime meetingDate;
    private String name;
    private Boolean activate;

    public MeetingListDto(Meeting meeting) {
        this.meetingDate = meeting.getMeetingDate();
        this.name = meeting.getName();
        this.activate = meeting.getActivate();
    }

}
