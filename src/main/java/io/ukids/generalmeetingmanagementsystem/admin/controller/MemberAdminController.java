package io.ukids.generalmeetingmanagementsystem.admin.controller;

import io.ukids.generalmeetingmanagementsystem.admin.dto.response.MemberDetailDto;
import io.ukids.generalmeetingmanagementsystem.admin.dto.response.MemberListDto;
import io.ukids.generalmeetingmanagementsystem.admin.service.member.MemberAdminService;
import io.ukids.generalmeetingmanagementsystem.admin.service.member.MemberQueryAdminService;
import io.ukids.generalmeetingmanagementsystem.common.dto.ListDto;
import io.ukids.generalmeetingmanagementsystem.common.response.ApiDataResponse;
import io.ukids.generalmeetingmanagementsystem.common.response.ApiResponse;
import io.ukids.generalmeetingmanagementsystem.common.response.HttpStatusCode;
import io.ukids.generalmeetingmanagementsystem.domain.member.MemberSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/member")
@RequiredArgsConstructor
public class MemberAdminController {

    private final MemberAdminService memberService;
    private final MemberQueryAdminService memberQueryAdminService;

    @GetMapping
    public ApiDataResponse<ListDto<MemberListDto>> query(
                @RequestParam String college,
                @RequestParam String major,
                @RequestParam String name,
                @RequestParam Boolean activate,
                Pageable pageable) {

        MemberSearchCondition condition = MemberSearchCondition.builder()
                .college(college)
                .major(major)
                .name(name)
                .activate(activate)
                .build();

        ListDto<MemberListDto> members = memberQueryAdminService.query(condition, pageable);

        return ApiDataResponse.of(HttpStatusCode.OK, members);
    }

    @GetMapping("/{studentNumber}")
    public ApiDataResponse<MemberDetailDto> findOne(@PathVariable String studentNumber) {
        MemberDetailDto member = memberQueryAdminService.findOnd(studentNumber);

        return ApiDataResponse.of(HttpStatusCode.OK, member);
    }

    @DeleteMapping("/{studentNumber}")
    public ApiResponse delete(@PathVariable String studentNumber) {
        return ApiResponse.of(HttpStatusCode.OK);
    }

    @PatchMapping("/{studentNumber}/permit")
    public ApiResponse permit(@PathVariable String studentNumber) {
        memberService.permit(studentNumber);
        return ApiResponse.of(HttpStatusCode.OK, "정상적으로 승인되었습니다.");
    }

    @PatchMapping("/{studentNumber}/block")
    public ApiResponse block(@PathVariable String studentNumber) {
        memberService.block(studentNumber);
        return ApiResponse.of(HttpStatusCode.OK, "정상적으로 블락되었습니다.");
    }

}
