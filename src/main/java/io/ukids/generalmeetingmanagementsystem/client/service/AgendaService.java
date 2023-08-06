package io.ukids.generalmeetingmanagementsystem.client.service;

import io.ukids.generalmeetingmanagementsystem.client.dto.response.AgendaResponseDto;
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
public class AgendaService {

    private final AgendaRepository agendaRepository;
    // 모든 회의 안건 찾기
    public List<AgendaResponseDto> findAllAgenda(String meetingName) {

        List<Agenda> agendas = agendaRepository.findAllByMeeting_Name(meetingName)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 회의가 없습니다."));

        return agendas.stream()
                .map(a-> new AgendaResponseDto(a))
                .collect(Collectors.toList());
    }
}