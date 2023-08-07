package io.ukids.generalmeetingmanagementsystem.client.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
@Builder
public class VoteClientRequestDto {
    Long agendaId;
    String studentNumber;
    String voteValue;
}
