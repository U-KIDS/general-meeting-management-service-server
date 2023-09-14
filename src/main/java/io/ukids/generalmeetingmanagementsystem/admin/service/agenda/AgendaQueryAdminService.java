package io.ukids.generalmeetingmanagementsystem.admin.service.agenda;

import io.ukids.generalmeetingmanagementsystem.admin.dto.request.AgendaInfoDto;
import io.ukids.generalmeetingmanagementsystem.admin.dto.response.*;
import io.ukids.generalmeetingmanagementsystem.common.dto.ListDto;
import io.ukids.generalmeetingmanagementsystem.common.exception.BaseException;
import io.ukids.generalmeetingmanagementsystem.common.exception.ErrorCode;
import io.ukids.generalmeetingmanagementsystem.domain.agenda.Agenda;
import io.ukids.generalmeetingmanagementsystem.domain.agenda.AgendaRepository;
import io.ukids.generalmeetingmanagementsystem.domain.agendaimage.AgendaImage;
import io.ukids.generalmeetingmanagementsystem.domain.agendaimage.AgendaImageRepository;
import io.ukids.generalmeetingmanagementsystem.domain.meeting.Meeting;
import io.ukids.generalmeetingmanagementsystem.domain.meeting.MeetingRepository;
import io.ukids.generalmeetingmanagementsystem.domain.member.Member;
import io.ukids.generalmeetingmanagementsystem.domain.member.MemberRepository;
import io.ukids.generalmeetingmanagementsystem.domain.member.enums.Authority;
import io.ukids.generalmeetingmanagementsystem.domain.vote.Vote;
import io.ukids.generalmeetingmanagementsystem.domain.vote.VoteQueryRepository;
import io.ukids.generalmeetingmanagementsystem.domain.vote.VoteRepository;
import io.ukids.generalmeetingmanagementsystem.domain.vote.VoteSearchCondition;
import io.ukids.generalmeetingmanagementsystem.domain.vote.enums.VoteValue;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AgendaQueryAdminService {

    private final AgendaRepository agendaRepository;
    private final AgendaImageRepository agendaImageRepository;
    private final VoteRepository voteRepository;
    private final VoteQueryRepository voteQueryRepository;
    private final MemberRepository memberRepository;

    public AgendaDetailDto findOne(Long agendaId) {
        Agenda agenda = agendaRepository.findById(agendaId)
                .orElseThrow(() -> new BaseException(ErrorCode.AGENDA_NOT_FOUND));

        List<AgendaImage> images = agendaImageRepository.findAllByAgendaId(agendaId);
        List<String> imageUrls = images.stream()
                .map(AgendaImage::getImageUrl)
                .collect(Collectors.toList());

        AgendaDetailDto.VotePreviewDto votePreviewDto = AgendaDetailDto.VotePreviewDto.builder()
                .agree(voteRepository.countAllByAgendaIdAndVoteValue(agendaId, VoteValue.AGREE))
                .disagree(voteRepository.countAllByAgendaIdAndVoteValue(agendaId, VoteValue.DISAGREE))
                .abstention(voteRepository.countAllByAgendaIdAndVoteValue(agendaId, VoteValue.ABSTENTION))
                .build();

        return AgendaDetailDto.builder()
                .title(agenda.getTitle())
                .agendaNumber(agenda.getAgendaNumber())
                .agendaCreateBy(agenda.getAgendaCreateBy())
                .result(agenda.getResult())
                .agendaStatus(agenda.getStatus())
                .votePreviewDto(votePreviewDto)
                .imageUrls(imageUrls)
                .build();
    }

    public VoteListDto queryVote(Long agendaId, VoteSearchCondition condition, Pageable pageable) {
        Agenda agenda = agendaRepository.findById(agendaId)
                .orElseThrow(() -> new BaseException(ErrorCode.AGENDA_NOT_FOUND));
        List<Vote> votes = voteQueryRepository.findDynamicQueryVote(agenda, condition, pageable);

        return VoteListDto.builder()
                .agendaName(agenda.getTitle())
                .agendaStatus(agenda.getStatus())
                .size(votes.size())
                .votes(votes.stream()
                        .map(vote -> new VoteListDto.VoteInfoDto(vote))
                        .collect(Collectors.toList()))
                .build();
    }

    public ListDto<MeetingDetailDto.AgendaInfoDto> queryOverview(Long meetingId) {
        List<Agenda> agendas = agendaRepository.findAllByMeetingId(meetingId);
        List<MeetingDetailDto.AgendaInfoDto> result = agendas.stream()
                .map(agenda -> new MeetingDetailDto.AgendaInfoDto(agenda))
                .collect(Collectors.toList());
        return new ListDto(result);
    }

    public VoteOverviewDto queryVoteOverview(Long agendaId) {
        List<Member> members = memberRepository.findAllByActivateAndAuthoritiesInOrderByNameAsc(true, Collections.singleton(Authority.ROLE_USER));
        List<VoteOverviewDto.MemberVoteDto> votes = members.stream()
                .map((member) -> {
                    Vote vote = voteRepository.findByAgendaIdAndMember(agendaId, member)
                            .orElse(null);
                    if (vote == null) {
                        return VoteOverviewDto.MemberVoteDto.builder()
                                .name(member.getName())
                                .value(null)
                                .build();
                    }
                    return VoteOverviewDto.MemberVoteDto.builder()
                            .name(member.getName())
                            .value(vote.getVoteValue())
                            .build();
                })
                .collect(Collectors.toList());
        return VoteOverviewDto.builder()
                .agreeCount(voteRepository.countAllByAgendaIdAndVoteValue(agendaId, VoteValue.AGREE))
                .disagreeCount(voteRepository.countAllByAgendaIdAndVoteValue(agendaId, VoteValue.DISAGREE))
                .abstentionCount(voteRepository.countAllByAgendaIdAndVoteValue(agendaId, VoteValue.ABSTENTION))
                .memberVote(votes)
                .build();
    }
}
