package io.ukids.generalmeetingmanagementsystem.admin.service;

import io.ukids.generalmeetingmanagementsystem.admin.dto.response.MeetingInfoDto;
import io.ukids.generalmeetingmanagementsystem.admin.dto.response.MeetingListDto;
import io.ukids.generalmeetingmanagementsystem.common.dto.CreateDto;
import io.ukids.generalmeetingmanagementsystem.common.dto.ListDto;
import io.ukids.generalmeetingmanagementsystem.common.exception.BaseException;
import io.ukids.generalmeetingmanagementsystem.common.exception.ErrorCode;
import io.ukids.generalmeetingmanagementsystem.common.mapper.MeetingMapper;
import io.ukids.generalmeetingmanagementsystem.domain.meeting.Meeting;
import io.ukids.generalmeetingmanagementsystem.domain.meeting.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MeetingAdminService {

    private final MeetingRepository meetingRepository;
    private final MeetingMapper meetingMapper;

    public ListDto<MeetingListDto> query() {
        List<Meeting> meetings = meetingRepository.findAllByActivate(true);
        List<MeetingListDto> result = meetings.stream()
                .map(meetingMapper::map)
                .collect(Collectors.toList());

        return new ListDto(result);
    }

    @Transactional
    public CreateDto create(MeetingInfoDto meetingInfoDto) {
        Meeting meeting = meetingMapper.map(meetingInfoDto);
        Long id = meetingRepository.save(meeting).getId();

        return new CreateDto(id);
    }

    @Transactional
    public void start(Long id) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.MEETING_NOT_FOUND));
        meeting.start();
    }

    @Transactional
    public void end(Long id) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        meeting.end();
    }


    @Transactional(readOnly = true)
    public Long update() {
        return null;
    }

    @Transactional(readOnly = true)
    public void delete(Long meetingId) {
        meetingRepository.deleteById(meetingId);
    }
}
