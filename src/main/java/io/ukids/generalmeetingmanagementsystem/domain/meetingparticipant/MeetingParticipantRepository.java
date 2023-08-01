package io.ukids.generalmeetingmanagementsystem.domain.meetingparticipant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingParticipantRepository extends JpaRepository<MeetingParticipant, Long> {
}
