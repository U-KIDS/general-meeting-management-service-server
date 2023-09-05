package io.ukids.generalmeetingmanagementsystem.domain.agendaimage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendaImageRepository extends JpaRepository<AgendaImage, Long> {
    List<AgendaImage> findAllByAgendaId(Long agendaId);
}
