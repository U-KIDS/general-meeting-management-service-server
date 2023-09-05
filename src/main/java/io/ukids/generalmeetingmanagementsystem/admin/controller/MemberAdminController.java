package io.ukids.generalmeetingmanagementsystem.admin.controller;

import io.ukids.generalmeetingmanagementsystem.admin.dto.request.MemberInfoDto;
import io.ukids.generalmeetingmanagementsystem.admin.dto.response.MemberDetailDto;
import io.ukids.generalmeetingmanagementsystem.admin.dto.response.MemberListDto;
import io.ukids.generalmeetingmanagementsystem.admin.service.member.MemberAdminService;
import io.ukids.generalmeetingmanagementsystem.admin.service.member.MemberQueryAdminService;
import io.ukids.generalmeetingmanagementsystem.common.dto.ListDto;
import io.ukids.generalmeetingmanagementsystem.common.response.ApiDataResponse;
import io.ukids.generalmeetingmanagementsystem.common.response.ApiResponse;
import io.ukids.generalmeetingmanagementsystem.common.response.HttpStatusCode;
import io.ukids.generalmeetingmanagementsystem.domain.member.MemberSearchCondition;
import io.ukids.generalmeetingmanagementsystem.domain.member.enums.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/member")
@CrossOrigin
@RequiredArgsConstructor
public class MemberAdminController {

    private final MemberAdminService memberAdminService;
    private final MemberQueryAdminService memberQueryAdminService;

    @GetMapping
    public ApiDataResponse<ListDto<MemberListDto>> query(
                @RequestParam(required = false) String college,
                @RequestParam(required = false) String major,
                @RequestParam(required = false) String name,
                @RequestParam(required = false) Boolean activate,
                Pageable pageable) {

        MemberSearchCondition condition = MemberSearchCondition.builder()
                .college(college)
                .major(major)
                .name(name)
                .activate(activate)
                .authority(Authority.ROLE_USER)
                .build();
        ListDto<MemberListDto> members = memberQueryAdminService.query(condition, pageable);

        return ApiDataResponse.of(HttpStatusCode.OK, members);
    }

    @GetMapping("/{studentNumber}")
    public ApiDataResponse<MemberDetailDto> findOne(@PathVariable String studentNumber) {
        MemberDetailDto member = memberQueryAdminService.findOnd(studentNumber);

        return ApiDataResponse.of(HttpStatusCode.OK, member);
    }

    @PatchMapping("/{studentNumber}")
    public ApiResponse update(
            @PathVariable String studentNumber,
            @RequestBody MemberInfoDto memberInfoDto) {
        memberAdminService.update(studentNumber, memberInfoDto);
        return ApiResponse.of(HttpStatusCode.OK, "유저 정보가 정상적으로 수정되었습니다.");
    }

    @DeleteMapping("/{studentNumber}")
    public ApiResponse delete(@PathVariable String studentNumber) {
        memberAdminService.delete(studentNumber);
        return ApiResponse.of(HttpStatusCode.OK, "유저가 삭제되었습니다.");
    }

    @PatchMapping("/{studentNumber}/permit")
    public ApiResponse permit(@PathVariable String studentNumber) {
        memberAdminService.permit(studentNumber);
        return ApiResponse.of(HttpStatusCode.OK, "정상적으로 승인되었습니다.");
    }

    @PatchMapping("/{studentNumber}/block")
    public ApiResponse block(@PathVariable String studentNumber) {
        memberAdminService.block(studentNumber);
        return ApiResponse.of(HttpStatusCode.OK, "정상적으로 블락되었습니다.");
    }

}
