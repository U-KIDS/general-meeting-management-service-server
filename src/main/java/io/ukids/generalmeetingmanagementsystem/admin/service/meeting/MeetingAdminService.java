package io.ukids.generalmeetingmanagementsystem.admin.service.meeting;

import io.ukids.generalmeetingmanagementsystem.admin.dto.response.MeetingInfoDto;
import io.ukids.generalmeetingmanagementsystem.common.dto.CreateDto;
import io.ukids.generalmeetingmanagementsystem.common.exception.BaseException;
import io.ukids.generalmeetingmanagementsystem.common.exception.ErrorCode;
import io.ukids.generalmeetingmanagementsystem.common.mapper.MeetingMapper;
import io.ukids.generalmeetingmanagementsystem.domain.agenda.Agenda;
import io.ukids.generalmeetingmanagementsystem.domain.agenda.AgendaRepository;
import io.ukids.generalmeetingmanagementsystem.domain.agendaimage.AgendaImageRepository;
import io.ukids.generalmeetingmanagementsystem.domain.meeting.Meeting;
import io.ukids.generalmeetingmanagementsystem.domain.meeting.MeetingRepository;
import io.ukids.generalmeetingmanagementsystem.domain.vote.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MeetingAdminService {

    private final MeetingRepository meetingRepository;
    private final AgendaRepository agendaRepository;
    private final AgendaImageRepository agendaImageRepository;
    private final VoteRepository voteRepository;
    private final MeetingMapper meetingMapper;

    public CreateDto create(MeetingInfoDto meetingInfoDto) {
        Meeting meeting = meetingMapper.map(meetingInfoDto);
        Long id = meetingRepository.save(meeting).getId();

        return new CreateDto(id);
    }

    public void activate(Long id) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.MEETING_NOT_FOUND));
        meeting.activate();
    }

    public void deactivate(Long id) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        meeting.deactivate();
    }


    public void update(Long meetingId, MeetingInfoDto meetingInfoDto) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEETING_NOT_FOUND));
        meeting.update(meetingInfoDto);
    }

    public void delete(Long meetingId) {
        System.out.println("asdasdasd");
        agendaRepository.findAllByMeetingId(meetingId)
                        .forEach(agenda -> deleteAgenda(agenda));
        meetingRepository.deleteById(meetingId);
    }

    private void deleteAgenda(Agenda agenda) {
        agendaImageRepository.deleteAllByAgenda(agenda);
        voteRepository.deleteAllByAgenda(agenda);
        agendaRepository.delete(agenda);
    }


}
