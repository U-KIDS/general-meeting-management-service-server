package io.ukids.generalmeetingmanagementsystem.client.service;

import io.ukids.generalmeetingmanagementsystem.client.dto.response.AgendaClientResponseDto;
import io.ukids.generalmeetingmanagementsystem.domain.agenda.Agenda;
import io.ukids.generalmeetingmanagementsystem.domain.agenda.AgendaRepository;
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

    // 해당하는 회의의 모든 안건 찾기
    public List<AgendaClientResponseDto> findAllAgenda(Long meetingId) {
        List<Agenda> agendas = agendaRepository.findAllByMeetingId(meetingId);

        List<AgendaClientResponseDto> agendaClientResponseDtos = agendas.stream().map(a -> new AgendaClientResponseDto(a)).collect(Collectors.toList());

        return agendaClientResponseDtos;
    }
}