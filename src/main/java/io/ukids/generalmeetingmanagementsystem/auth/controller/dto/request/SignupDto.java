package io.ukids.generalmeetingmanagementsystem.auth.controller.dto.request;

import io.ukids.generalmeetingmanagementsystem.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignupDto {

    @NotNull
    private String studentNumber;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @NotNull
    private String college;

    @NotNull
    private String major;

    @NotNull
    private Integer grade;

    @NotNull
    private String imageUrl;

    @Builder
    public SignupDto(String studentNumber, String password, String name, String college, String major, Integer grade, String imageUrl) {
        this.studentNumber = studentNumber;
        this.password = password;
        this.name = name;
        this.college = college;
        this.major = major;
        this.grade = grade;
        this.imageUrl = imageUrl;
    }
}
