package io.ukids.generalmeetingmanagementsystem.admin.service;

import io.ukids.generalmeetingmanagementsystem.domain.member.Member;
import io.ukids.generalmeetingmanagementsystem.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberAdminService {

    private final MemberRepository memberRepository;

    @Transactional
    public void permit(String studentNumber) {
        Member member = memberRepository.findByStudentNumber(studentNumber)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        member.permit();
    }
}
