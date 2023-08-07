package io.ukids.generalmeetingmanagementsystem.client.service;

import io.ukids.generalmeetingmanagementsystem.client.dto.response.MeetingClientDto;
import io.ukids.generalmeetingmanagementsystem.client.dto.response.MemberDetailClientResponseDto;
import io.ukids.generalmeetingmanagementsystem.common.exception.BaseException;
import io.ukids.generalmeetingmanagementsystem.domain.meeting.Meeting;
import io.ukids.generalmeetingmanagementsystem.domain.meeting.MeetingRepository;
import io.ukids.generalmeetingmanagementsystem.domain.member.Member;
import io.ukids.generalmeetingmanagementsystem.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static io.ukids.generalmeetingmanagementsystem.common.exception.ErrorCode.MEMBER_NOT_FOUND;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberClientService {

    private final MemberRepository memberRepository;
    private final MeetingRepository meetingRepository;

    public MemberDetailClientResponseDto findMemberDetail(String studentNumber) {
        Member member = memberRepository.findByStudentNumber(studentNumber)
                .orElseThrow(() -> new BaseException(MEMBER_NOT_FOUND));

        List<Meeting> meetingList = meetingRepository.findAllByActivate(true);

        return MemberDetailClientResponseDto.builder()
                .memberId(member.getId())
                .college(member.getCollege())
                .major(member.getMajor())
                .name(member.getName())
                .meetingList(meetingList.stream()
                        .map(m -> new MeetingClientDto(m))
                        .collect(Collectors.toList()))
                .build();
    }
}