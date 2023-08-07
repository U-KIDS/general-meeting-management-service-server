package io.ukids.generalmeetingmanagementsystem.admin.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MeetingInfoDto {
    private String name;
    private LocalDateTime meetingDate;
}
