package io.ukids.generalmeetingmanagementsystem.auth.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDto {

    private String token;

}
