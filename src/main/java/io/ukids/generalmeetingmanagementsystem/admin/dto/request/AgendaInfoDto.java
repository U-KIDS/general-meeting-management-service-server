package io.ukids.generalmeetingmanagementsystem.admin.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AgendaInfoDto {
    private String title;
}
