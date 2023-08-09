package io.ukids.generalmeetingmanagementsystem.client.controller;

import io.ukids.generalmeetingmanagementsystem.client.dto.response.AgendaClientResponseDto;
import io.ukids.generalmeetingmanagementsystem.client.dto.response.MainViewResponseDto;
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

    // 해당하는 회의의 모든 안건 찾기
    @GetMapping(value = "/{meetingId}")
    public ApiDataResponse<MainViewResponseDto> findMeetingAndAllAgenda(@PathVariable Long meetingId) {
        MainViewResponseDto mainViewResponseDto = agendaClientService.findMeetingAndAllAgenda(meetingId);
        return ApiDataResponse.of(HttpStatusCode.OK, mainViewResponseDto);
    }
}