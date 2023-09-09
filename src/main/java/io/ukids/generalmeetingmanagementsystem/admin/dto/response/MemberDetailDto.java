package io.ukids.generalmeetingmanagementsystem.admin.dto.response;

import io.ukids.generalmeetingmanagementsystem.domain.member.Member;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDetailDto {

    private String name;
    private Boolean activate;
    private String college;
    private String major;
    private Integer grade;
    private String studentNumber;
    private String imageUrl;

    public MemberDetailDto(Member member) {
        this.name = member.getName();
        this.activate = member.getActivate();
        this.college = member.getCollege();
        this.major = member.getMajor();
        this.grade = member.getGrade();
        this.studentNumber = member.getStudentNumber();
        this.imageUrl = member.getImageUrl();
    }
}
