package io.ukids.generalmeetingmanagementsystem.client.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class MeetingClinetDto {
    private Long id;
    private String meetingName;
    private LocalDateTime meetingDate;
}
