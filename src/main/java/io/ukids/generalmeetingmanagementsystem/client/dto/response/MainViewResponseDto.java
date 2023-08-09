package io.ukids.generalmeetingmanagementsystem.client.dto.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
@Builder
public class MainViewResponseDto {

    private MeetingClientDto meetingClientDto;
    private List<AgendaClientResponseDto> agendas;

}
