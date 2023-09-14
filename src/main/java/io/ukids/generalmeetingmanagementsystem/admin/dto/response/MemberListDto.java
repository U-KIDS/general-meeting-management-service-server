package io.ukids.generalmeetingmanagementsystem.admin.dto.response;

import io.ukids.generalmeetingmanagementsystem.domain.member.Member;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberListDto {

    private String studentNumber;
    private String name;
    private String college;
    private String major;
    private Boolean activate;
    private Integer grade;

}
