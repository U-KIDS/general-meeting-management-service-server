package io.ukids.generalmeetingmanagementsystem.admin.service.meeting;

import io.ukids.generalmeetingmanagementsystem.admin.dto.response.MeetingDetailDto;
import io.ukids.generalmeetingmanagementsystem.admin.dto.response.MeetingListDto;
import io.ukids.generalmeetingmanagementsystem.common.dto.ListDto;
import io.ukids.generalmeetingmanagementsystem.common.exception.BaseException;
import io.ukids.generalmeetingmanagementsystem.common.exception.ErrorCode;
import io.ukids.generalmeetingmanagementsystem.common.mapper.AgendaMapper;
import io.ukids.generalmeetingmanagementsystem.common.mapper.MeetingMapper;
import io.ukids.generalmeetingmanagementsystem.domain.agenda.Agenda;
import io.ukids.generalmeetingmanagementsystem.domain.agenda.AgendaRepository;
import io.ukids.generalmeetingmanagementsystem.domain.meeting.Meeting;
import io.ukids.generalmeetingmanagementsystem.domain.meeting.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MeetingQueryAdminService {

    private final MeetingRepository meetingRepository;
    private final MeetingMapper meetingMapper;
    private final AgendaRepository agendaRepository;
    private final AgendaMapper agendaMapper;

    public ListDto<MeetingListDto> query() {
        List<Meeting> meetings = meetingRepository.findAll();
        List<MeetingListDto> result = meetings.stream()
                .map(meetingMapper::map)
                .collect(Collectors.toList());

        return new ListDto(result);
    }

    public MeetingDetailDto findOne(Long meetingId) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEETING_NOT_FOUND));
        List<Agenda> agendas = agendaRepository.findAllByMeetingId(meetingId);

        return MeetingDetailDto.builder()
                .meetingName(meeting.getName())
                .sponsor(meeting.getSponsor())
                .meetingDate(meeting.getMeetingDate())
                .activate(meeting.getActivate())
                .agendas(agendas.stream()
                        .map(agenda -> new MeetingDetailDto.AgendaInfoDto(agenda))
                        .collect(Collectors.toList()))
                .build();
    }

}
