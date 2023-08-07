package io.ukids.generalmeetingmanagementsystem.client.dto.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Builder
public class MemberDetailClientResponseDto {
    private Long memberId;
    private String college;
    private String major;
    private String name;
    private List<MeetingClientDto> meetingList;
}
