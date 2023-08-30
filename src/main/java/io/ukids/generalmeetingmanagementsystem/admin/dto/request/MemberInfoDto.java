package io.ukids.generalmeetingmanagementsystem.admin.dto.request;

import lombok.Data;

@Data
public class MemberInfoDto {
    private final String name;
    private final String studentName;
    private final Integer grade;
    private final String college;
    private final String major;
}
