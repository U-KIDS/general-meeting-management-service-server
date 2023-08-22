package io.ukids.generalmeetingmanagementsystem.admin.service.member;

import io.ukids.generalmeetingmanagementsystem.admin.dto.response.MemberDetailDto;
import io.ukids.generalmeetingmanagementsystem.admin.dto.response.MemberListDto;
import io.ukids.generalmeetingmanagementsystem.common.dto.ListDto;
import io.ukids.generalmeetingmanagementsystem.common.exception.BaseException;
import io.ukids.generalmeetingmanagementsystem.common.exception.ErrorCode;
import io.ukids.generalmeetingmanagementsystem.common.mapper.MemberMapper;
import io.ukids.generalmeetingmanagementsystem.domain.member.Member;
import io.ukids.generalmeetingmanagementsystem.domain.member.MemberQueryRepository;
import io.ukids.generalmeetingmanagementsystem.domain.member.MemberRepository;
import io.ukids.generalmeetingmanagementsystem.domain.member.MemberSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberAdminService {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;
    private final MemberMapper memberMapper;

    public void permit(String studentNumber) {
        Member member = memberRepository.findByStudentNumber(studentNumber)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        member.permit();
    }

    public void block(String studentNumber) {
        Member member = memberRepository.findByStudentNumber(studentNumber)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
        member.block();
    }

    public void delete(String studentNumber) {
        memberRepository.deleteByStudentNumber(studentNumber);
    }
}
