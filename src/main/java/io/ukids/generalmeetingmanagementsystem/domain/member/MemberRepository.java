package io.ukids.generalmeetingmanagementsystem.domain.member;

import io.ukids.generalmeetingmanagementsystem.domain.member.enums.Authority;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @EntityGraph(attributePaths = "authorities") // 쿼리를 수행할 때 Eager 조회로 authorities 정보를 가져옴
    Optional<Member> findOneWithAuthoritiesByStudentNumber(String studentNumber);
    Optional<Member> findByStudentNumber(String studentNumber);
    Boolean existsByStudentNumber(String username);
    void deleteByStudentNumber(String studentNumber);
    List<Member> findAllByActivateAndAuthoritiesIn(Boolean activate, Set<Authority> authorities);
    List<Member> findAllByActivateAndAuthoritiesInOrderByNameAsc(Boolean activate, Set<Authority> authorities);
}
