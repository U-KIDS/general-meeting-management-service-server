package io.ukids.generalmeetingmanagementsystem.admin.controller;

import io.ukids.generalmeetingmanagementsystem.admin.dto.response.MeetingInfoDto;
import io.ukids.generalmeetingmanagementsystem.admin.dto.response.MeetingListDto;
import io.ukids.generalmeetingmanagementsystem.admin.service.MeetingAdminService;
import io.ukids.generalmeetingmanagementsystem.common.dto.CreateDto;
import io.ukids.generalmeetingmanagementsystem.common.dto.ListDto;
import io.ukids.generalmeetingmanagementsystem.common.response.ApiDataResponse;
import io.ukids.generalmeetingmanagementsystem.common.response.ApiResponse;
import io.ukids.generalmeetingmanagementsystem.common.response.HttpStatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/meeting")
@RequiredArgsConstructor
public class MeetingAdminController {

    private final MeetingAdminService meetingService;

    @GetMapping
    public ApiDataResponse<ListDto<MeetingListDto>> query() {
        ListDto<MeetingListDto> members = meetingService.query();
        return ApiDataResponse.of(HttpStatusCode.OK, members);
    }

    @PostMapping
    public ApiDataResponse<CreateDto> create(@RequestBody MeetingInfoDto meetingInfoDto) {
        CreateDto meetingCreateDto = meetingService.create(meetingInfoDto);
        return ApiDataResponse.of(HttpStatusCode.OK, meetingCreateDto);
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
