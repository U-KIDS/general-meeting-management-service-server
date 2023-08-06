package io.ukids.generalmeetingmanagementsystem.client.controller;

import io.ukids.generalmeetingmanagementsystem.client.dto.response.AgendaResponseDto;
import io.ukids.generalmeetingmanagementsystem.client.service.AgendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client")
public class ClientController {

    private final AgendaService agendaService;

    // 모든 안건 불러오기
    @GetMapping(value = "/{meetingName}")
    public ResponseEntity<List<AgendaResponseDto>> findAllAgenda(@PathVariable String meetingName) {
        return ResponseEntity.ok().body(agendaService.findAllAgenda(meetingName));
    }
}
