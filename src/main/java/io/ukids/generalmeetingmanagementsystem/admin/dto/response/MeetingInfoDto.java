package io.ukids.generalmeetingmanagementsystem.admin.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
public class MeetingInfoDto {
    private String name;
    private String sponsor;
    private LocalDateTime meetingDate;
}
