package io.ukids.generalmeetingmanagementsystem.common.mapper;

import io.ukids.generalmeetingmanagementsystem.admin.dto.response.MeetingInfoDto;
import io.ukids.generalmeetingmanagementsystem.admin.dto.response.MeetingListDto;
import io.ukids.generalmeetingmanagementsystem.domain.meeting.Meeting;
import org.springframework.stereotype.Component;

@Component
public class MeetingMapper {

    public MeetingListDto map(Meeting meeting) {
        return MeetingListDto.builder()
                .meetingDate(meeting.getMeetingDate())
                .name(meeting.getName())
                .activate(meeting.getActivate())
                .build();
    }

    public Meeting map(MeetingInfoDto meetingInfoDto) {
        return Meeting.builder()
                .meetingDate(meetingInfoDto.getMeetingDate())
                .name(meetingInfoDto.getName())
                .build();
    }
}
