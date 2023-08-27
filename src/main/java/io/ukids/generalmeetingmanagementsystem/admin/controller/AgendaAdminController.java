package io.ukids.generalmeetingmanagementsystem.admin.controller;

import io.ukids.generalmeetingmanagementsystem.admin.dto.request.AgendaInfoDto;
import io.ukids.generalmeetingmanagementsystem.admin.dto.response.AgendaDetailDto;
import io.ukids.generalmeetingmanagementsystem.admin.dto.response.VoteListDto;
import io.ukids.generalmeetingmanagementsystem.admin.service.agenda.AgendaAdminService;
import io.ukids.generalmeetingmanagementsystem.admin.service.agenda.AgendaQueryAdminService;
import io.ukids.generalmeetingmanagementsystem.common.dto.CreateDto;
import io.ukids.generalmeetingmanagementsystem.common.response.ApiDataResponse;
import io.ukids.generalmeetingmanagementsystem.common.response.ApiResponse;
import io.ukids.generalmeetingmanagementsystem.common.response.HttpStatusCode;
import io.ukids.generalmeetingmanagementsystem.domain.vote.VoteSearchCondition;
import io.ukids.generalmeetingmanagementsystem.domain.vote.enums.VoteValue;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/agenda")
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
            @RequestParam VoteValue voteValue,
            @RequestParam String name,
            Pageable pageable) {

        VoteSearchCondition condition = VoteSearchCondition.builder()
                .voteValue(voteValue)
                .name(name)
                .build();
        VoteListDto voteListDto = agendaQueryAdminService.queryVote(agendaId, condition, pageable);
        return ApiDataResponse.of(HttpStatusCode.OK, voteListDto);
    }

    @PostMapping("/meeting/{meetingId}")
    public ApiDataResponse<CreateDto> create(
            @RequestBody AgendaInfoDto agendaInfoDto,
            @PathVariable Long meetingId) {

        CreateDto createDto = agendaAdminService.create(agendaInfoDto, meetingId);
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

}
