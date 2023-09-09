package io.ukids.generalmeetingmanagementsystem.client.service;

import io.ukids.generalmeetingmanagementsystem.admin.dto.response.MeetingDetailDto;
import io.ukids.generalmeetingmanagementsystem.client.dto.response.AgendaClientResponseDto;
import io.ukids.generalmeetingmanagementsystem.client.dto.response.AgendaInfoDto;
import io.ukids.generalmeetingmanagementsystem.common.exception.BaseException;
import io.ukids.generalmeetingmanagementsystem.common.exception.ErrorCode;
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
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AgendaClientService {

    private final AgendaRepository agendaRepository;
    private final MeetingRepository meetingRepository;

    // 해당하는 회의의 모든 안건 찾기
    public AgendaClientResponseDto findAllAgenda(Long meetingId) {

        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEETING_NOT_FOUND));
        List<Agenda> agendas = agendaRepository.findAllByMeetingId(meetingId);
        List<AgendaClientResponseDto.AgendaInfoDto> agendasInfoDtos = agendas.stream()
                .map(agenda -> new AgendaClientResponseDto.AgendaInfoDto(agenda))
                .collect(Collectors.toList());

        AgendaClientResponseDto agendaClientResponseDto = AgendaClientResponseDto.builder()
                .meetingTitle(meeting.getName())
                .meetingDate(meeting.getMeetingDate())
                .agendas(agendasInfoDtos)
                .build();

        return agendaClientResponseDto;
    }

    public AgendaInfoDto findOne(Long agendaId) {
        Agenda agenda = agendaRepository.findById(agendaId)
                .orElseThrow(() -> new BaseException(ErrorCode.AGENDA_NOT_FOUND));
        return new AgendaInfoDto(agenda);
    }
}