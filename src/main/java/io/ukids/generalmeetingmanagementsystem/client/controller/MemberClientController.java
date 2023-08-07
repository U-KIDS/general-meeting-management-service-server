package io.ukids.generalmeetingmanagementsystem.client.controller;

import io.ukids.generalmeetingmanagementsystem.client.dto.response.MemberDetailClientResponseDto;
import io.ukids.generalmeetingmanagementsystem.client.service.MemberClientService;
import io.ukids.generalmeetingmanagementsystem.common.response.ApiDataResponse;
import io.ukids.generalmeetingmanagementsystem.common.response.HttpStatusCode;
import io.ukids.generalmeetingmanagementsystem.common.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class MemberClientController {

    private final MemberClientService memberClientService;

    // 로그인 유저 정보와 참여 가능 회의 목록 조회
    @GetMapping(value = "/main")
    public ApiDataResponse<MemberDetailClientResponseDto> findMemberDetail() {
        String studentNumber = SecurityUtil.getCurrentStudentNumber();
        MemberDetailClientResponseDto memberDetail = memberClientService.findMemberDetail(studentNumber);
        return ApiDataResponse.of(HttpStatusCode.OK, memberDetail);
    }

}
