package io.ukids.generalmeetingmanagementsystem.admin.service.agenda;

import io.ukids.generalmeetingmanagementsystem.admin.dto.response.AgendaDetailDto;
import io.ukids.generalmeetingmanagementsystem.admin.dto.response.VoteListDto;
import io.ukids.generalmeetingmanagementsystem.common.exception.BaseException;
import io.ukids.generalmeetingmanagementsystem.common.exception.ErrorCode;
import io.ukids.generalmeetingmanagementsystem.domain.agenda.Agenda;
import io.ukids.generalmeetingmanagementsystem.domain.agenda.AgendaRepository;
import io.ukids.generalmeetingmanagementsystem.domain.agendaimage.AgendaImage;
import io.ukids.generalmeetingmanagementsystem.domain.agendaimage.AgendaImageRepository;
import io.ukids.generalmeetingmanagementsystem.domain.meeting.Meeting;
import io.ukids.generalmeetingmanagementsystem.domain.meeting.MeetingRepository;
import io.ukids.generalmeetingmanagementsystem.domain.vote.Vote;
import io.ukids.generalmeetingmanagementsystem.domain.vote.VoteQueryRepository;
import io.ukids.generalmeetingmanagementsystem.domain.vote.VoteRepository;
import io.ukids.generalmeetingmanagementsystem.domain.vote.VoteSearchCondition;
import io.ukids.generalmeetingmanagementsystem.domain.vote.enums.VoteValue;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public AgendaDetailDto findOne(Long agendaId) {
        Agenda agenda = agendaRepository.findById(agendaId)
                .orElseThrow(() -> new BaseException(ErrorCode.AGENDA_NOT_FOUND));

        List<AgendaImage> images = agendaImageRepository.findAllByAgendaId(agendaId);
        List<String> imageUrls = images.stream()
                .map(AgendaImage::getImageUrl)
                .collect(Collectors.toList());

        AgendaDetailDto.VotePreviewDto votePreviewDto = AgendaDetailDto.VotePreviewDto.builder()
                .agree(voteRepository.countAllByVoteValue(VoteValue.AGREE))
                .disagree(voteRepository.countAllByVoteValue(VoteValue.DISAGREE))
                .abstention(voteRepository.countAllByVoteValue(VoteValue.ABSTENTION))
                .build();

        return AgendaDetailDto.builder()
                .title(agenda.getTitle())
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
                .size(votes.size())
                .voteInfoDtos(votes.stream()
                        .map(vote -> new VoteListDto.VoteInfoDto(vote))
                        .collect(Collectors.toList()))
                .build();
    }
}
