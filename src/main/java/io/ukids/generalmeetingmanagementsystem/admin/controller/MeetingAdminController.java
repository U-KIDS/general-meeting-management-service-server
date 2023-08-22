package io.ukids.generalmeetingmanagementsystem.admin.controller;

import io.ukids.generalmeetingmanagementsystem.admin.dto.response.MeetingDetailDto;
import io.ukids.generalmeetingmanagementsystem.admin.dto.response.MeetingInfoDto;
import io.ukids.generalmeetingmanagementsystem.admin.dto.response.MeetingListDto;
import io.ukids.generalmeetingmanagementsystem.admin.service.meeting.MeetingAdminService;
import io.ukids.generalmeetingmanagementsystem.admin.service.meeting.MeetingQueryAdminService;
import io.ukids.generalmeetingmanagementsystem.common.dto.CreateDto;
import io.ukids.generalmeetingmanagementsystem.common.dto.ListDto;
import io.ukids.generalmeetingmanagementsystem.common.response.ApiDataResponse;
import io.ukids.generalmeetingmanagementsystem.common.response.ApiResponse;
import io.ukids.generalmeetingmanagementsystem.common.response.HttpStatusCode;
import io.ukids.generalmeetingmanagementsystem.domain.meeting.Meeting;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/meeting")
@RequiredArgsConstructor
public class MeetingAdminController {

    private final MeetingAdminService meetingService;
    private final MeetingQueryAdminService meetingQueryAdminService;

    @GetMapping
    public ApiDataResponse<ListDto<MeetingListDto>> query() {
        ListDto<MeetingListDto> meetings = meetingQueryAdminService.query();
        return ApiDataResponse.of(HttpStatusCode.OK, meetings);
    }

    @GetMapping("/{id}")
    public ApiDataResponse<MeetingDetailDto> findOne(@PathVariable Long id) {
        MeetingDetailDto meeting = meetingQueryAdminService.findOne(id);
        return ApiDataResponse.of(HttpStatusCode.OK, meeting);
    }

    @PostMapping
    public ApiDataResponse<CreateDto> create(@RequestBody MeetingInfoDto meetingInfoDto) {
        CreateDto createDto = meetingService.create(meetingInfoDto);
        return ApiDataResponse.of(HttpStatusCode.CREATED, createDto);
    }

    @PatchMapping("/{meetingId}/start")
    public ApiResponse start(@PathVariable Long meetingId) {
        meetingService.start(meetingId);
        return ApiResponse.of(HttpStatusCode.OK);
    }

    @PatchMapping("{meetingId}/end")
    public ApiResponse end(@PathVariable Long meetingId) {
        meetingService.end(meetingId);
        return ApiResponse.of(HttpStatusCode.OK);
    }
}
