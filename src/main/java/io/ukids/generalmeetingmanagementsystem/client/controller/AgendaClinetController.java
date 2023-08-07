package io.ukids.generalmeetingmanagementsystem.client.controller;

import io.ukids.generalmeetingmanagementsystem.client.dto.response.AgendaClientResponseDto;
import io.ukids.generalmeetingmanagementsystem.client.service.AgendaClientService;
import io.ukids.generalmeetingmanagementsystem.common.response.ApiDataResponse;
import io.ukids.generalmeetingmanagementsystem.common.response.HttpStatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client")
public class AgendaClinetController {

    private final AgendaClientService agendaClientService;

    // 모든 안건 불러오기
    @GetMapping(value = "/{meetingName}")
    public ApiDataResponse<List<AgendaClientResponseDto>> findAllAgenda(@PathVariable String meetingName) {
        List<AgendaClientResponseDto> agendas = agendaClientService.findAllAgenda(meetingName);
        return ApiDataResponse.of(HttpStatusCode.OK, agendas);
    }

}