package io.ukids.generalmeetingmanagementsystem.client.controller;

import io.ukids.generalmeetingmanagementsystem.client.dto.request.VoteRequestDto;
import io.ukids.generalmeetingmanagementsystem.client.service.VoteService;
import io.ukids.generalmeetingmanagementsystem.common.dto.CreateDto;
import io.ukids.generalmeetingmanagementsystem.common.response.ApiDataResponse;
import io.ukids.generalmeetingmanagementsystem.common.response.ApiResponse;
import io.ukids.generalmeetingmanagementsystem.common.response.HttpStatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client")
public class VoteController {
    private final VoteService voteService;

    // 투표하기
    @PostMapping(value = "/vote")
    public ApiResponse onClickVote(@RequestBody VoteRequestDto voteRequestDto) {
        CreateDto voteCreateDto = voteService.onClickVote(voteRequestDto);
        return ApiDataResponse.of(HttpStatusCode.OK, voteCreateDto, "투표를 완료했습니다.");
    }
}