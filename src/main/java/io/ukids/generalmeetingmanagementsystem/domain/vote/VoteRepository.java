package io.ukids.generalmeetingmanagementsystem.domain.vote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote,Long> {

    Boolean existsByAgenda_IdAndMember_StudentNumber(Long agndaId, String studentNumber);
}
