package io.ukids.generalmeetingmanagementsystem.domain.agendaimage;

import io.ukids.generalmeetingmanagementsystem.domain.agenda.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendaImageRepository extends JpaRepository<AgendaImage, Long> {
    List<AgendaImage> findAllByAgendaId(Long agendaId);
    void deleteAllByAgendaId(Long agendaId);
    void deleteAllByAgenda(Agenda agenda);
}
