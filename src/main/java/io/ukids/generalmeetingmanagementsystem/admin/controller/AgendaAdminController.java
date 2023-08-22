package io.ukids.generalmeetingmanagementsystem.admin.controller;

import io.ukids.generalmeetingmanagementsystem.admin.dto.request.AgendaCreateDto;
import io.ukids.generalmeetingmanagementsystem.admin.service.agenda.AgendaAdminService;
import io.ukids.generalmeetingmanagementsystem.common.dto.CreateDto;
import io.ukids.generalmeetingmanagementsystem.common.response.ApiDataResponse;
import io.ukids.generalmeetingmanagementsystem.common.response.HttpStatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/agenda")
@RequiredArgsConstructor
public class AgendaAdminController {

    private final AgendaAdminService agendaAdminService;

    @PostMapping("/{meetingId}")
    public ApiDataResponse create(
            @RequestBody AgendaCreateDto agendaCreateDto,
            @PathVariable Long meetingId) {

        CreateDto createDto = agendaAdminService.create(agendaCreateDto, meetingId);
        return ApiDataResponse.of(HttpStatusCode.CREATED, createDto);
    }

}
