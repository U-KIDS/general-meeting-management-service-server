package io.ukids.generalmeetingmanagementsystem.auth.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserLoginDto {

    @NotNull
    private String studentNumber;

    @NotNull
    private String name;

    private String password;

    @Builder
    public UserLoginDto(String studentNumber, String name) {
        this.studentNumber = studentNumber;
        this.name = name;
        this.password = "password";
    }

}
