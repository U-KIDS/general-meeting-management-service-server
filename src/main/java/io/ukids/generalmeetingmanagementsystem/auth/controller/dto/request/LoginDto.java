package io.ukids.generalmeetingmanagementsystem.auth.controller.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginDto {

    @NotNull
    private String studentNumber;

    @NotNull
    private String password;

    @Builder
    public LoginDto(String studentNumber, String password) {
        this.studentNumber = studentNumber;
        this.password = password;
    }

}
