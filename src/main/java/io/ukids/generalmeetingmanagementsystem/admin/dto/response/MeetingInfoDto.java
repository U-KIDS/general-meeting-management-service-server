package io.ukids.generalmeetingmanagementsystem.admin.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.ukids.generalmeetingmanagementsystem.domain.meeting.Meeting;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
public class MeetingInfoDto {
    private String name;
    private String sponsor;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime meetingDate;

    public MeetingInfoDto(Meeting meeting) {
        this.name = meeting.getName();
        this.sponsor = meeting.getSponsor();
        this.meetingDate = meeting.getMeetingDate();
    }
}
