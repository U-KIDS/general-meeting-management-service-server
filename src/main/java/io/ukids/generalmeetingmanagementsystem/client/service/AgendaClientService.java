package io.ukids.generalmeetingmanagementsystem.client.service;

import io.ukids.generalmeetingmanagementsystem.client.dto.response.AgendaClientResponseDto;
import io.ukids.generalmeetingmanagementsystem.client.dto.response.MainViewResponseDto;
import io.ukids.generalmeetingmanagementsystem.client.dto.response.MeetingClientDto;
import io.ukids.generalmeetingmanagementsystem.common.exception.BaseException;
import io.ukids.generalmeetingmanagementsystem.domain.agenda.Agenda;
import io.ukids.generalmeetingmanagementsystem.domain.agenda.AgendaRepository;
import io.ukids.generalmeetingmanagementsystem.domain.meeting.Meeting;
import io.ukids.generalmeetingmanagementsystem.domain.meeting.MeetingRepository;
import io.ukids.generalmeetingmanagementsystem.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static io.ukids.generalmeetingmanagementsystem.common.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AgendaClientService {

    private final AgendaRepository agendaRepository;
    private final MeetingRepository meetingRepository;

    // 해당하는 회의의 모든 안건 찾기
    public MainViewResponseDto findMeetingAndAllAgenda(Long meetingId) {

        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new BaseException(MEETING_NOT_FOUND));

        List<Agenda> agendas = agendaRepository.findAllByMeetingId(meetingId)
                .orElseThrow(() -> new BaseException(AGENDA_NOT_FOUND));

        List<AgendaClientResponseDto> agendaClientResponseDtos = agendas.stream()
                .map(a-> new AgendaClientResponseDto(a))
                .collect(Collectors.toList());

        return MainViewResponseDto.builder()
                .meetingClientDto(new MeetingClientDto(meeting))
                .agendas(agendaClientResponseDtos)
                .build();
    }
}