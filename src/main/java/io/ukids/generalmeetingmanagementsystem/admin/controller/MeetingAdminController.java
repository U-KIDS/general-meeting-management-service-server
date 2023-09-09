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
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/meeting")
@CrossOrigin
@RequiredArgsConstructor
public class MeetingAdminController {

    private final MeetingAdminService meetingAdminService;
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
        CreateDto createDto = meetingAdminService.create(meetingInfoDto);
        return ApiDataResponse.of(HttpStatusCode.CREATED, createDto);
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Long id) {
        meetingAdminService.delete(id);
        return ApiResponse.of(HttpStatusCode.OK, "회의가 정상적으로 삭제되었습니다.");
    }

    @GetMapping("/{id}/update")
    public ApiDataResponse<MeetingInfoDto> findOneUpdateValue(@PathVariable Long id) {
        MeetingInfoDto meeting = meetingQueryAdminService.findOneUpdateValue(id);
        return ApiDataResponse.of(HttpStatusCode.OK, meeting);
    }

    @PatchMapping("/{meetingId}/activate")
    public ApiResponse start(@PathVariable Long meetingId) {
        meetingAdminService.activate(meetingId);
        return ApiResponse.of(HttpStatusCode.OK);
    }

    @PatchMapping("/{meetingId}/deactivate")
    public ApiResponse end(@PathVariable Long meetingId) {
        meetingAdminService.deactivate(meetingId);
        return ApiResponse.of(HttpStatusCode.OK);
    }

    @PatchMapping("/{meetingId}")
    public ApiResponse update(
            @PathVariable Long meetingId,
            @RequestBody MeetingInfoDto meetingInfoDto ) {
        meetingAdminService.update(meetingId, meetingInfoDto);
        return ApiResponse.of(HttpStatusCode.OK, "회의가 정상적으로 수정되었습니다.");
    }

}
