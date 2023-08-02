package io.ukids.generalmeetingmanagementsystem.domain.member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSearchCondition {

    private String college;

    private String major;

    private Boolean activate;

    @Builder
    public MemberSearchCondition(String college, String major, Boolean activate) {
        this.college = college;
        this.major = major;
        this.activate = activate;
    }

}
