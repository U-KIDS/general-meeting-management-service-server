package io.ukids.generalmeetingmanagementsystem.auth.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminLoginDto {

    @NotNull
    private String studentNumber;

    @NotNull
    private String password;

    @Builder
    public AdminLoginDto(String studentNumber, String password) {
        this.studentNumber = studentNumber;
        this.password = password;
    }

}
