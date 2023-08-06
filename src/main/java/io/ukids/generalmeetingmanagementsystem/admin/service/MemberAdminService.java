package io.ukids.generalmeetingmanagementsystem.admin.service;

import io.ukids.generalmeetingmanagementsystem.domain.member.Member;
import io.ukids.generalmeetingmanagementsystem.domain.member.MemberQueryRepository;
import io.ukids.generalmeetingmanagementsystem.domain.member.MemberRepository;
import io.ukids.generalmeetingmanagementsystem.domain.member.MemberSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberAdminService {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;

    public List<Member> query(MemberSearchCondition condition, Pageable pageable) {
        return memberQueryRepository.findDynamicQueryMembers(condition, pageable);
    }

    @Transactional
    public void permit(String studentNumber) {
        Member member = memberRepository.findByStudentNumber(studentNumber)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        member.permit();
    }
}
