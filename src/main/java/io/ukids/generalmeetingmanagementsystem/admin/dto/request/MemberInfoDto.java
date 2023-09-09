package io.ukids.generalmeetingmanagementsystem.admin.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberInfoDto {
    private String name;
    private String studentName;
    private Integer grade;
    private String college;
    private String major;
}
