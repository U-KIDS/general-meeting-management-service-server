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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/login")
    public ApiDataResponse<TokenDto> login(@Valid @RequestBody LoginDto loginDto) {
        System.out.println("nginx good");
        TokenDto tokenDto = authService.login(loginDto);
        return ApiDataResponse.of(HttpStatusCode.OK, tokenDto);
    }

    @PostMapping(value = "/signup")
    public ApiDataResponse<CreateDto> signup(@Valid @RequestBody SignupDto signupDto) {
        CreateDto memberCreateDto = authService.signup(signupDto);
        return ApiDataResponse.of(HttpStatusCode.CREATED, memberCreateDto);
    }
}
