package io.ukids.generalmeetingmanagementsystem.client.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
public class MemberMeetingClientResponseDto {
    private Long id;
    private String college;
    private String major;
    private String name;
    private List<MeetingClinetDto> meetingList;
}
