package io.ukids.generalmeetingmanagementsystem.domain.member;

import io.ukids.generalmeetingmanagementsystem.domain.member.enums.Authority;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSearchCondition {

    private String college;

    private String major;

    private String name;

    private Boolean activate;

    private Authority authority;

    @Builder
    public MemberSearchCondition(String college, String major, String name, Boolean activate, Authority authority) {
        this.college = college;
        this.major = major;
        this.name = name;
        this.activate = activate;
        this.authority = authority;
    }

}
