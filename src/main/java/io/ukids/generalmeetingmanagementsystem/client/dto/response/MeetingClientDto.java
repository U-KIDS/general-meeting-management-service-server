package io.ukids.generalmeetingmanagementsystem.client.dto.response;

import io.ukids.generalmeetingmanagementsystem.domain.meeting.Meeting;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class MeetingClientDto {
    private Long id;
    private String meetingName;
    private LocalDateTime meetingDate;

    public MeetingClientDto(Meeting meeting) {
        this.id = meeting.getId();
        this.meetingName = meeting.getName();
        this.meetingDate = meeting.getMeetingDate();
    }
}
