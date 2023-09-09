package io.ukids.generalmeetingmanagementsystem.client.controller;

import io.ukids.generalmeetingmanagementsystem.client.dto.request.VoteClientRequestDto;
import io.ukids.generalmeetingmanagementsystem.client.service.VoteClientService;
import io.ukids.generalmeetingmanagementsystem.common.dto.CreateDto;
import io.ukids.generalmeetingmanagementsystem.common.response.ApiDataResponse;
import io.ukids.generalmeetingmanagementsystem.common.response.ApiResponse;
import io.ukids.generalmeetingmanagementsystem.common.response.HttpStatusCode;
import io.ukids.generalmeetingmanagementsystem.common.util.SecurityUtil;
import io.ukids.generalmeetingmanagementsystem.domain.agenda.enums.AgendaResult;
import io.ukids.generalmeetingmanagementsystem.domain.vote.enums.VoteValue;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client")
public class VoteClientController {
    private final VoteClientService voteClientService;

    // 투표하기
    @PostMapping(value = "/vote")
    public ApiResponse onClickVote(@RequestBody VoteClientRequestDto voteClientRequestDto) {
        CreateDto voteCreateDto = voteClientService.onClickVote(voteClientRequestDto);
        return ApiDataResponse.of(HttpStatusCode.OK, voteCreateDto, "투표를 완료했습니다.");
    }

    @PostMapping(value = "/agenda/{agendaId}/vote")
    public ApiResponse vote(
            @PathVariable Long agendaId,
            @RequestParam VoteValue voteValue) {
        String studentNumber = SecurityUtil.getCurrentStudentNumber();
        voteClientService.vote(agendaId, studentNumber, voteValue);

        return ApiResponse.of(HttpStatusCode.OK, "투표를 완료하였습니다.");
    }
}