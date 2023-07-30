package io.ukids.generalmeetingmanagementsystem.auth.controller;

import io.ukids.generalmeetingmanagementsystem.auth.dto.request.AdminLoginDto;
import io.ukids.generalmeetingmanagementsystem.auth.dto.request.UserLoginDto;
import io.ukids.generalmeetingmanagementsystem.auth.dto.response.TokenDto;
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

    @PostMapping(value = "/login/user")
    public ResponseEntity userLogin(@Valid @RequestBody UserLoginDto userLoginDto) {

        String jwt = authService.login(userLoginDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        TokenDto tokenDto = TokenDto.builder()
                .token(jwt)
                .build();

        return ResponseEntity.ok().headers(httpHeaders).body(tokenDto);
    }

    @PostMapping(value = "/login/admin")
    public ResponseEntity adminLogin(@Valid @RequestBody AdminLoginDto adminLoginDto) {

        String jwt = authService.login(adminLoginDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        TokenDto tokenDto = TokenDto.builder()
                .token(jwt)
                .build();

        return ResponseEntity.ok().headers(httpHeaders).body(tokenDto);
    }

}
