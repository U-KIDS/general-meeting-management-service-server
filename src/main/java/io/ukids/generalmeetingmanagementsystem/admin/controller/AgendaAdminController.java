package io.ukids.generalmeetingmanagementsystem.admin.controller;

import io.ukids.generalmeetingmanagementsystem.admin.dto.request.AgendaInfoDto;
import io.ukids.generalmeetingmanagementsystem.admin.dto.response.AgendaDetailDto;
import io.ukids.generalmeetingmanagementsystem.admin.dto.response.MeetingDetailDto;
import io.ukids.generalmeetingmanagementsystem.admin.dto.response.VoteListDto;
import io.ukids.generalmeetingmanagementsystem.admin.dto.response.VoteOverviewDto;
import io.ukids.generalmeetingmanagementsystem.admin.service.agenda.AgendaAdminService;
import io.ukids.generalmeetingmanagementsystem.admin.service.agenda.AgendaQueryAdminService;
import io.ukids.generalmeetingmanagementsystem.common.dto.CreateDto;
import io.ukids.generalmeetingmanagementsystem.common.dto.ListDto;
import io.ukids.generalmeetingmanagementsystem.common.response.ApiDataResponse;
import io.ukids.generalmeetingmanagementsystem.common.response.ApiResponse;
import io.ukids.generalmeetingmanagementsystem.common.response.HttpStatusCode;
import io.ukids.generalmeetingmanagementsystem.domain.agenda.enums.AgendaResult;
import io.ukids.generalmeetingmanagementsystem.domain.agenda.enums.AgendaStatus;
import io.ukids.generalmeetingmanagementsystem.domain.vote.VoteSearchCondition;
import io.ukids.generalmeetingmanagementsystem.domain.vote.enums.VoteValue;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/admin/agenda")
@CrossOrigin
@RequiredArgsConstructor
public class AgendaAdminController {

    private final AgendaAdminService agendaAdminService;
    private final AgendaQueryAdminService agendaQueryAdminService;

    @GetMapping("/{agendaId}")
    public ApiDataResponse<AgendaDetailDto> findOne(
            @PathVariable Long agendaId){

        AgendaDetailDto agendaDetailDto = agendaQueryAdminService.findOne(agendaId);
        return ApiDataResponse.of(HttpStatusCode.OK, agendaDetailDto);
    }

    @GetMapping("/{agendaId}/vote")
    public ApiDataResponse<VoteListDto> queryVote(
            @PathVariable Long agendaId,
            @RequestParam(required = false) VoteValue voteValue,
            @RequestParam(required = false) String name,
            Pageable pageable) {

        VoteSearchCondition condition = VoteSearchCondition.builder()
                .voteValue(voteValue)
                .name(name)
                .build();
        VoteListDto voteListDto = agendaQueryAdminService.queryVote(agendaId, condition, pageable);
        return ApiDataResponse.of(HttpStatusCode.OK, voteListDto);
    }

    @PostMapping(value = "/meeting/{meetingId}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ApiDataResponse<CreateDto> create(
            @RequestPart AgendaInfoDto agendaInfoDto,
            @RequestPart(required = false) List<MultipartFile> images,
            @PathVariable Long meetingId) {

        System.out.println(images);
        CreateDto createDto = agendaAdminService.create(agendaInfoDto, meetingId, images);
        return ApiDataResponse.of(HttpStatusCode.CREATED, createDto);
    }

    @DeleteMapping("/{agendaId}")
    public ApiResponse delete(@PathVariable Long agendaId) {
        agendaAdminService.delete(agendaId);
        return ApiResponse.of(HttpStatusCode.OK, "안건이 성공적으로 삭제되었습니다.");
    }

    @PatchMapping("/{agendaId}")
    public ApiResponse update(
            @PathVariable Long agendaId,
            @RequestBody AgendaInfoDto agendaInfoDto) {
        agendaAdminService.update(agendaId, agendaInfoDto);
        return ApiResponse.of(HttpStatusCode.OK, "안건이 성공적으로 수정되었습니다.");
    }

    @PatchMapping("/{agendaId}/start")
    public ApiResponse start(@PathVariable Long agendaId) {
        agendaAdminService.start(agendaId);
        return ApiResponse.of(HttpStatusCode.OK, "투표를 시작합니다.");
    }

    @PatchMapping("/{agendaId}/end")
    public ApiResponse end(
            @PathVariable Long agendaId) {
        agendaAdminService.end(agendaId);
        return ApiResponse.of(HttpStatusCode.OK, "투표를 종료합니다.");
    }

    @PatchMapping("/{agendaId}/resolve")
    public ApiResponse resolve(
            @PathVariable Long agendaId,
            @RequestParam AgendaResult agendaResult) {
        agendaAdminService.resolve(agendaId, agendaResult);
        return ApiResponse.of(HttpStatusCode.OK);
    }

    @GetMapping("/overview/{meetingId}")
    public ApiDataResponse<ListDto<MeetingDetailDto.AgendaInfoDto>> queryOverview(@PathVariable Long meetingId) {
        ListDto<MeetingDetailDto.AgendaInfoDto> result = agendaQueryAdminService.queryOverview(meetingId);
        return ApiDataResponse.of(HttpStatusCode.OK, result);
    }

    @GetMapping("/overview/{agendaId}/vote")
    public ApiDataResponse<VoteOverviewDto> queryVoteOverview(@PathVariable Long agendaId) {
        VoteOverviewDto result = agendaQueryAdminService.queryVoteOverview(agendaId);
        return ApiDataResponse.of(HttpStatusCode.OK, result);
    }
}
