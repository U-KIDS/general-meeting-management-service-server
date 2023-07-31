package io.ukids.generalmeetingmanagementsystem.auth.controller;

import io.ukids.generalmeetingmanagementsystem.auth.controller.dto.request.LoginDto;
import io.ukids.generalmeetingmanagementsystem.auth.controller.dto.request.SignupDto;
import io.ukids.generalmeetingmanagementsystem.auth.controller.dto.response.CreateDto;
import io.ukids.generalmeetingmanagementsystem.auth.controller.dto.response.TokenDto;
import io.ukids.generalmeetingmanagementsystem.auth.jwt.JwtFilter;
import io.ukids.generalmeetingmanagementsystem.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginDto loginDto) {

        String jwt = authService.login(loginDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        TokenDto tokenDto = TokenDto.builder()
                .token(jwt)
                .build();

        return ResponseEntity.ok().headers(httpHeaders).body(tokenDto);
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<CreateDto> signup(@Valid @RequestBody SignupDto signupDto) {
        Long memberId = authService.signup(signupDto);
        CreateDto createDto = CreateDto.builder()
                .id(memberId)
                .build();
        return ResponseEntity.ok().body(createDto);
    }
}
