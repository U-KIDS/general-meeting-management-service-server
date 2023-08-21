package io.ukids.generalmeetingmanagementsystem.admin.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberDetailDto {

    private String name;
    private Boolean activate;
    private String college;
    private String major;
    private Integer grade;
    private String studentNumber;

}
