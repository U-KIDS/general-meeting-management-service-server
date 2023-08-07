package io.ukids.generalmeetingmanagementsystem.client.service;

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

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VoteService {

    private final VoteRepository voteRepository;
    private final AgendaRepository agendaRepository;
    private final MemberRepository memberRepository;

    // 투표하기 버튼 클릭 시 어떤 안건에 어떤 멤버가 투표했는지 찬반 여부 저장
    public String onClickVote(Long agendaId, String studentNumber, String voteValue) {
        // 투표를 이미 했는지 확인
        // 투표를 이미 한 경우 return "이미 투표 완료된 안건입니다."
        if(voteRepository.existsByAgenda_IdAndMember_StudentNumber(agendaId, studentNumber)){
            return "이미 투표 완료된 안건입니다.";
        }
        else {
            // 투표를 아직 안한 경우 DB에 저장하고 return "투표를 완료했습니다."
            Agenda agenda = agendaRepository.findById(agendaId)
                    .orElseThrow(()-> new IllegalArgumentException("안건을 찾을 수 없습니다."));

            Member member = memberRepository.findByStudentNumber(studentNumber)
                    .orElseThrow(()-> new IllegalArgumentException("학생을 찾을 수 없습니다."));

            Vote vote = Vote.builder()
                    .voteValue(VoteValue.valueOf(voteValue))
                    .agenda(agenda)
                    .member(member)
                    .build();

            voteRepository.save(vote);

            return "투표를 완료했습니다.";
        }
    }
}