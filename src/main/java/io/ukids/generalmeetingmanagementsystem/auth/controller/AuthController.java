package io.ukids.generalmeetingmanagementsystem.auth.controller;

import io.ukids.generalmeetingmanagementsystem.auth.controller.dto.request.LoginDto;
import io.ukids.generalmeetingmanagementsystem.auth.controller.dto.request.SignupDto;
import io.ukids.generalmeetingmanagementsystem.auth.controller.dto.response.TokenDto;
import io.ukids.generalmeetingmanagementsystem.auth.service.AuthService;
import io.ukids.generalmeetingmanagementsystem.common.dto.CreateDto;
import io.ukids.generalmeetingmanagementsystem.common.response.ApiDataResponse;
import io.ukids.generalmeetingmanagementsystem.common.response.ApiResponse;
import io.ukids.generalmeetingmanagementsystem.common.response.HttpStatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/login")
    public ApiResponse<TokenDto> login(@Valid @RequestBody LoginDto loginDto) {

        String jwt = authService.login(loginDto);

        TokenDto tokenDto = TokenDto.builder()
                .token(jwt)
                .build();

        return ApiDataResponse.of(HttpStatusCode.OK, tokenDto);
    }

    @PostMapping(value = "/signup")
    public ApiResponse<CreateDto> signup(@Valid @RequestBody SignupDto signupDto) {
        Long memberId = authService.signup(signupDto);
        return ApiDataResponse.of(HttpStatusCode.CREATED, new CreateDto(memberId));
    }
}
