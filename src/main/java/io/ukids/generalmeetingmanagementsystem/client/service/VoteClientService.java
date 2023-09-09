package io.ukids.generalmeetingmanagementsystem.client.service;

import io.ukids.generalmeetingmanagementsystem.client.dto.request.VoteClientRequestDto;
import io.ukids.generalmeetingmanagementsystem.common.dto.CreateDto;
import io.ukids.generalmeetingmanagementsystem.common.exception.BaseException;
import io.ukids.generalmeetingmanagementsystem.common.exception.ErrorCode;
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

import static io.ukids.generalmeetingmanagementsystem.common.exception.ErrorCode.*;

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
                    .orElseThrow(() -> new BaseException(AGENDA_NOT_FOUND));

            Member member = memberRepository.findByStudentNumber(voteClientRequestDto.getStudentNumber())
                    .orElseThrow(() -> new BaseException(MEMBER_NOT_FOUND));

            Vote vote = Vote.builder()
                    .voteValue(VoteValue.valueOf(voteClientRequestDto.getVoteValue()))
                    .agenda(agenda)
                    .member(member)
                    .build();

            voteRepository.save(vote);

            return new CreateDto(vote.getId());
        }
    }

    @Transactional
    public void vote(Long agendaId, String studentNumber, VoteValue voteValue) {
        if(voteRepository.existsByAgenda_IdAndMember_StudentNumber(agendaId, studentNumber)){
            throw new BaseException(AGENDA_ALREADY_VOTED);
        }

        Agenda agenda = agendaRepository.findById(agendaId)
                .orElseThrow(() -> new BaseException(AGENDA_NOT_FOUND));
        Member member = memberRepository.findByStudentNumber(studentNumber)
                .orElseThrow(() -> new BaseException(MEMBER_NOT_FOUND));

        Vote vote = Vote.builder()
                .voteValue(voteValue)
                .agenda(agenda)
                .member(member)
                .build();
        voteRepository.save(vote);
    }

}