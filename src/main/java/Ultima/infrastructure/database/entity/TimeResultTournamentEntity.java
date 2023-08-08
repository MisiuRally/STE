package Ultima.infrastructure.database.entity;

import Ultima.domain.TimeResult;
import Ultima.domain.TimeResultTournament;
import Ultima.domain.Tournament;
import jakarta.persistence.*;
import lombok.*;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
//@EqualsAndHashCode(of = "")
//@ToString(of = {""})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "time_result_tournament")
public class TimeResultTournamentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_result_tournament_id")
    private Integer timeResultTournamentId;

    @OneToOne
    @JoinColumn(name = "tournament_entity_id")
    private TournamentEntity tournament;



}
