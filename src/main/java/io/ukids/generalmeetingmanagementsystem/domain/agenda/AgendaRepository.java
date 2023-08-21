package io.ukids.generalmeetingmanagementsystem.domain.agenda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    @Override
    Optional<Agenda> findById(Long aLong);
    Optional<List<Agenda>> findAllByMeeting_Name(String meetingName);
    Optional<List<Agenda>> findAllByMeetingId(Long id);
    List<Agenda> findAllByMeeting_Id(Long id);
}

