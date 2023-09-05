package io.ukids.generalmeetingmanagementsystem.admin.service.meeting;

import io.ukids.generalmeetingmanagementsystem.admin.dto.response.MeetingDetailDto;
import io.ukids.generalmeetingmanagementsystem.admin.dto.response.MeetingInfoDto;
import io.ukids.generalmeetingmanagementsystem.admin.dto.response.MeetingListDto;
import io.ukids.generalmeetingmanagementsystem.admin.service.agenda.AgendaAdminService;
import io.ukids.generalmeetingmanagementsystem.common.dto.CreateDto;
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
@Transactional
@RequiredArgsConstructor
public class MeetingAdminService {

    private final MeetingRepository meetingRepository;
    private final AgendaRepository agendaRepository;
    private final AgendaAdminService agendaAdminService;
    private final MeetingMapper meetingMapper;

    public CreateDto create(MeetingInfoDto meetingInfoDto) {
        Meeting meeting = meetingMapper.map(meetingInfoDto);
        Long id = meetingRepository.save(meeting).getId();

        return new CreateDto(id);
    }

    public void start(Long id) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.MEETING_NOT_FOUND));
        meeting.start();
    }

    public void end(Long id) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        meeting.end();
    }


    public void update(Long meetingId, MeetingInfoDto meetingInfoDto) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEETING_NOT_FOUND));
        meeting.update(meetingInfoDto);
    }

    public void delete(Long meetingId) {
        agendaRepository.findAllByMeetingId(meetingId)
                        .forEach(agenda -> agendaAdminService.delete(agenda.getId()));
        meetingRepository.deleteById(meetingId);
    }
}
