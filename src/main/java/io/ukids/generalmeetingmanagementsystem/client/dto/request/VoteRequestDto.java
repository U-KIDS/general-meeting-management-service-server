package io.ukids.generalmeetingmanagementsystem.client.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
@Builder
public class VoteRequestDto {
    Long agendaId;
    String studentNumber;
    String voteValue;
}
