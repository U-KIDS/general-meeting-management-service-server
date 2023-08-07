package io.ukids.generalmeetingmanagementsystem.client.service;

import io.ukids.generalmeetingmanagementsystem.client.dto.request.VoteClientRequestDto;
import io.ukids.generalmeetingmanagementsystem.common.dto.CreateDto;
import io.ukids.generalmeetingmanagementsystem.common.exception.BaseException;
import io.ukids.generalmeetingmanagementsystem.domain.agenda.Agenda;
import io.ukids.generalmeetingmanagementsystem.domain.agenda.AgendaRepository;
import io.ukids.generalmeetingmanagementsystem.domain.member.Member;
import io.ukids.generalmeetingmanagementsystem.domain.member.MemberRepository;
import io.ukids.generalmeetingmanagementsystem.domain.vote.Vote;
import io.ukids.generalmeetingmanagementsystem.domain.vote.VoteRepository;
import io.ukids.generalmeetingmanagementsystem.domain.vote.enums.VoteValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static io.ukids.generalmeetingmanagementsystem.common.exception.ErrorCode.AGENDA_ALREADY_VOTED;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VoteClientService {

    private final VoteRepository voteRepository;
    private final AgendaRepository agendaRepository;
    private final MemberRepository memberRepository;

    // 투표하기 버튼 클릭 시 어떤 안건에 어떤 멤버가 투표했는지 찬반 여부 저장
    public CreateDto onClickVote(VoteClientRequestDto voteClientRequestDto) {
        // 투표를 이미 했는지 확인
        if(voteRepository.existsByAgenda_IdAndMember_StudentNumber(voteClientRequestDto.getAgendaId(), voteClientRequestDto.getStudentNumber())){
            throw new BaseException(AGENDA_ALREADY_VOTED);
        }
        else {
            // 투표를 아직 안한 경우 DB에 저장
            Agenda agenda = agendaRepository.findById(voteClientRequestDto.getAgendaId())
                    .orElseThrow(()-> new IllegalArgumentException("안건을 찾을 수 없습니다."));

            Member member = memberRepository.findByStudentNumber(voteClientRequestDto.getStudentNumber())
                    .orElseThrow(()-> new IllegalArgumentException("학생을 찾을 수 없습니다."));

            Vote vote = Vote.builder()
                    .voteValue(VoteValue.valueOf(voteClientRequestDto.getVoteValue()))
                    .agenda(agenda)
                    .member(member)
                    .build();

            voteRepository.save(vote);

            return new CreateDto(vote.getId());
        }
    }

}