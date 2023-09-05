package io.ukids.generalmeetingmanagementsystem.domain.agendaimage;

import io.ukids.generalmeetingmanagementsystem.domain.agenda.Agenda;
import io.ukids.generalmeetingmanagementsystem.domain.basetime.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AgendaImage extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imageUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agenda_id")
    private Agenda agenda;

    @Builder
    public AgendaImage(String imageUrl, Agenda agenda) {
        this.imageUrl = imageUrl;
        this.agenda = agenda;
    }
}
