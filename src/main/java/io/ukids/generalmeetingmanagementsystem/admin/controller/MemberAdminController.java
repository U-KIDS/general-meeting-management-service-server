package io.ukids.generalmeetingmanagementsystem.admin.controller;

import io.ukids.generalmeetingmanagementsystem.admin.service.MemberAdminService;
import io.ukids.generalmeetingmanagementsystem.common.dto.ListDto;
import io.ukids.generalmeetingmanagementsystem.common.response.ApiDataResponse;
import io.ukids.generalmeetingmanagementsystem.common.response.ApiResponse;
import io.ukids.generalmeetingmanagementsystem.common.response.HttpStatusCode;
import io.ukids.generalmeetingmanagementsystem.domain.member.Member;
import io.ukids.generalmeetingmanagementsystem.domain.member.MemberSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/member")
@RequiredArgsConstructor
public class MemberAdminController {

    private final MemberAdminService memberAdminService;

    @GetMapping
    public ApiResponse<ListDto> query(
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

        List<Member> members = memberAdminService.query(condition, pageable);

        return ApiDataResponse.of(HttpStatusCode.OK, new ListDto(members));
    }

    @PatchMapping("/{studentNumber}")
    public ApiResponse<String> permit(@PathVariable String studentNumber) {
        memberAdminService.permit(studentNumber);
        return ApiResponse.of(HttpStatusCode.OK, "정상적으로 허락되었습니다.");
    }

}
