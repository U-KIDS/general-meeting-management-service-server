package io.ukids.generalmeetingmanagementsystem.admin.service.agenda;

import io.ukids.generalmeetingmanagementsystem.admin.dto.request.AgendaInfoDto;
import io.ukids.generalmeetingmanagementsystem.common.dto.CreateDto;
import io.ukids.generalmeetingmanagementsystem.common.exception.BaseException;
import io.ukids.generalmeetingmanagementsystem.common.exception.ErrorCode;
import io.ukids.generalmeetingmanagementsystem.common.mapper.AgendaMapper;
import io.ukids.generalmeetingmanagementsystem.domain.agenda.Agenda;
import io.ukids.generalmeetingmanagementsystem.domain.agenda.AgendaRepository;
import io.ukids.generalmeetingmanagementsystem.domain.meeting.Meeting;
import io.ukids.generalmeetingmanagementsystem.domain.meeting.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AgendaAdminService {

    private final AgendaRepository agendaRepository;
    private final AgendaMapper agendaMapper;
    private final MeetingRepository meetingRepository;

    public CreateDto create(AgendaInfoDto agendaInfoDto, Long meetingId) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEETING_NOT_FOUND));
        Agenda agenda = agendaMapper.map(agendaInfoDto, meeting);
        Long id = agendaRepository.save(agenda).getId();

        return new CreateDto(id);
    }

    public void delete(Long agendaId) {
        agendaRepository.deleteById(agendaId);
    }

    public void update(Long agendaId, AgendaInfoDto agendaInfoDto) {
        Agenda agenda = agendaRepository.findById(agendaId)
                .orElseThrow(() -> new BaseException(ErrorCode.AGENDA_NOT_FOUND));
        agenda.update(agendaInfoDto);
    }
}
