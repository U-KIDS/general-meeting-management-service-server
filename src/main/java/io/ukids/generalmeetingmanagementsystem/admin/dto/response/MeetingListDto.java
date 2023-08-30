package io.ukids.generalmeetingmanagementsystem.admin.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.ukids.generalmeetingmanagementsystem.domain.meeting.Meeting;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MeetingListDto {

    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime meetingDate;
    private String name;
    private Boolean activate;
    private String sponsor;

}
